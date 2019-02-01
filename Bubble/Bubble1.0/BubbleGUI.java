import javax.swing.JFrame;
/**
 * 单线程式的简单的泡泡交互游戏
 * 点击或点击拖拽绘制泡泡，鼠标滚轮改变泡泡绘制泡泡的大小
 * 可以改变泡泡移动速度，暂停界面，清除泡泡
 * 泡泡到达边界时反弹
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
