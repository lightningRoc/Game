import java.awt.Container;

import javax.swing.JFrame;

/**
 * ����˹����1.0
 * ʵ�ֶ���˹����ļ�ģ��,���޿���ת����,����ʱֻ�ṩ���ַ�����ʽ
 * ������ʽ�����ҷ�������������������ƶ����ƣ��·�����п����ƶ�
 * ͨ������������һ��������һ��,����һ�֣����������߽�����Ϸ����
 * Ҫ������һ����Ϸ����Ҫ����
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
		frame.setSize(new java.awt.Dimension(WIDTH+7,HEIGHT+28));//��Ӧwindows�µ�500*500����ʾ��С
		frame.setVisible(true);
	}

}
