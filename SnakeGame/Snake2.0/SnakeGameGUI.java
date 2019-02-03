import javax.swing.JFrame;

/**
 * 贪吃蛇2.0
 * 在贪吃蛇1.0的基础上增加一些改进以及添加一些规则
 * 1.0:实现贪吃蛇的基本操作->2.0:无更改
 * 1.0:操作方式：方向键控制方向，空格控制暂停->2.0:添加难度选择
 * 1.0:通过吃豆子不断变长，碰到屏幕边缘或蛇自己的身体则游戏结束->2.0:去除屏幕边缘空气墙,是得蛇可以在到达一边的边缘时可以在另一边
 * 的边缘出现,同时增加实体的墙壁,在蛇碰到墙壁或自己的身体时游戏结束
 * 1.0:要进行新一局游戏则需要重启->增加一局游戏结束后重开一局的能力
 * @author PC
 *
 */
public class SnakeGameGUI extends JFrame {
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
