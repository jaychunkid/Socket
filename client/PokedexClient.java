package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//客户端逻辑层
class PokedexClient {

	ClientInterface interface1;

	BufferedReader is;
	PrintWriter os;
	Socket server;

	public PokedexClient(ClientInterface c) {
		interface1 = c;
	}

	// 连接服务器方法
	public void connectServer() {
		try {
			server = new Socket(InetAddress.getByName(null), 1025);
			is = new BufferedReader(new InputStreamReader(server.getInputStream()));
			os = new PrintWriter(server.getOutputStream());
			interface1.outputArea.setText("连接服务器成功" + '\n');
			String str = is.readLine();
			while (!str.equals("end")) {
				interface1.outputArea.append(str + '\n');
				str = is.readLine();
			}
			interface1.outputArea.append("\n");
			interface1.connect.setText("断开连接");
			interface1.inputField.setEditable(true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			interface1.outputArea.setText("连接服务器失败");
		} catch (IOException e) {
			e.printStackTrace();
			interface1.outputArea.setText("连接服务器失败");
		}
	}

	// 断开连接方法
	public void disconnect() {
		if (server != null) {
			try {
				os.println("quit");
				os.flush();
				is.close();
				os.close();
				server.close();
				interface1.inputField.setEditable(false);
				interface1.outputArea.append("断开连接成功" + '\n');
				interface1.connect.setText("连接服务器");
				interface1.inputField.setEditable(false);
			} catch (IOException e) {
				e.printStackTrace();
				interface1.outputArea.setText("断开连接失败" + '\n');
			}
		}

	}

	// 查询图鉴方法
	public void search() {
		if (interface1.connect.getActionCommand() == "连接服务器")
			interface1.outputArea.setText("请先连接服务器" + '\n');
		else {
			String inputString = interface1.inputField.getText();
			if (inputString.equals(""))
				interface1.outputArea.setText("请先输入需要查找的Pokemon名字" + '\n');
			else {

				os.println(inputString);
				os.flush();
				try {
					String str = is.readLine();
					interface1.outputArea.setText("");
					while (!str.equals("end")) {
						interface1.outputArea.append(str + '\n');
						str = is.readLine();
					}
					interface1.outputArea.append("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
