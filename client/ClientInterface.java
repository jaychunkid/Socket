package socket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//客户端界面
public class ClientInterface extends JFrame {

	private static final long serialVersionUID = 8180953184848213451L;
	
	JSplitPane sp;
	JPanel controlPanel, searchPanel;
	BorderLayout bl;
	FlowLayout fl;
	JTextArea outputArea; // 输出信息的组件
	JTextField inputField; // 输入查找信息的组件
	JButton connect, search;
	JLabel jb; // 纯粹装饰用

	PokedexClient client;

	public ClientInterface() {
		// 构造函数中对界面进行设置
		sp = new JSplitPane();
		sp.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sp.setDividerLocation(300);
		add(sp);

		outputArea = new JTextArea();
		outputArea.setColumns(15);
		outputArea.setRows(19);
		outputArea.setLineWrap(true);
		outputArea.setEditable(false);
		outputArea.setFont(new Font("黑体", Font.PLAIN, 13));
		outputArea.setForeground(Color.WHITE);
		outputArea.setBackground(Color.GRAY);
		sp.setLeftComponent(outputArea);

		controlPanel = new JPanel();
		controlPanel.setBackground(Color.BLACK);
		sp.setRightComponent(controlPanel);

		bl = new BorderLayout();
		bl.setVgap(8);
		controlPanel.setLayout(bl);

		connect = new JButton("连接服务器");
		connect.setFont(new Font("黑体", Font.BOLD, 14));
		connect.setBorderPainted(false);
		connect.setBackground(Color.RED);
		connect.setForeground(Color.WHITE);
		connect.addActionListener(new ConnectButtonListener());

		searchPanel = new JPanel();
		searchPanel.setBackground(Color.BLACK);

		fl = new FlowLayout();
		fl.setHgap(10);
		searchPanel.setLayout(fl);

		inputField = new JTextField();
		inputField.setColumns(13);
		inputField.setEditable(false); // 在连接服务器前不可输入

		search = new JButton("查询");
		search.setFont(new Font("黑体", Font.BOLD, 14));
		search.setBorderPainted(false);
		search.setBackground(Color.RED);
		search.setForeground(Color.WHITE);
		search.addActionListener(new SearchButtonListener());

		searchPanel.add(inputField);
		searchPanel.add(search);

		jb = new JLabel("Pokedex");
		jb.setForeground(Color.white);
		jb.setHorizontalAlignment(JLabel.CENTER);

		controlPanel.add(connect, BorderLayout.NORTH);
		controlPanel.add(searchPanel, BorderLayout.CENTER);
		controlPanel.add(jb, BorderLayout.SOUTH);
		
		// 匿名类处理窗口关闭事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 如果已连接服务器，则先断开连接
				client.disconnect();
				System.exit(0);
			}
		});

		setBounds(400, 200, 250, 450);
		setTitle("口袋图鉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		client = new PokedexClient(this);
	}

	// 处理连接按钮的点击事件
	private class ConnectButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (connect.getActionCommand() == "连接服务器")
				client.connectServer();
			else
				client.disconnect();
		}
	}

	// 处理查找按钮的点击事件
	private class SearchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			client.search();
		}
	}
	
	public static void main(String[] args){
		new ClientInterface();
	}
}
