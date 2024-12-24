package common;

import java.sql.SQLException;

public class SQLconnection {

	// 加载数据库驱动类
	static String DriverName = "com.mysql.cj.jdbc.Driver"; // 新版本写法cj.
	// 声明数据库的URL
	static String Url = "jdbc:mysql://localhost:3306/document"; // 新版本显式关闭useSSL，添加时区

	// 数据库用户
	static String User = "root";
	static String Password = "3270294673GWJ";

	// 连接数据库函数
	public static void Connect() throws ClassNotFoundException, SQLException {
		DataProcessing.connectToDatabase(DriverName, Url, User, Password);
	}

	// 断开数据库函数
	public static void Disconnect() throws SQLException {
		DataProcessing.disconnectFromDatabase();
	}

}

