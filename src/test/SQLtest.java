package test;

import java.sql.*;

public class SQLtest { // 该类用于测试数据库连接

	public static void main(String[] args) {

		Connection connection;
		Statement statement;
		ResultSet resultSet;

		// 加载数据库驱动类
		String driverName = "com.mysql.cj.jdbc.Driver"; // 新版本写法cj.
		// 声明数据库的URL
		String url = "jdbc:mysql://localhost:3306/document"; // 新版本显式关闭useSSL，添加时区

		// 数据库用户
		String user = "root";                         
		String password = "3270294673GWJ";

		try {

			// 1. 加载驱动
			Class.forName(driverName);
			// 2. 建立数据库连接
			connection = DriverManager.getConnection(url, user, password);
			// 3. 创建语句对象
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// 4. 执行SQL语句
			String sql = "select * from user_info ";
			// 5. 处理对象
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String username = resultSet.getString("UserName");
				String pwd = resultSet.getString("Password");
				String role = resultSet.getString("Role");
				System.out.println(username + ";" + pwd + ";" + role);
			}
			// 6. 释放资源
			resultSet.close();
			statement.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println("数据驱动错误");
		} catch (SQLException e) {
			System.out.println("数据库错误");
		}

	}

}
