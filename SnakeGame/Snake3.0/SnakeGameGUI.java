import javax.swing.JFrame;

/**
 * ̰����3.0
 * ��̰����2.0�Ļ���������һЩ�Ľ�
 * ������ʽ������ڰ���ͬ�ƶ�������ͬ�İ���ʱ���м���Ч��
 * ���ͨ��ϵͳ��ͨ���������ʾ������ʾ��ǰ����/�����Լ�Ŀ�곤��/�������ﵽĿ��ʱ����ѡ�������һ��
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
		frame.setSize(new java.awt.Dimension(WIDTH+107,HEIGHT+28));//��Ӧwindows�µ�500*500����ʾ��С
		frame.setVisible(true);
	}

}
