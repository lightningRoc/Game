import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class Blocks {
	
	private HashSet<Block> blocks;//标识块的总量
	private Block[] currentBlocks;//标识当前下落的块
	private int num;//标识当前最下面一层的块的数量
	private static Random rand;
	public static final int size=10;//单个块大小
	
	public Blocks()
	{
		rand=new Random();
		blocks=new HashSet<Block>();
		getNewBlocks();
		num=0;
		//fastTest();
	}
	
	private void fastTest()//方便快速测试
	{
		int newX=5;
		for(int i=0;i<48;i++)
		{
			blocks.add(new Block(newX,495));
			blocks.add(new Block(newX,485));
			newX+=10;
		}
		num=48;
	}
	
	
	
	private class Block//标识单个块
	{
		int x;//块x坐标
		int y;//块y坐标
		
		public Block(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		
		public void draw(Graphics canvas)//画出一个以(x,y)为圆心的一个圆形结点
		{
			canvas.setColor(Color.BLACK);
			canvas.fillRect(x-size/2, y-size/2, size, size);
		}
		
		public int getX()
		{
			return x;
		}
		public int getY()
		{
			return y;
		}
		
		public void setX(int x)
		{
			this.x=x;
		}
		
		public void setY(int y)
		{
			this.y=y;
		}
		
		public int hashCode()//为使用hash表而重写hashCode()方法
		{
			return Objects.hash(x,y);
		}
		
		@Override 
		public boolean equals(Object other)//为使用hash表而重写equals()方法
		{
			if(this==other)return true;
			if(other==null)return false;
			if(getClass()!=other.getClass())return false;
			
			if(!(other instanceof Block))return false;
			
			Block otherObject=(Block) other;
			if(x==otherObject.x&&y==otherObject.y)return true;
			else return false;
		}
	}
	
	//根据给定坐标给出各种块
	public Block[] getBlocks1(int x,int y)
	{
		if(x==5)x+=10;
		if(x==495)x-=10;
		return new Block[] {new Block(x,y),new Block(x+10,y),new Block(x,y+10),new Block(x-10,y+10)};
	}
	
	public Block[] getBlocks2(int x,int y)
	{
		if(x==5)x+=10;
		return new Block[] {new Block(x,y),new Block(x-10,y),new Block(x,y+10),new Block(x-10,y+10)};
	}
	
	public Block[] getBlocks3(int x,int y)
	{
		if(x==5)x+=10;
		return new Block[] {new Block(x,y),new Block(x,y+10),new Block(x,y+20)};
	}
	
	//获取新的块
	public boolean getNewBlocks()
	{
		int newX=5;//随机获取新的块
		int allNum=TetrisGameGUI.getW()/size;
		int auxNum=allNum;
		for(int i=0;i<allNum;i++)
		{
			if(rand.nextInt(auxNum)==0)break;//以1/num的几率获得指定结点
			auxNum--;
			newX+=10;
		}
		
		int getNum=rand.nextInt(3);//随机获取新的块样式
		switch(getNum)
		{
		case 0:
			currentBlocks=getBlocks1(newX,5);
			break;
		case 1:
			currentBlocks=getBlocks2(newX,5);
			break;
		case 2:
			currentBlocks=getBlocks3(newX,5);
			break;
		}
		
		for(Block b:currentBlocks)
		{
			if(blocks.contains(b))return false;//是否结束
		}
		return true;
	}
	
	//向下移动块
	public boolean move()
	{
		if(touchBottom())//首先判断碰底可以方便进行横向插入
		{
			for(Block b:currentBlocks)//碰底
			{
				if(b.getY()==495)
				{
					num++;
				}
				blocks.add(b);
			}
			if(!getNewBlocks())return false;//判断是否结束
		}
		
		for(Block b:currentBlocks)
		{
			b.setY(b.getY()+10);
		}
		
		if(canRemove())//可以消除
		{
			TetrisGamePanel.addScore();
			num=0;
			HashSet<Block> res=new HashSet<Block>();
			for(Block b:blocks)
			{
				if(b.getY()!=495)
				{
					if(b.getY()+10==495)num++;
					res.add(new Block(b.getX(),b.getY()+10));
				}
			}
			blocks=res;
		}
		
		return true;
	}
	
	//左右移动
	public void move(int direction)
	{
		if(canControl(direction))
		{
			for(Block b:currentBlocks)
			{
				b.setX(b.getX()+(direction==0?-10:10));
			}
		}
	}
	
	//是否触底
	public boolean touchBottom()
	{
		for(Block b:currentBlocks)
		{
			if(blocks.contains(new Block(b.getX(),b.getY()+10)))//是否碰到其他块
			{
				return true;
			}
			
			if(b.getY()+10>TetrisGameGUI.getH())return true;//是否触碰边缘
		}
		
		return false;
	}
	
	//是否可以左右移动,0代表右,1表示左
	public boolean canControl(int direction)
	{
		for(Block b:currentBlocks)
		{
			int newX=b.getX()+(direction==0?-10:10);
			int newY=b.getY();
			if(newX<5||newX>495)
			{
				return false;
			}
			
			if(blocks.contains(new Block(newX,newY)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	//是否可以消除底部一行
	public boolean canRemove()
	{
		return num==TetrisGameGUI.getW()/size;
	}
	
	
	//画出全部块
	public void drawBlocks(Graphics canvas)
	{
		for(Block b:blocks)
		{
			b.draw(canvas);
		}
		for(Block b:currentBlocks)
		{
			b.draw(canvas);
		}
	}
}