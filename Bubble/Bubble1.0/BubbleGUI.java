import javax.swing.JFrame;
/**
 * ���߳�ʽ�ļ򵥵����ݽ�����Ϸ
 * ���������ק�������ݣ������ָı����ݻ������ݵĴ�С
 * ���Ըı������ƶ��ٶȣ���ͣ���棬�������
 * ���ݵ���߽�ʱ����
 * @author PC
 *
 */
public class BubbleGUI extends JFrame {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame("BubbleDraw GUI App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BubblePanel());
		frame.setSize(new java.awt.Dimension(500,500));
		frame.setVisible(true);
	}

}
