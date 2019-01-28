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
	
	private Snake snake;//蛇主体
	private Food food;//表示食物
	private static Timer timer;//控制时钟
	private static Timer pause;//控制暂停键的闪烁显示
	private int delay=500;
	private JLabel pauseAndStart;
	
	public SnakeGamePanel(JFrame GUI)
	{
		setBackground(Color.white);
		pauseAndStart=new JLabel("开始");
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
		public void actionPerformed(ActionEvent e)//闪烁计时器触发
		{
			if(pauseAndStart.isVisible())pauseAndStart.setVisible(false);
			else pauseAndStart.setVisible(true);
		}
	}
	
	private class ControlListener extends KeyAdapter implements ActionListener
	{
		public void keyPressed(KeyEvent e) //键盘事件处理
		{
			switch (e.getKeyCode()) 
			{
			//方向键控制方向
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
			//空格控制暂停与开始	
			case 32:
				if(pauseAndStart.getText().equals("开始"))//暂停后显示设置
				{
					timer.stop();
					pauseAndStart.setText("暂停");
					pauseAndStart.setVisible(true);
					pause.start();
				}
				else
				{
					timer.start();
					pauseAndStart.setText("开始");
					pauseAndStart.setVisible(false);
					pause.stop();
				}
				break;
			}
		}
		
		public void actionPerformed(ActionEvent e)//计时器触发
		{
			if(!snake.move(food))
			{
				JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
						"游戏结束", "提示", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("结束");
				timer.stop();
			}
			repaint();
		}
	}
	
	public void paintComponent(Graphics canvas)//绘制面板
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
