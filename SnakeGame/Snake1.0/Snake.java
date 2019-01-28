import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;

public class Snake {
	private static final int UP = 1, DOWN = -1, LEFT = 2, RIGHT = -2;//���˶������ʶ
	private int direction;//��ʾ�ߵĵ�ǰ�˶�����
	private LinkedList<Node> body;//�����ߵ�����
	public static final int size=10;//����С
	
	private class Node//��ʾ������Ľ��
	{
		//����������С
		private int x;
		private int y;
		
		
		public Node(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		
		public void draw(Graphics canvas)//����һ����(x,y)ΪԲ�ĵ�һ��Բ�ν��
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
		body.addLast(new Node(SnakeGameGUI.getW()/2+5,SnakeGameGUI.getH()/2+5));//��ʼ�����������
		body.addLast(new Node(SnakeGameGUI.getW()/2+5-size,SnakeGameGUI.getH()/2+5));
		body.addLast(new Node(SnakeGameGUI.getW()/2+5-size-size,SnakeGameGUI.getH()/2+5));
	}
	
	public boolean move(Food food)//�������ƶ�
	{
		// 1.ȥβ
		boolean ok=true;
		int x = body.peekFirst().getX();
		int y = body.peekFirst().getY();
		switch(direction)//������һͷ����Լ��Ƿ�ײǽ
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
	
	public boolean canEat(Node head,Food food)//�ж�ʳ���Ƿ��ܳ�
	{
		if(food.getX()==head.getX()&&food.getY()==head.getY())return true;
		else return false;
	}
	
	public boolean hitBody(Node next)//�ж��Ƿ�ײ������
	{
		HashSet<Food> aux=BeUsed();
		for(Food f:aux)
		{
			if(next.getX()==f.getX()&&next.getY()==f.getY())return true;
		}
		
		return false;
	}
	
	public void drawSnake(Graphics canvas)//����������
	{
		for(Node node:body)
		{
			node.draw(canvas);
		}
	}
	
	public void setDirection(int direction)//�ı��ߵķ���
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
	
	public HashSet<Food> BeUsed()//�����Ѿ���ռ�õĽ��
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
