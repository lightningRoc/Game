import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Snake {
	private static final int UP = 1, DOWN = -1, LEFT = 2, RIGHT = -2;//���˶������ʶ
	private int direction;//��ʾ�ߵĵ�ǰ�˶�����
	private LinkedList<Node> body;//�����ߵ�����
	public static final int size=10;//����С
	private static Image bodyIcon;//�ߵ�����
	private static Image headRIcon;//���ҵ�ͷ��
	private static Image headLIcon;//�����ͷ��
	private static Image headUIcon;//���ϵ�ͷ��
	private static Image headDIcon;//���µ�ͷ��
	static//��ȡͼƬ
	{
		try {
			bodyIcon=ImageIO.read(new File("image\\snakeBody.png"));
			headRIcon=ImageIO.read(new File("image\\snakeHeadR.png"));
			headLIcon=ImageIO.read(new File("image\\snakeHeadL.png"));
			headUIcon=ImageIO.read(new File("image\\snakeHeadU.png"));
			headDIcon=ImageIO.read(new File("image\\snakeHeadD.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
			//canvas.setColor(Color.BLUE);
			//canvas.fillOval(x-size/2, y-size/2, size, size);
			canvas.drawImage(bodyIcon, x-size/2, y-size/2, size, size,null);
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
		body.addLast(new Node(25,5));//��ʼ�����������
		body.addLast(new Node(15,5));
		body.addLast(new Node(5,5));
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
				y+=SnakeGameGUI.getH();
			}
			break;
		case DOWN:
			y+=10;
			if(y+size/2>SnakeGameGUI.getH())
			{
				y-=SnakeGameGUI.getH();
			}
			break;
		case LEFT:
			x-=10;
			if(x-size/2<0)
			{
				x+=SnakeGameGUI.getW();
			}
			break;
		case RIGHT:
			x+=10;
			if(x+size/2>SnakeGameGUI.getW())
			{
				x-=SnakeGameGUI.getW();
			}
			break;
		}
		Node newH=new Node(x,y);
		if(!canEat(newH,food))body.removeLast();
		else food.eated();
		if(hitBody(newH))return false;
		if(hitWall(newH))return false;
		body.addFirst(newH);
		
		return ok;
	}
	
	public boolean canEat(Node head,Food food)//�ж�ʳ���Ƿ��ܳ�
	{
		if(food.getX()==head.getX()&&food.getY()==head.getY())return true;
		else return false;
	}
	
	public boolean hitWall(Node head)//�ж��Ƿ�ײǽ
	{
		Wall judge=new Wall(head.getX(),head.getY());
		HashSet<Wall> aux=judge.getWalls();
		if(aux.contains(judge))return true;
		else return false;
	}
	
	public boolean hitBody(Node next)//�ж��Ƿ�ײ������
	{
		HashSet<Food> aux=beUsed();
		for(Food f:aux)
		{
			if(next.getX()==f.getX()&&next.getY()==f.getY())return true;
		}
		
		return false;
	}
	
	public void drawSnake(Graphics canvas)//����������
	{
		//��������
		for(Node node:body)
		{
			node.draw(canvas);
		}
		
		//����ͷ��
		Node head=body.getFirst();
		switch(direction)
		{
		case UP:
			canvas.drawImage(headUIcon, head.getX()-size/2, head.getY()-size/2, size, size,null);
			break;
		case DOWN:
			canvas.drawImage(headDIcon, head.getX()-size/2, head.getY()-size/2, size, size,null);
			break;
		case LEFT:
			canvas.drawImage(headLIcon, head.getX()-size/2, head.getY()-size/2, size, size,null);
			break;
		case RIGHT:
			canvas.drawImage(headRIcon, head.getX()-size/2, head.getY()-size/2, size, size,null);
			break;
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
	
	public int getDirection()
	{
		return direction;
	}
	
	public LinkedList<Node> getBody()
	{
		return body;
	}
	
	public HashSet<Food> beUsed()//�����Ѿ���ռ�õĽ��
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
