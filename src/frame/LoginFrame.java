package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import CS.Client;
import common.*;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JLabel Username_Label;
	private JLabel Password_Label;
	private JTextField Username_Txt;
	private JPasswordField Password_Txt;
	private JButton Confirm_Button;
	private JButton Cancel_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {

		// 框架
		setTitle("\u7CFB\u7EDF\u767B\u5F55");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 372);

		// 中间容器
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 用户名标签
		Username_Label = new JLabel("\u7528\u6237\u540D\uFF1A");
		Username_Label.setBounds(128, 89, 80, 40);
		Username_Label.setFont(new Font("黑体", Font.PLAIN, 20));

		// 密码标签
		Password_Label = new JLabel("\u5BC6\u7801\uFF1A");
		Password_Label.setBounds(148, 142, 60, 37);
		Password_Label.setFont(new Font("黑体", Font.PLAIN, 20));

		// 用户名文本域
		Username_Txt = new JTextField();
		Username_Txt.setBounds(222, 99, 176, 24);
		Username_Txt.setColumns(10);

		// 密码文本域
		Password_Txt = new JPasswordField();
		Password_Txt.setBounds(222, 150, 176, 24);

		// 确认按钮
		Confirm_Button = new JButton("\u786E\u5B9A");
		Confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 登录事件
				LoginInActionPerformed(e);
			}
		});
		Confirm_Button.setFont(new Font("黑体", Font.PLAIN, 20));
		Confirm_Button.setBounds(173, 216, 85, 27);

		// 取消按钮
		Cancel_Button = new JButton("\u53D6\u6D88");
		Cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 取消事件
				ResetValueActionPerformed(e);
			}
		});
		Cancel_Button.setFont(new Font("黑体", Font.PLAIN, 20));
		Cancel_Button.setBounds(313, 216, 85, 27);

		// 设置布局与添加部件
		// 绝对布局
		contentPane.setLayout(null);
		contentPane.add(Username_Label);
		contentPane.add(Password_Label);
		contentPane.add(Username_Txt);
		contentPane.add(Password_Txt);
		contentPane.add(Confirm_Button);
		contentPane.add(Cancel_Button);

		// 连接数据库
		// 应在构造函数中连接，不应在主函数连接，否则登出后无法连接
		try {
			SQLconnection.Connect();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

	}

	// 登录
	private synchronized void LoginInActionPerformed(ActionEvent evt) {

		String username = this.Username_Txt.getText();
		String password = new String(this.Password_Txt.getPassword()); // 获取输入内容

		if (StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "未输入用户名！"); // 显示对话框
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "未输入密码！"); // 显示对话框
			return;
		}

		try {
			if (DataProcessing.searchUser(username, password) == null) {
				JOptionPane.showMessageDialog(null, "用户名与密码不匹配！"); // 显示对话框
				return;
			} else {

				// 连接服务器，发送登录消息
				Client.ConnectToServer();
				Client.GetStreams();
				Client.SendMessage(username + "登录");
				Client.ReceiveMessage();

				// 导入用户
				User user = DataProcessing.searchUser(username, password);
				// 令当前界面消失
				this.dispose();
				// 跳转至主界面,新建对象并传入用户参数
				MainFrame mainframe = new MainFrame(user);
				mainframe.setVisible(true);
			}

		} catch (HeadlessException | SQLException | IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
			return;
		}

	}

	// 重置文本域
	private void ResetValueActionPerformed(ActionEvent evt) {
		// 设置为空
		this.Username_Txt.setText("");
		this.Password_Txt.setText("");
	}
}

