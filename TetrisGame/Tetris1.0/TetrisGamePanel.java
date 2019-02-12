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


public class TetrisGamePanel extends JPanel {
	
	private Blocks blocks;//标识块
	private static Timer timer;//控制时钟
	private static Timer pause;//控制暂停键的闪烁显示
	private static int score;
	private JLabel lblScore;
	private int delay=500;
	private JLabel pauseAndStart;
	
	public TetrisGamePanel(JFrame GUI)
	{
		score=0;
		setBackground(Color.white);
		pauseAndStart=new JLabel("开始");
		pauseAndStart.setBounds(213, 5, 24, 15);
		pauseAndStart.setVisible(false);
		setLayout(null);
		add(pauseAndStart);
		
		lblScore = new JLabel("得分:"+score);
		lblScore.setForeground(Color.BLUE);
		lblScore.setBounds(10, 5, 54, 15);
		lblScore.setOpaque(false);
		add(lblScore);
		
		blocks=new Blocks();
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
			case KeyEvent.VK_DOWN:
				if(!blocks.move())//加速
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
							"游戏结束", "提示", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("结束");
					timer.stop();
				}
				repaint();
				break;
			case KeyEvent.VK_LEFT:
				blocks.move(0);
				repaint();
				break;
			case KeyEvent.VK_RIGHT:
				blocks.move(1);
				repaint();
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
			if(!blocks.move())
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
		blocks.drawBlocks(canvas);
		lblScore.setText("得分:"+score);
	}
	
	public static void addScore() 
	{
		score++;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
