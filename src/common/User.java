package common;

import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class User {

	private String name;
	private String password;
	private String role;

	String uploadpath = "C:\\Users\\guang\\Desktop\\experr\\Uploads\\";
	String downloadpath = "C:\\Users\\guang\\Desktop\\experr\\Downloads\\";

	User(String name, String password, String role) {
		this.setName(name);
		this.setPassword(password);
		this.setRole(role);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return "Name: " + this.name + " Password: " + this.password + " Role: " + this.role;
	}

	public boolean downloadFile(String fileID) {

		Doc doc;

		try {

			// 获取哈希表信息
			doc = DataProcessing.searchDoc(fileID);
			// 输入文件对象
			File input_file = new File(uploadpath + doc.getFilename());
			// 输入过滤器流,建立在文件流上
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(input_file));

			// 输出文件对象
			File output_file = new File(downloadpath + doc.getFilename());
			// 创建文件
			output_file.createNewFile();
			// 输出过滤器流,建立在文件流上
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(output_file));

			// 用字节数组存取数据
			byte[] bytes = new byte[1024];
			// 文件写入操作
			int length = 0;
			while ((length = input.read(bytes)) != -1) {
				output.write(bytes, 0, length);
			}

			// 关闭流
			input.close();
			output.close();
			
			return true;

		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		return false;
	}

	public boolean uploadFile(String fileID, String filepath, String filedescription) {

		// 输入文件对象
		File input_file = new File(filepath);
		// 获取文件名
		String filename = input_file.getName();
		// 获取当前时间
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		try {

			if (DataProcessing.insertDoc(fileID, this.getName(), timestamp, filedescription, filename)) {

				// 输入过滤器流,建立在文件流上
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(input_file));

				// 输出文件对象
				File output_file = new File(uploadpath + input_file.getName());
				// 创建文件
				output_file.createNewFile();
				// 输出过滤器流,建立在文件流上
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(output_file));

				// 用字节数组存取数据
				byte[] bytes = new byte[1024];
				// 文件写入操作
				int length = 0;
				while ((length = input.read(bytes)) != -1) {
					output.write(bytes, 0, length);
				}

				// 关闭流
				input.close();
				output.close();

				return true;

			} else
				return false;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		return false;
	}

	// 修改密码
	public boolean changeSelfInfo(String password) {

		try {
			if (DataProcessing.updateUser(name, password, role)) {
				this.password = password;
				return true;
			} else
				return false;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		return false;
	}

}
