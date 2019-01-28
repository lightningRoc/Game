import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SnakeGamePanel extends JPanel {
	
	private Snake snake;//������
	private Food food;//��ʾʳ��
	private static Timer timer;//����ʱ��
	private static Timer pause;//������ͣ������˸��ʾ
	private int delay=500;
	private JLabel pauseAndStart;
	
	public SnakeGamePanel(JFrame GUI)
	{
		setBackground(Color.white);
		pauseAndStart=new JLabel("��ʼ");
		pauseAndStart.setVisible(false);
		add(pauseAndStart);
		
		snake=new Snake();
		food=new Food();
		timer=new Timer(delay,new ControlListener());
		pause=new Timer(delay,new Blink());
		
		GUI.addKeyListener(new ControlListener());
		
		timer.start();
		repaint();
	}
	
	private class Blink implements ActionListener
	{
		public void actionPerformed(ActionEvent e)//��˸��ʱ������
		{
			if(pauseAndStart.isVisible())pauseAndStart.setVisible(false);
			else pauseAndStart.setVisible(true);
		}
	}
	
	private class ControlListener extends KeyAdapter implements ActionListener
	{
		public void keyPressed(KeyEvent e) //�����¼�����
		{
			switch (e.getKeyCode()) 
			{
			//��������Ʒ���
			case KeyEvent.VK_UP:
				snake.setDirection(snake.getUP());
				break;
			case KeyEvent.VK_DOWN:
				snake.setDirection(snake.getDOWN());
				break;
			case KeyEvent.VK_LEFT:
				snake.setDirection(snake.getLEFT());
				break;
			case KeyEvent.VK_RIGHT:
				snake.setDirection(snake.getRIGHT());
				break;
			//�ո������ͣ�뿪ʼ	
			case 32:
				if(pauseAndStart.getText().equals("��ʼ"))//��ͣ����ʾ����
				{
					timer.stop();
					pauseAndStart.setText("��ͣ");
					pauseAndStart.setVisible(true);
					pause.start();
				}
				else
				{
					timer.start();
					pauseAndStart.setText("��ʼ");
					pauseAndStart.setVisible(false);
					pause.stop();
				}
				break;
			}
		}
		
		public void actionPerformed(ActionEvent e)//��ʱ������
		{
			if(!snake.move(food))
			{
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
						"��Ϸ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("����");
				timer.stop();
			}
			repaint();
		}
	}
	
	public void paintComponent(Graphics canvas)//�������
	{
		super.paintComponent(canvas);
		snake.drawSnake(canvas);
		//System.out.println(food.getX()+" "+food.getY());
		if(food.isExist())
		{
			food.drawFood(canvas);
		}
		else
		{
			food=Food.newFood(snake.BeUsed());
			food.drawFood(canvas);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
