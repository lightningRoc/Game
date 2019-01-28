import java.awt.Container;

import javax.swing.JFrame;

/**
 * 贪吃蛇1.0
 * 实现贪吃蛇的基本操作
 * 操作方式：方向键控制方向，空格控制暂停
 * 通过吃豆子不断变长，碰到屏幕边缘或蛇自己的身体则游戏结束
 * 要进行新一局游戏则需要重启
 * @author PC
 *
 */
public class SnakeGameGUI extends JFrame {
	public SnakeGameGUI() {
	}
	private static int WIDTH=500;
	private static int HEIGHT=500;

	public static int getW()
	{
		return WIDTH;
	}
	public static int getH()
	{
		return HEIGHT;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame("Snake Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new SnakeGamePanel(frame));
		frame.setResizable(false);
		frame.setSize(new java.awt.Dimension(WIDTH+7,HEIGHT+28));//适应windows下的500*500的显示大小
		frame.setVisible(true);
	}

}
