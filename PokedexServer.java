package socket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//服务端逻辑层
class PokedexServer {

	ServerInterface interface1;

	PokedexManager pokedex; // 管理图鉴的类

	ServerSocket server;
	Socket client;
	private static int num = 1; // 查找次数计数

	public PokedexServer(ServerInterface s) {
		interface1 = s;
		scan();
		startServer();
	}

	// 读取图鉴文件的方法，构造PokedexManager类对象
	public void scan() {
		try {
			pokedex = new PokedexManager(new FileScanner().start());
			interface1.outputArea.setText("图鉴文件读取成功" + '\n');
		} catch (FileNotFoundException e) {
			interface1.outputArea.setText("找不到图鉴文件" + '\n');
		}
	}

	// 开启服务器，接受请求的方法
	public void startServer() {
		while (true) {
			try {
				server = new ServerSocket(1025);
			} catch (IOException e) {
				System.out.println("服务器开启失败");
				System.exit(-1);
			}

			try {
				client = server.accept();
				new ServerThread(client).start();
			} catch (IOException e) {
				System.out.println("接受请求失败");
			}

			try {
				server.close();
			} catch (IOException e) {
				System.out.println("服务器开启失败");
			}
		}
	}

	// 服务器的线程类
	private class ServerThread extends Thread {
		private Socket client;
		private BufferedReader is;
		private PrintWriter os;
		private String inputString;
		private String clientIP;

		public ServerThread(Socket cli) throws IOException {
			client = cli;
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			os = new PrintWriter(client.getOutputStream());
			// 先向客户端输出服务器端的图鉴信息
			os.println("服务端图鉴现包含全国图鉴#XXX-XXX");
			os.println("本图鉴仅支持官方简体中文译名查找");
			os.println("end"); // 输出结束的标记，下同
			os.flush();
		}
		
		//线程运行主函数
		public void run() {
			clientIP = client.getInetAddress().toString();
			try {
				inputString = is.readLine();
				// 在接受到“quit”语句前保持连接
				while (!inputString.equals("quit")) {
					search(inputString);
					inputString = is.readLine();
				}
				is.close();
				os.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 负责对需要搜索内容的分析
		public void search(String str) {
			Pokemon tmp = new Pokemon();
			boolean found = false; // 表示查找成功或失败
			// 针对格式“#+编号”
			if (str.charAt(0) == '#' && str.length() > 1 && Character.isDigit(str.charAt(1))) {
				Integer id = Integer.parseInt(str.substring(1));
				found = pokedex.search(id, tmp);
			}
			// 针对搜索pm全国图鉴编号
			else if (Character.isDigit(str.charAt(0))) {
				Integer id = Integer.parseInt(str);
				found = pokedex.search(id, tmp);
			}
			// 针对搜索pm名字
			else
				found = pokedex.search(str, tmp);

			print(found, tmp);
		}

		// 输出搜索信息
		public void print(boolean found, Pokemon pm) {
			// 查找到该pm
			if (found) {
				os.println("全国图鉴编号：" + pm.getID());
				os.println(pm.getName());
				os.println("属性：" + pm.getType());
				if (pm.getEvoluteWay().equals("0"))
					os.println(pm.getEvolution());
				else
					os.println(pm.getEvolution() + " " + pm.getEvoluteWay());
				if (!pm.getNextEvolution().equals("无"))
					os.println("可进化为" + pm.getNextEvolution());
				os.println("end");
				os.flush();
				// 在服务器界面上输出查找结果
				interface1.outputArea
						.append(new Integer(num++).toString() + ".IP:" + clientIP + " 查找" + inputString + " 成功" + '\n');
			}
			// 未能找到该pm
			else {
				os.println("图鉴中暂无该pm");
				os.println("end");
				os.flush();
				interface1.outputArea
						.append(new Integer(num++).toString() + ".IP:" + clientIP + " 查找" + inputString + " 失败" + '\n');
			}
		}
	}
}
