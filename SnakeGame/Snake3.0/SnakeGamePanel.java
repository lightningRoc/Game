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
	
	private Snake snake;//������
	private Food food;//��ʾʳ��
	private Wall walls;//��ʾǽ��
	private static Timer timer;//����ʱ��
	private static Timer pause;//������ͣ������˸��ʾ
	private int delay=500;
	private JLabel pauseAndStart;
	private JButton btnEasy;
	private JButton btnNormal;
	private JButton btnHard;
	private JButton btnRestart;
	private JLabel lblScore;//��ʾ����/�÷�
	private static int score;//���泤��/�÷�
	private JLabel lblAim;
	private static int aimScore;//Ŀ�곤��/�÷�
	private JButton btnNextLevel;
	private static int level;//����ȼ�;
	
	public SnakeGamePanel(JFrame GUI)
	{
		setBackground(Color.white);
		pauseAndStart=new JLabel("��ʼ");
		pauseAndStart.setHorizontalAlignment(SwingConstants.CENTER);
		pauseAndStart.setBounds(244, 10, 34, 23);
		pauseAndStart.setVisible(false);
		setLayout(null);
		add(pauseAndStart);
		
		btnEasy = new JButton("\u7B80\u5355");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//��
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				GUI.requestFocus();//�ı佹���Ա���̿����ܹ���ȷ��ȡ
				timer.start();
			}
		});
		btnEasy.setBounds(113, 147, 93, 23);
		add(btnEasy);
		
		btnNormal = new JButton("\u4E00\u822C");
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//��ͨ
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
			public void actionPerformed(ActionEvent e) {//����
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
			public void actionPerformed(ActionEvent e) {//�ؿ���Ϸ
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
			public void actionPerformed(ActionEvent arg0) {//ת����һ��
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
		lblScore.setText("���ȣ�"+score);
		lblAim.setText("Ŀ�곤�ȣ�"+aimScore);
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
				if(snake.getDirection()==snake.getUP())snakeMove();//�����ƶ�
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
			snakeMove();
		}
	}
	
	public void snakeMove()
	{
		if(!snake.move(food))
		{
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
					"��Ϸ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("����");
			timer.stop();
			btnRestart.setVisible(true);
		}
		lblScore.setText("���ȣ�"+score);
		repaint();
		if(score==aimScore)//ͨ��
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
	
	public void paintComponent(Graphics canvas)//�������
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
