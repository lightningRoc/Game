import javax.swing.JFrame;

/**
 * ̰����2.0
 * ��̰����1.0�Ļ���������һЩ�Ľ��Լ����һЩ����
 * 1.0:ʵ��̰���ߵĻ�������->2.0:�޸���
 * 1.0:������ʽ����������Ʒ��򣬿ո������ͣ->2.0:����Ѷ�ѡ��
 * 1.0:ͨ���Զ��Ӳ��ϱ䳤��������Ļ��Ե�����Լ�����������Ϸ����->2.0:ȥ����Ļ��Ե����ǽ,�ǵ��߿����ڵ���һ�ߵı�Եʱ��������һ��
 * �ı�Ե����,ͬʱ����ʵ���ǽ��,��������ǽ�ڻ��Լ�������ʱ��Ϸ����
 * 1.0:Ҫ������һ����Ϸ����Ҫ����->����һ����Ϸ�������ؿ�һ�ֵ�����
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
		frame.setSize(new java.awt.Dimension(WIDTH+7,HEIGHT+28));//��Ӧwindows�µ�500*500����ʾ��С
		frame.setVisible(true);
	}

}
