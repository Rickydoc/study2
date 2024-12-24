package test;

import java.io.*;
import java.net.*;

public class ServerTest {

	// 此类用于与ClientTest类测试连接
	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		try {
			// 1.创建服务端对象serverSocket和系统指定端口号
			ServerSocket serversocket = new ServerSocket(8888);
			System.out.println("启动服务器....");

			// 2.使用ServerSocket对象中的方法accept, 获取收到请求的客户端对象Socket
			Socket socket = serversocket.accept();
			System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");

			// 3.使用网络字节输入流读取客户端发送来的消息
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String mess = br.readLine();
			System.out.println("客户端:" + mess);

			// 4.使用网络字节输出流向客户端回写信息
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(mess + "\n");
			bw.flush();

			// 5.释放资源
			serversocket.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
