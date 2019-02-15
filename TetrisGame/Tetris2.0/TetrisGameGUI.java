import java.awt.Container;

import javax.swing.JFrame;

/**
 * 俄罗斯方块2.0
 * 1.0:实现俄罗斯方块的简单模拟,暂无块旋转操作,且暂时只提供三种方块样式->2.0:添加旋转操作,提供4种基本样式
 * 1.0操作方式：左右方向键控制下落块的左右移动控制，下方向进行快速移动->2.0:添加上方向进行旋转控制
 * 1.0:通过填满最下面一行来消除一行,并积一分，顶部出到边界则游戏结束->2.0:不变
 * 1.0:要进行新一局游戏则需要重启->2.0:不变
 * 2.0:分离出独立的控制面板以方便控制与某些显示
 * 2.0:可以添加网格线提示
 * 2.0:添加难度控制
 * @author PC
 *
 */
public class TetrisGameGUI extends JFrame {
	public TetrisGameGUI() {
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
		JFrame frame=new JFrame("Tetris Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TetrisGamePanel(frame));
		frame.setResizable(false);
		frame.setSize(new java.awt.Dimension(WIDTH+7+100,HEIGHT+28));//适应windows下的500*500的显示大小
		frame.setVisible(true);
	}

}
