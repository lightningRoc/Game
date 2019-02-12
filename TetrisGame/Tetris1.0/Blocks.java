import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class Blocks {
	
	private HashSet<Block> blocks;//��ʶ�������
	private Block[] currentBlocks;//��ʶ��ǰ����Ŀ�
	private int num;//��ʶ��ǰ������һ��Ŀ������
	private static Random rand;
	public static final int size=10;//�������С
	
	public Blocks()
	{
		rand=new Random();
		blocks=new HashSet<Block>();
		getNewBlocks();
		num=0;
		//fastTest();
	}
	
	private void fastTest()//������ٲ���
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
	
	
	
	private class Block//��ʶ������
	{
		int x;//��x����
		int y;//��y����
		
		public Block(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		
		public void draw(Graphics canvas)//����һ����(x,y)ΪԲ�ĵ�һ��Բ�ν��
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
		
		public int hashCode()//Ϊʹ��hash�����дhashCode()����
		{
			return Objects.hash(x,y);
		}
		
		@Override 
		public boolean equals(Object other)//Ϊʹ��hash�����дequals()����
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
	
	//���ݸ�������������ֿ�
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
	
	//��ȡ�µĿ�
	public boolean getNewBlocks()
	{
		int newX=5;//�����ȡ�µĿ�
		int allNum=TetrisGameGUI.getW()/size;
		int auxNum=allNum;
		for(int i=0;i<allNum;i++)
		{
			if(rand.nextInt(auxNum)==0)break;//��1/num�ļ��ʻ��ָ�����
			auxNum--;
			newX+=10;
		}
		
		int getNum=rand.nextInt(3);//�����ȡ�µĿ���ʽ
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
			if(blocks.contains(b))return false;//�Ƿ����
		}
		return true;
	}
	
	//�����ƶ���
	public boolean move()
	{
		if(touchBottom())//�����ж����׿��Է�����к������
		{
			for(Block b:currentBlocks)//����
			{
				if(b.getY()==495)
				{
					num++;
				}
				blocks.add(b);
			}
			if(!getNewBlocks())return false;//�ж��Ƿ����
		}
		
		for(Block b:currentBlocks)
		{
			b.setY(b.getY()+10);
		}
		
		if(canRemove())//��������
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
	
	//�����ƶ�
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
	
	//�Ƿ񴥵�
	public boolean touchBottom()
	{
		for(Block b:currentBlocks)
		{
			if(blocks.contains(new Block(b.getX(),b.getY()+10)))//�Ƿ�����������
			{
				return true;
			}
			
			if(b.getY()+10>TetrisGameGUI.getH())return true;//�Ƿ�����Ե
		}
		
		return false;
	}
	
	//�Ƿ���������ƶ�,0������,1��ʾ��
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
	
	//�Ƿ���������ײ�һ��
	public boolean canRemove()
	{
		return num==TetrisGameGUI.getW()/size;
	}
	
	
	//����ȫ����
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