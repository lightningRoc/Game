import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class SnakeGamePanel extends JPanel {
	
	private Snake snake;//蛇主体
	private Food food;//表示食物
	private Wall walls;//表示墙体
	private static Timer timer;//控制时钟
	private static Timer pause;//控制暂停键的闪烁显示
	private int delay=500;
	private JLabel pauseAndStart;
	private JButton btnEasy;
	private JButton btnNormal;
	private JButton btnHard;
	private JButton btnRestart;
	private JLabel lblScore;//显示长度/得分
	private static int score;//保存长度/得分
	private JLabel lblAim;
	private static int aimScore;//目标长度/得分
	private JButton btnNextLevel;
	private static int level;//保存等级;
	
	public SnakeGamePanel(JFrame GUI)
	{
		setBackground(Color.white);
		pauseAndStart=new JLabel("开始");
		pauseAndStart.setHorizontalAlignment(SwingConstants.CENTER);
		pauseAndStart.setBounds(244, 10, 34, 23);
		pauseAndStart.setVisible(false);
		setLayout(null);
		add(pauseAndStart);
		
		btnEasy = new JButton("\u7B80\u5355");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//简单
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				GUI.requestFocus();//改变焦点以便键盘控制能够正确获取
				timer.start();
			}
		});
		btnEasy.setBounds(113, 147, 93, 23);
		add(btnEasy);
		
		btnNormal = new JButton("\u4E00\u822C");
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//普通
				timer.setDelay(300);
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				GUI.requestFocus();
				timer.start();
			}
		});
		btnNormal.setBounds(216, 147, 93, 23);
		add(btnNormal);
		
		btnHard = new JButton("\u56F0\u96BE");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//困难
				timer.setDelay(150);
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				GUI.requestFocus();
				timer.start();
			}
		});
		btnHard.setBounds(319, 147, 93, 23);
		add(btnHard);
		
		btnRestart = new JButton("\u518D\u6765\u4E00\u5C40");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//重开游戏
				init();
				btnEasy.setVisible(true);
				btnNormal.setVisible(true);
				btnHard.setVisible(true);
				btnRestart.setVisible(false);
			}
		});
		btnRestart.setBounds(216, 251, 93, 23);
		add(btnRestart);
		
		JPanel panelControl = new JPanel();
		panelControl.setBounds(500, 0, 100, 510);
		add(panelControl);
		panelControl.setLayout(null);
		
		lblScore = new JLabel("\u957F\u5EA6");
		lblScore.setBounds(23, 26, 67, 15);
		panelControl.add(lblScore);
		
		lblAim = new JLabel("\u76EE\u6807\u957F\u5EA6");
		lblAim.setBounds(10, 75, 80, 15);
		panelControl.add(lblAim);
		
		btnNextLevel = new JButton("\u4E0B\u4E00\u5173");
		btnNextLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//转到下一关
				level++;
				aimScore+=10;
				init();
				timer.start();
				GUI.requestFocus();
			}
		});
		btnNextLevel.setBounds(216, 76, 93, 23);
		add(btnNextLevel);
		
		level=1;
		aimScore=10;
		init();
		btnRestart.setVisible(false);
		timer=new Timer(delay,new ControlListener());
		pause=new Timer(delay,new Blink());
		
		GUI.addKeyListener(new ControlListener());
		
		repaint();
	}
	
	public void init()
	{
		snake=new Snake();
		food=new Food();
		walls=new Wall();
		btnNextLevel.setVisible(false);
		score=3;
		lblScore.setText("长度："+score);
		lblAim.setText("目标长度："+aimScore);
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
				if(snake.getDirection()==snake.getUP())snakeMove();//加速移动
				snake.setDirection(snake.getUP());
				break;
			case KeyEvent.VK_DOWN:
				if(snake.getDirection()==snake.getDOWN())snakeMove();
				snake.setDirection(snake.getDOWN());
				break;
			case KeyEvent.VK_LEFT:
				if(snake.getDirection()==snake.getLEFT())snakeMove();
				snake.setDirection(snake.getLEFT());
				break;
			case KeyEvent.VK_RIGHT:
				if(snake.getDirection()==snake.getRIGHT())snakeMove();
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
			snakeMove();
		}
	}
	
	public void snakeMove()
	{
		if(!snake.move(food))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"游戏结束", "提示", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("结束");
			timer.stop();
			btnRestart.setVisible(true);
		}
		lblScore.setText("长度："+score);
		repaint();
		if(score==aimScore)//通关
		{
			timer.stop();
			btnNextLevel.setVisible(true);
		}
	}
	
	public static void addScore()
	{
		score+=1;
	}
	
	public static int getLevel()
	{
		return level;
	}
	
	public void paintComponent(Graphics canvas)//绘制面板
	{
		super.paintComponent(canvas);
		snake.drawSnake(canvas);
		walls.drawWalls(canvas);
		//System.out.println(food.getX()+" "+food.getY());
		if(food.isExist())
		{
			food.drawFood(canvas);
		}
		else
		{
			HashSet<Wall> aux=walls.getWalls();
			HashSet<Food> beUsed=snake.beUsed();
			for(Wall w:aux)
			{
				beUsed.add(new Food(w.getX(),w.getY()));
			}
			food=Food.newFood(beUsed);
			food.drawFood(canvas);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
