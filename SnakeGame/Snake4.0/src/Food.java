import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

public class Food {
	
	private int x;//ʳ���x����
	private int y;//ʳ���y����
	private boolean exist;//��ʶʳ���Ƿ����
	private static Random rand;
	public static Image foodIcon;//ʳ�����ͼƬ
	static
	{
		try {
			foodIcon=ImageIO.read(new File("image\\food.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Food()
	{
		x=205;
		y=205;
		exist=true;
		rand=new Random();
		//hashTest();
	}
	
	public Food(int x,int y)
	{
		this.x=x;
		this.y=y;
		exist=true;
	}
	
	public void hashTest()
	{
		HashSet<Food> test=new HashSet<Food>();
		Food food=new Food(100,100);
		test.add(food);
		food=new Food(100,100);
		test.add(food);
		food=new Food(105,105);
		test.add(food);
		food=new Food(105,105);
		test.add(food);
		for(Food f:test)
		{
			System.out.println(f.getX()+" "+f.getY());
		}
	}
	
	public static Food newFood(HashSet<Food> beUesed)//�����ȡһ�����еĽ����Ϊ��һ��ʳ��
	{
		int allNum=(SnakeGameGUI.getW()/Snake.size)*(SnakeGameGUI.getH()/Snake.size);
		int num=allNum-beUesed.size()+1;
		int newX=5,newY=5;
		for(int i=allNum;i>0;i--)
		{			
			if(!beUesed.contains(new Food(newX,newY)))
			{
				if(rand.nextInt(num)==0)break;//��1/num�ļ��ʻ��ָ�����
				num--;
			}
			
			if(newX==495)
			{
				newY+=10;
				newX=5;
			}
			else
			{
				newX+=10;
			}
		}
		
		return new Food(newX,newY);
	}
	
	public void drawFood(Graphics canvas)//����ʳ��
	{
		//canvas.setColor(Color.BLACK);
		//canvas.fillOval(x-Snake.size/2, y-Snake.size/2, Snake.size, Snake.size);
		canvas.drawImage(foodIcon,x-Snake.size/2, y-Snake.size/2, Snake.size, Snake.size,null);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean isExist()//ʳ���Ƿ񱻳�
	{
		return exist;
	}
	
	public void eated()//���Ե�
	{
		exist=false;
		SnakeGamePanel.addScore();
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
		
		if(!(other instanceof Food))return false;
		
		Food otherObject=(Food) other;
		if(x==otherObject.x&&y==otherObject.y)return true;
		else return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
