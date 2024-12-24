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
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;

	private JMenu UserManager_Menu;
	private JMenu FileManager_Menu;
	private JMenu SelfInfo_Menu;
	private JMenu Others_Menu;

	private JButton AddUser_Button;
	private JButton DelUser_Button;
	private JButton UpdateUser_Button;
	private JButton UploadFile_Button;
	private JButton DownloadFile_Button;
	private JButton ChangeSelfInfo_Button;
	private JButton Exit_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 实验6无法实现单独调用界面, 不能实现用户与服务器的消息传递！
					// 请从登录界面开始跳转

					// 单独调用连接数据库
					try {
						SQLconnection.Connect();
					} catch (ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
					}

					// 单独启动默认管理者
					MainFrame frame = new MainFrame(new Administrator("kate", "123", "Administrator"));
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
	public MainFrame(User user) {
		// 传入角色参数
		// 框架
		setResizable(false);
		// 根据角色设置标题
		SetTitle(user.getRole());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1171, 699);

		// 中间容器
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 菜单栏
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1165, 33);
		contentPane.add(menuBar);

		// 用户管理下拉菜单
		UserManager_Menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		UserManager_Menu.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(UserManager_Menu);

		// 增添用户按钮
		AddUser_Button = new JButton("\u589E\u6DFB\u7528\u6237");
		AddUser_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 增添用户事件
				AddUserActionPerformed(user, e);
			}
		});
		AddUser_Button.setFont(new Font("黑体", Font.PLAIN, 16));
		UserManager_Menu.add(AddUser_Button);

		// 删除用户按钮
		DelUser_Button = new JButton("\u5220\u9664\u7528\u6237");
		DelUser_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 删除用户事件
				DelUserActionPerformed(user, e);
			}
		});
		DelUser_Button.setFont(new Font("黑体", Font.PLAIN, 16));
		UserManager_Menu.add(DelUser_Button);

		// 修改用户按钮
		UpdateUser_Button = new JButton("\u4FEE\u6539\u7528\u6237");
		UpdateUser_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 修改用户事件
				UpdateUserActionPerformed(user, e);
			}
		});
		UpdateUser_Button.setFont(new Font("黑体", Font.PLAIN, 16));
		UserManager_Menu.add(UpdateUser_Button);

		// 档案管理下拉菜单
		FileManager_Menu = new JMenu("\u6863\u6848\u7BA1\u7406");
		FileManager_Menu.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(FileManager_Menu);

		// 上传文件按钮
		UploadFile_Button = new JButton("\u4E0A\u4F20\u6587\u4EF6");
		UploadFile_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 上传文件事件
				UploadFileActionPerformed(user, e);
			}
		});
		UploadFile_Button.setFont(new Font("黑体", Font.PLAIN, 16));
		FileManager_Menu.add(UploadFile_Button);

		// 下载文件按钮
		DownloadFile_Button = new JButton("\u4E0B\u8F7D\u6587\u4EF6");
		DownloadFile_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 下载文件事件
				DownloadFileActionPerformed(user, e);
			}
		});
		DownloadFile_Button.setFont(new Font("黑体", Font.PLAIN, 16));
		FileManager_Menu.add(DownloadFile_Button);

		// 个人信息管理下拉菜单
		SelfInfo_Menu = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		SelfInfo_Menu.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(SelfInfo_Menu);

		// 修改密码按钮
		ChangeSelfInfo_Button = new JButton("\u5BC6\u7801\u4FEE\u6539");
		ChangeSelfInfo_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 修改密码事件
				ChangeSelfActionPerformed(user, e);
			}
		});
		ChangeSelfInfo_Button.setFont(new Font("黑体", Font.PLAIN, 16));
		SelfInfo_Menu.add(ChangeSelfInfo_Button);

		// 其他类下拉菜单
		Others_Menu = new JMenu("\u5176\u4ED6");
		Others_Menu.setFont(new Font("黑体", Font.PLAIN, 18));
		menuBar.add(Others_Menu);

		// 退出按钮
		Exit_Button = new JButton("\u9000\u51FA");
		Exit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 退出事件
				ExitActionPerformed(e);
			}
		});
		Exit_Button.setFont(new Font("黑体", Font.PLAIN, 16));
		Others_Menu.add(Exit_Button);

		// 设置各按钮权限
		SetRights(user.getRole());
	}

	// 增添用户
	private void AddUserActionPerformed(User user, ActionEvent evt) {
		// 选项编号 0
		UserFrame userframe = new UserFrame(user, 0);
		userframe.setVisible(true);
	}

	// 删除用户
	private void DelUserActionPerformed(User user, ActionEvent evt) {
		// 选项编号 1
		UserFrame userframe = new UserFrame(user, 1);
		userframe.setVisible(true);
	}

	// 修改用户
	private void UpdateUserActionPerformed(User user, ActionEvent evt) {
		// 选项编号 2
		UserFrame userframe = new UserFrame(user, 2);
		userframe.setVisible(true);
	}

	// 上传文件
	private void UploadFileActionPerformed(User user, ActionEvent evt) {
		// 选项编号0
		FileFrame fileframe = new FileFrame(user, 0);
		fileframe.setVisible(true);
	}

	// 下载文件
	private void DownloadFileActionPerformed(User user, ActionEvent evt) {
		// 选项编号1
		FileFrame fileframe = new FileFrame(user, 1);
		fileframe.setVisible(true);
	}

	// 修改密码
	private void ChangeSelfActionPerformed(User user, ActionEvent evt) {
		SelfInfoFrame selfframe = new SelfInfoFrame(user);
		selfframe.setVisible(true);
	}

	// 设置标题
	private void SetTitle(String role) {
		if (role.equalsIgnoreCase("administrator")) {
			setTitle("档案管理员界面");
		} else if (role.equalsIgnoreCase("browser")) {
			setTitle("档案浏览员界面");
		} else if (role.equalsIgnoreCase("operator")) {
			setTitle("档案录入员界面");
		}
	}

	// 设置用户权限
	private void SetRights(String role) {

		if (role.equalsIgnoreCase("administrator")) {

			AddUser_Button.setEnabled(true);
			DelUser_Button.setEnabled(true);
			UpdateUser_Button.setEnabled(true);
			DownloadFile_Button.setEnabled(true);
			UploadFile_Button.setEnabled(false);
			ChangeSelfInfo_Button.setEnabled(true);
			Exit_Button.setEnabled(true);

		} else if (role.equalsIgnoreCase("browser")) {

			AddUser_Button.setEnabled(false);
			DelUser_Button.setEnabled(false);
			UpdateUser_Button.setEnabled(false);
			DownloadFile_Button.setEnabled(true);
			UploadFile_Button.setEnabled(false);
			ChangeSelfInfo_Button.setEnabled(true);
			Exit_Button.setEnabled(true);

		} else if (role.equalsIgnoreCase("operator")) {

			AddUser_Button.setEnabled(false);
			DelUser_Button.setEnabled(false);
			UpdateUser_Button.setEnabled(false);
			DownloadFile_Button.setEnabled(true);
			UploadFile_Button.setEnabled(true);
			ChangeSelfInfo_Button.setEnabled(true);
			Exit_Button.setEnabled(true);
		}
	}

	// 退出
	private void ExitActionPerformed(ActionEvent evt) {

		// 断开数据库连接
		try {
			SQLconnection.Disconnect();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		// 发送登出消息
		try {
			Client.SendMessage("登出");
			// 最后再接收服务器登出消息
			Client.ReceiveMessage();
			Client.CloseConnection();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
			return;
		} finally {
			// 在finally里返回登录界面，无论是否断开服务器连接都能返回
			this.dispose();
			LoginFrame loginframe = new LoginFrame();
			loginframe.setVisible(true);
		}

	}

}
