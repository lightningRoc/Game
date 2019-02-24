import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Objects;

public class Wall {
	private int x;//һ��ǽ���x����
	private int y;//һ��ǽ���y����
	private static HashSet<Wall> walls;//�������е�ǽ��
	
	public Wall()
	{
		createWallByLevel();
		//hashTest();
	}
	
	public Wall(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void createWallByLevel()
	{
		walls=new HashSet<Wall>();
		switch(SnakeGamePanel.getLevel())
		{
		case 1:
			createWall1();
			break;
		case 2:
			createWall2();
			break;
		}
	}
	
	private void createWall1()//ǽ�ڰ�ͼ1
	{
		int newX=55;
		int newY=55;
		for(int i=0;i<8;i++)
		{
			walls.add(new Wall(newX,newY));
			newX+=10;
		}
		newX=55;
		for(int i=0;i<8;i++)
		{
			walls.add(new Wall(newX,newY));
			newY+=10;
		}
		
		newX=445;
		newY=55;
		for(int i=0;i<8;i++)
		{
			walls.add(new Wall(newX,newY));
			newX-=10;
		}
		newX=445;
		for(int i=0;i<8;i++)
		{
			walls.add(new Wall(newX,newY));
			newY+=10;
		}
		
		newX=55;
		newY=445;
		for(int i=0;i<8;i++)
		{
			walls.add(new Wall(newX,newY));
			newX+=10;
		}
		newX=55;
		for(int i=0;i<8;i++)
		{
			walls.add(new Wall(newX,newY));
			newY-=10;
		}
		
		
		newX=445;
		newY=445;
		for(int i=0;i<8;i++)
		{
			walls.add(new Wall(newX,newY));
			newX-=10;
		}
		newX=445;
		for(int i=0;i<8;i++)
		{
			walls.add(new Wall(newX,newY));
			newY-=10;
		}
	}
	
	private void createWall2()//ǽ�ڰ�ͼ2
	{
		int newX=45;
		int newY=35;
		for(int i=0;i<15;i++)
		{
			walls.add(new Wall(newX,newY));
			newX+=10;
		}
		newX=95;
		newY+=100;
		for(int i=0;i<10;i++)
		{
			walls.add(new Wall(newX,newY));
			newX+=10;
		}
		newX=145;
		newY+=100;
		for(int i=0;i<5;i++)
		{
			walls.add(new Wall(newX,newY));
			newX+=10;
		}
		newY=35;
		for(int i=0;i<40;i++)
		{
			walls.add(new Wall(newX,newY));
			newY+=10;
		}
		
		//��һ��
		newX=285;
		newY=35;
		for(int i=0;i<15;i++)
		{
			walls.add(new Wall(newX,newY));
			newX+=10;
		}
		newX=285;
		newY+=100;
		for(int i=0;i<10;i++)
		{
			walls.add(new Wall(newX,newY));
			newX+=10;
		}
		newX=285;
		newY+=100;
		for(int i=0;i<5;i++)
		{
			walls.add(new Wall(newX,newY));
			newX+=10;
		}
		
		newX=285;
		newY=35;
		for(int i=0;i<40;i++)
		{
			walls.add(new Wall(newX,newY));
			newY+=10;
		}
	}
	
	public void drawWalls(Graphics canvas)//��������ǽ��
	{
		canvas.setColor(Color.BLACK);
		for(Wall w:walls)
		{
			w.drawWall(canvas);
		}
	}
	
	public void drawWall(Graphics canvas)//����һ��ǽ��
	{
		canvas.fillRect(x-Snake.size/2, y-Snake.size/2, Snake.size, Snake.size);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public HashSet<Wall> getWalls()
	{
		return walls;
	}
	
	public void hashTest()
	{
		HashSet<Wall> test=new HashSet<Wall>();
		Wall wall=new Wall(100,100);
		test.add(wall);
		wall=new Wall(100,100);
		test.add(wall);
		wall=new Wall(105,105);
		test.add(wall);
		wall=new Wall(105,105);
		test.add(wall);
		for(Wall w:test)
		{
			System.out.println(w.getX()+" "+w.getY());
		}
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
		
		if(!(other instanceof Wall))return false;
		
		Wall otherObject=(Wall) other;
		if(x==otherObject.x&&y==otherObject.y)return true;
		else return false;
	}

}
