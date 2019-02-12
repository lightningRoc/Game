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
	
	private Blocks blocks;//��ʶ��
	private static Timer timer;//����ʱ��
	private static Timer pause;//������ͣ������˸��ʾ
	private static int score;
	private JLabel lblScore;
	private int delay=500;
	private JLabel pauseAndStart;
	
	public TetrisGamePanel(JFrame GUI)
	{
		score=0;
		setBackground(Color.white);
		pauseAndStart=new JLabel("��ʼ");
		pauseAndStart.setBounds(213, 5, 24, 15);
		pauseAndStart.setVisible(false);
		setLayout(null);
		add(pauseAndStart);
		
		lblScore = new JLabel("�÷�:"+score);
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
			case KeyEvent.VK_DOWN:
				if(!blocks.move())//����
				{
					JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
							"��Ϸ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("����");
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
			if(!blocks.move())
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
		blocks.drawBlocks(canvas);
		lblScore.setText("�÷�:"+score);
	}
	
	public static void addScore() 
	{
		score++;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
