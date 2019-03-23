import javax.swing.JFrame;

/**
 * 贪吃蛇4.0
 * 在贪吃蛇3.0的基础上添加简单的贴图处理，改变外观显示
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
