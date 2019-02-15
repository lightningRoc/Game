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
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class TetrisGamePanel extends JPanel {
	
	private Blocks blocks;//标识块
	private static Timer timer;//控制时钟
	private static Timer pause;//控制暂停键的闪烁显示
	private static int score;//得分
	private JLabel lblScore;
	private int delay=500;//延时
	private JLabel pauseAndStart;
	private JButton btnEasy;
	private JButton btnNormal;
	private JButton btnHard;
	private JLabel lblDifficulty;//显示难度
	private JButton btnShowTip;
	private boolean tip;//是否显示网格线
	
	public TetrisGamePanel(JFrame GUI)
	{
		//初始化
		score=0;
		tip=false;
		setBackground(Color.white);
		pauseAndStart=new JLabel("开始");
		pauseAndStart.setBounds(213, 5, 35, 15);
		pauseAndStart.setVisible(false);
		setLayout(null);
		add(pauseAndStart);
		
		JPanel panelControl = new JPanel();
		panelControl.setBounds(500, 0, 100, 500);
		add(panelControl);
		panelControl.setLayout(null);
		
		lblScore = new JLabel("得分:"+score);
		lblScore.setBounds(24, 32, 54, 15);
		panelControl.add(lblScore);
		lblScore.setForeground(Color.BLACK);
		lblScore.setOpaque(false);
		
		lblDifficulty = new JLabel("");
		lblDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficulty.setBounds(10, 78, 80, 15);
		panelControl.add(lblDifficulty);
		
		btnShowTip = new JButton("\u7F51\u683C\u7EBF");
		btnShowTip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//控制网格线的显示与否
				tip=!tip;
				GUI.requestFocus();
				repaint();
			}
		});
		btnShowTip.setBounds(10, 148, 80, 23);
		panelControl.add(btnShowTip);
		
		btnEasy = new JButton("\u7B80\u5355");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//简单
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				lblDifficulty.setText("难度:简单");
				GUI.requestFocus();//改变焦点以便键盘控制能够正确获取
				timer.start();
			}
		});
		btnEasy.setBounds(52, 154, 93, 23);
		add(btnEasy);
		
		btnNormal = new JButton("\u666E\u901A");
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//普通
				timer.setDelay(200);
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				lblDifficulty.setText("难度:普通");
				GUI.requestFocus();
				timer.start();
			}
		});
		btnNormal.setBounds(191, 154, 93, 23);
		add(btnNormal);
		
		btnHard = new JButton("\u56F0\u96BE");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//困难
				timer.setDelay(100);
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				lblDifficulty.setText("难度:困难");
				GUI.requestFocus();
				timer.start();
			}
		});
		btnHard.setBounds(331, 154, 93, 23);
		add(btnHard);
		
		blocks=new Blocks();
		timer=new Timer(delay,new ControlListener());
		pause=new Timer(delay,new Blink());
		
		GUI.addKeyListener(new ControlListener());
		
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
			case KeyEvent.VK_UP://改变方向
				blocks.changeDirect();
				repaint();
				break;
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
			case KeyEvent.VK_LEFT://左移
				blocks.move(0);
				repaint();
				break;
			case KeyEvent.VK_RIGHT://右移
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
		if(tip)drawTip(canvas);
	}
	
	public static void addScore() 
	{
		score++;
	}
	
	//显示网格线提示
	public void drawTip(Graphics canvas)
	{
		canvas.setColor(Color.BLUE);
		for(int i=0;i<=500;i+=10)
		{
			canvas.drawLine(0,i,500,i);
		}
		for(int i=0;i<=500;i+=10)
		{
			canvas.drawLine(i,0,i,500);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
