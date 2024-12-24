package test;

import java.net.*;
import java.io.*;

public class ClientTest {

	// 此类用于与ServerTest类测试连接
	public static void main(String[] args) {

		try {
			// 1.创建客户端对象Socket, 构造方法绑定服务器的IP地址和端口号
			Socket socket = new Socket("127.0.0.1", 8888);

			// 2.建立网络字节流InputStream与OutputStream的对象
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// 3.向服务端发送测试信息
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
			bw.flush();

			// 4.读取服务器返回的消息
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String mess = br.readLine();
			System.out.println("服务器:" + mess);

			// 5.释放资源
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
