import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;

public class Snake {
	private static final int UP = 1, DOWN = -1, LEFT = 2, RIGHT = -2;//蛇运动方向标识
	private int direction;//表示蛇的当前运动方向
	private LinkedList<Node> body;//保存蛇的主体
	public static final int size=10;//结点大小
	
	private class Node//表示蛇身体的结点
	{
		//结点坐标与大小
		private int x;
		private int y;
		
		
		public Node(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		
		public void draw(Graphics canvas)//画出一个以(x,y)为圆心的一个圆形结点
		{
			canvas.setColor(Color.BLUE);
			canvas.fillOval(x-size/2, y-size/2, size, size);
		}
		
		public int getX()
		{
			return x;
		}
		public int getY()
		{
			return y;
		}
	}
	
	public Snake()
	{
		direction=RIGHT;
		body=new LinkedList<Node>();
		body.addLast(new Node(SnakeGameGUI.getW()/2+5,SnakeGameGUI.getH()/2+5));//初始赋予三个结点
		body.addLast(new Node(SnakeGameGUI.getW()/2+5-size,SnakeGameGUI.getH()/2+5));
		body.addLast(new Node(SnakeGameGUI.getW()/2+5-size-size,SnakeGameGUI.getH()/2+5));
	}
	
	public boolean move(Food food)//控制蛇移动
	{
		// 1.去尾
		boolean ok=true;
		int x = body.peekFirst().getX();
		int y = body.peekFirst().getY();
		switch(direction)//计算下一头结点以及是否撞墙
		{
		case UP:
			y-=10;
			if(y-size/2<0)
			{
				ok=false;
			}
			break;
		case DOWN:
			y+=10;
			if(y+size/2>SnakeGameGUI.getH())
			{
				ok=false;
			}
			break;
		case LEFT:
			x-=10;
			if(x-size/2<0)
			{
				ok=false;
			}
			break;
		case RIGHT:
			x+=10;
			if(x+size/2>SnakeGameGUI.getW())
			{
				ok=false;
			}
			break;
		}
		Node newH=new Node(x,y);
		if(!canEat(newH,food))body.removeLast();
		else food.eated();
		if(hitBody(newH))return false;
		body.addFirst(newH);
		
		return ok;
	}
	
	public boolean canEat(Node head,Food food)//判断食物是否能吃
	{
		if(food.getX()==head.getX()&&food.getY()==head.getY())return true;
		else return false;
	}
	
	public boolean hitBody(Node next)//判断是否撞到身体
	{
		HashSet<Food> aux=BeUsed();
		for(Food f:aux)
		{
			if(next.getX()==f.getX()&&next.getY()==f.getY())return true;
		}
		
		return false;
	}
	
	public void drawSnake(Graphics canvas)//画出整个蛇
	{
		for(Node node:body)
		{
			node.draw(canvas);
		}
	}
	
	public void setDirection(int direction)//改变蛇的方向
	{
		if(this.direction+direction!=0)this.direction=direction;
	}
	
	public int getUP()
	{
		return UP;
	}
	
	public int getDOWN()
	{
		return DOWN;
	}
	
	public int getLEFT()
	{
		return LEFT;
	}
	
	public int getRIGHT()
	{
		return RIGHT;
	}
	
	public LinkedList<Node> getBody()
	{
		return body;
	}
	
	public HashSet<Food> BeUsed()//返回已经被占用的结点
	{
		HashSet<Food> res=new HashSet<>();
		for(Node n:body)
		{
			res.add(new Food(n.getX(),n.getY()));
		}
		
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
