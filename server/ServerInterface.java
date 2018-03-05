package socket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

//服务端界面
public class ServerInterface extends JFrame {

	private static final long serialVersionUID = -1812082811419894631L;

	JTextArea outputArea; // 唯一一个组件，输出各种连接信息
	PokedexServer server;

	public ServerInterface() {
		// 构造函数中对界面进行设置，并调用Operation类中方法开始运行服务器
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setBackground(Color.BLACK);
		outputArea.setFont(new Font("黑体", Font.PLAIN, 13));
		outputArea.setForeground(Color.PINK);
		outputArea.setLineWrap(true);

		// 匿名类处理窗口关闭事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		add(outputArea);
		setTitle("服务器终端");
		setBounds(800, 200, 400, 400);
		setVisible(true);

		server = new PokedexServer(this);
	}
	
	public static void main(String[] args){
		new ServerInterface();
	}
}
