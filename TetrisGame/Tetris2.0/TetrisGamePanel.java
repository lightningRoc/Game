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
	
	private Blocks blocks;//��ʶ��
	private static Timer timer;//����ʱ��
	private static Timer pause;//������ͣ������˸��ʾ
	private static int score;//�÷�
	private JLabel lblScore;
	private int delay=500;//��ʱ
	private JLabel pauseAndStart;
	private JButton btnEasy;
	private JButton btnNormal;
	private JButton btnHard;
	private JLabel lblDifficulty;//��ʾ�Ѷ�
	private JButton btnShowTip;
	private boolean tip;//�Ƿ���ʾ������
	
	public TetrisGamePanel(JFrame GUI)
	{
		//��ʼ��
		score=0;
		tip=false;
		setBackground(Color.white);
		pauseAndStart=new JLabel("��ʼ");
		pauseAndStart.setBounds(213, 5, 35, 15);
		pauseAndStart.setVisible(false);
		setLayout(null);
		add(pauseAndStart);
		
		JPanel panelControl = new JPanel();
		panelControl.setBounds(500, 0, 100, 500);
		add(panelControl);
		panelControl.setLayout(null);
		
		lblScore = new JLabel("�÷�:"+score);
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
			public void actionPerformed(ActionEvent e) {//���������ߵ���ʾ���
				tip=!tip;
				GUI.requestFocus();
				repaint();
			}
		});
		btnShowTip.setBounds(10, 148, 80, 23);
		panelControl.add(btnShowTip);
		
		btnEasy = new JButton("\u7B80\u5355");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//��
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				lblDifficulty.setText("�Ѷ�:��");
				GUI.requestFocus();//�ı佹���Ա���̿����ܹ���ȷ��ȡ
				timer.start();
			}
		});
		btnEasy.setBounds(52, 154, 93, 23);
		add(btnEasy);
		
		btnNormal = new JButton("\u666E\u901A");
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//��ͨ
				timer.setDelay(200);
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				lblDifficulty.setText("�Ѷ�:��ͨ");
				GUI.requestFocus();
				timer.start();
			}
		});
		btnNormal.setBounds(191, 154, 93, 23);
		add(btnNormal);
		
		btnHard = new JButton("\u56F0\u96BE");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//����
				timer.setDelay(100);
				btnEasy.setVisible(false);
				btnNormal.setVisible(false);
				btnHard.setVisible(false);
				lblDifficulty.setText("�Ѷ�:����");
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
			case KeyEvent.VK_UP://�ı䷽��
				blocks.changeDirect();
				repaint();
				break;
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
			case KeyEvent.VK_LEFT://����
				blocks.move(0);
				repaint();
				break;
			case KeyEvent.VK_RIGHT://����
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
		if(tip)drawTip(canvas);
	}
	
	public static void addScore() 
	{
		score++;
	}
	
	//��ʾ��������ʾ
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
