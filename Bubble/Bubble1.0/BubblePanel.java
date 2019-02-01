import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class BubblePanel extends JPanel {
	Random rand=new Random();
	ArrayList<Bubble> bubbleList;//存储泡泡
	int size=25;//泡泡大小
	Timer timer;
	int delay=33;//时钟延时
	private JSlider slider;//控制速度
	
	public BubblePanel()
	{
		timer=new Timer(delay,new BubbleListener());
		bubbleList=new ArrayList<Bubble>();
		setBackground(Color.white);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btnPause = new JButton("暂停");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn=(JButton)e.getSource();
				if(btn.getText().equals("暂停"))
				{
					timer.stop();
					btn.setText("开始");
				}
				else
				{
					timer.start();
					btn.setText("暂停");
				}
			}
		});
		
		JLabel lblSpeed = new JLabel("速度");
		panel.add(lblSpeed);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int speed=slider.getValue()+1;
				int delay=1000/speed;
				timer.setDelay(delay);
			}
		});
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMaximum(120);
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(30);
		panel.add(slider);
		panel.add(btnPause);
		
		JButton btnClear = new JButton("清除");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bubbleList=new ArrayList<Bubble>();
				repaint();
			}
		});
		panel.add(btnClear);
		//testBubbles();
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		
		timer.start();
	}
	
	public void paintComponent(Graphics canvas)
	{
		super.paintComponent(canvas);
		for(Bubble b:bubbleList)
		{
			b.draw(canvas);
		}
	}
	
	
	public void testBubbles()//初始测试
	{
		for(int i=0;i<10000;i++)
		{
			int x=rand.nextInt(600);
			int y=rand.nextInt(400);
			bubbleList.add(new Bubble(x,y,size));
		}
		repaint();
	}
	
	
	private class BubbleListener extends MouseAdapter implements ActionListener
	{
		public void mousePressed(MouseEvent e)//鼠标点击事件
		{
			bubbleList.add(new Bubble(e.getX(),e.getY(),size));
			repaint();
		}
		
		public void mouseDragged(MouseEvent e)//鼠标拖拽事件
		{
			bubbleList.add(new Bubble(e.getX(),e.getY(),size));
			repaint();
		}
		
		public void mouseWheelMoved(MouseWheelEvent e)//鼠标滚轮滑动事件
		{
			if(System.getProperty("os.name").startsWith("Mac"))//为不同系统滑轮方向设置不同响应方式
			{
				size+=e.getUnitsToScroll();
			}
			else
			{
				size-=e.getUnitsToScroll();
			}
			
			if(size<3)size=3;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			for(Bubble b:bubbleList)
			{
				b.update();
			}
			repaint();
		}
	}
	
	private class Bubble//描述一个泡泡
	{
		private int x;
		private int y;
		private int size;
		private Color color;
		private int xspeed,yspeed;
		private final int MAX_SPEED=5;
		public Bubble(int newX,int newY,int newSize)
		{//以随机参数生成一个泡泡
			x=newX;
			y=newY;
			size=newSize;
			color=new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
			xspeed=rand.nextInt(MAX_SPEED*2+1)-MAX_SPEED;
			yspeed=rand.nextInt(MAX_SPEED*2+1)-MAX_SPEED;
			if(xspeed==0)xspeed=1;//防止泡泡停留原地
			if(yspeed==0)yspeed=1;
		}
		public void draw(Graphics canvas)//绘制泡泡
		{
			canvas.setColor(color);
			canvas.fillOval(x-size/2, y-size/2, size, size);
		}
		
		public void update()//泡泡移动更新
		{
			x+=xspeed;
			y+=yspeed;
			if(x-size/2<=0||x+size/2>=getWidth())
			{
				xspeed=-xspeed;
			}
			if(y-size/2<=0||y+size/2>=getHeight())
			{
				yspeed=-yspeed;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
