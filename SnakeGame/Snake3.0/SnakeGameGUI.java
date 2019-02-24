import javax.swing.JFrame;

/**
 * 贪吃蛇3.0
 * 在贪吃蛇2.0的基础上增加一些改进
 * 操作方式：添加在按下同移动方向相同的按键时，有加速效果
 * 添加通关系统，通过额外的显示窗口显示当前长度/分数以及目标长度/分数，达到目标时可以选择进入下一关
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
		frame.setSize(new java.awt.Dimension(WIDTH+107,HEIGHT+28));//适应windows下的500*500的显示大小
		frame.setVisible(true);
	}

}
