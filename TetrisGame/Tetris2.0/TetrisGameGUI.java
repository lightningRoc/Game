import java.awt.Container;

import javax.swing.JFrame;

/**
 * ����˹����2.0
 * 1.0:ʵ�ֶ���˹����ļ�ģ��,���޿���ת����,����ʱֻ�ṩ���ַ�����ʽ->2.0:�����ת����,�ṩ4�ֻ�����ʽ
 * 1.0������ʽ�����ҷ�������������������ƶ����ƣ��·�����п����ƶ�->2.0:����Ϸ��������ת����
 * 1.0:ͨ������������һ��������һ��,����һ�֣����������߽�����Ϸ����->2.0:����
 * 1.0:Ҫ������һ����Ϸ����Ҫ����->2.0:����
 * 2.0:����������Ŀ�������Է��������ĳЩ��ʾ
 * 2.0:���������������ʾ
 * 2.0:����Ѷȿ���
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
		frame.setSize(new java.awt.Dimension(WIDTH+7+100,HEIGHT+28));//��Ӧwindows�µ�500*500����ʾ��С
		frame.setVisible(true);
	}

}
