package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CS.Client;

import java.util.Enumeration;
import common.*;

@SuppressWarnings("serial")
public class UserFrame extends JFrame {

	// 中间容器
	private JPanel contentPane;
	// 多页面容器
	private JTabbedPane tabbedPane;

	// 增添用户页面及组件
	private JPanel AddUser_Panel;
	private JLabel Username_Label1;
	private JLabel Password_Label1;
	private JLabel Role_Label1;
	private JTextField Username_Txt1;
	private JPasswordField Password_Txt1;
	@SuppressWarnings("rawtypes")
	private JComboBox Role_ComboBox1;
	private JButton Confirm_Button1;
	private JButton Return_Button1;

	// 删除用户页面及组件
	private JPanel DelUser_Panel;
	private JScrollPane scrollPane;
	private JTable Users_table;
	private JButton Confirm_Button2;
	private JButton Return_Button2;

	// 修改用户页面及组件
	private JPanel UpdateUser_Panel;
	private JLabel Username_Label2;
	private JLabel Password_Label2;
	private JLabel Role_Label2;
	private JTextField Username_Txt2;
	private JPasswordField Password_Txt2;
	@SuppressWarnings("rawtypes")
	private JComboBox Role_ComboBox2;
	private JButton Confirm_Button3;
	private JButton Return_Button3;

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

					UserFrame frame = new UserFrame(new Administrator("kate", "123", "Administrator"), 0);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserFrame(User user, int choice) {
		// 传入用户及页面选项: 0 增添用户 1 删除用户 2 修改用户
		// 框架
		setResizable(false);
		setTitle("\u7528\u6237\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 441);

		// 中间容器
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 多页面容器
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(14, 49, 553, 309);
		contentPane.add(tabbedPane);

		// 添加用户页面
		AddUser_Panel = new JPanel();
		// 增添选项卡
		tabbedPane.addTab("\u65B0\u589E\u7528\u6237", null, AddUser_Panel, null);
		AddUser_Panel.setLayout(null);

		// 用户名标签
		Username_Label1 = new JLabel("\u7528\u6237\u540D");
		Username_Label1.setFont(new Font("黑体", Font.PLAIN, 18));
		Username_Label1.setBounds(114, 38, 61, 32);
		AddUser_Panel.add(Username_Label1);

		// 密码标签
		Password_Label1 = new JLabel("\u5BC6\u7801");
		Password_Label1.setFont(new Font("黑体", Font.PLAIN, 18));
		Password_Label1.setBounds(132, 93, 43, 32);
		AddUser_Panel.add(Password_Label1);

		// 角色标签
		Role_Label1 = new JLabel("\u89D2\u8272");
		Role_Label1.setFont(new Font("黑体", Font.PLAIN, 18));
		Role_Label1.setBounds(132, 150, 43, 32);
		AddUser_Panel.add(Role_Label1);

		// 用户名文本域
		Username_Txt1 = new JTextField();
		Username_Txt1.setBounds(197, 44, 181, 24);
		Username_Txt1.setColumns(10);
		AddUser_Panel.add(Username_Txt1);

		// 密码文本域
		Password_Txt1 = new JPasswordField();
		Password_Txt1.setBounds(197, 99, 181, 24);
		AddUser_Panel.add(Password_Txt1);

		// 角色选项栏
		Role_ComboBox1 = new JComboBox();
		Role_ComboBox1.setEditable(true);
		Role_ComboBox1.setModel(new DefaultComboBoxModel(new String[] { "", "Administrator", "Browser", "Operator" }));
		Role_ComboBox1.setBounds(197, 156, 181, 24);
		AddUser_Panel.add(Role_ComboBox1);

		// 增添按钮
		Confirm_Button1 = new JButton("\u589E\u6DFB");
		Confirm_Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 增添用户事件
				AddUserActionPerformed(user, e);
			}
		});
		Confirm_Button1.setFont(new Font("黑体", Font.PLAIN, 18));
		Confirm_Button1.setBounds(132, 222, 113, 27);
		AddUser_Panel.add(Confirm_Button1);

		// 返回按钮
		Return_Button1 = new JButton("\u8FD4\u56DE");
		Return_Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回事件
				ReturnActionPerformed(e);
			}
		});
		Return_Button1.setFont(new Font("黑体", Font.PLAIN, 18));
		Return_Button1.setBounds(329, 222, 113, 27);
		AddUser_Panel.add(Return_Button1);

		// 删除用户页面
		DelUser_Panel = new JPanel();
		tabbedPane.addTab("\u5220\u9664\u7528\u6237", null, DelUser_Panel, null);
		DelUser_Panel.setLayout(null);

		// 删除按钮
		Confirm_Button2 = new JButton("\u5220\u9664");
		Confirm_Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 删除事件
				DelUserActionPerformed(user, e);
			}
		});
		Confirm_Button2.setBounds(132, 222, 113, 27);
		Confirm_Button2.setFont(new Font("黑体", Font.PLAIN, 18));
		DelUser_Panel.add(Confirm_Button2);

		// 返回按钮
		Return_Button2 = new JButton("\u8FD4\u56DE");
		Return_Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回事件
				ReturnActionPerformed(e);
			}
		});
		Return_Button2.setBounds(329, 222, 113, 27);
		Return_Button2.setFont(new Font("黑体", Font.PLAIN, 18));
		DelUser_Panel.add(Return_Button2);

		// 可下拉容器域
		scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 37, 432, 159);
		DelUser_Panel.add(scrollPane);

		// 用户列表
		Users_table = new JTable();
		// 构造表格
		ConstructUserTable();
		// 加入可下拉区域
		scrollPane.setViewportView(Users_table);

		// 修改用户页面
		UpdateUser_Panel = new JPanel();
		UpdateUser_Panel.setLayout(null);
		tabbedPane.addTab("\u4FEE\u6539\u7528\u6237", null, UpdateUser_Panel, null);

		// 用户名标签
		Username_Label2 = new JLabel("\u7528\u6237\u540D");
		Username_Label2.setFont(new Font("黑体", Font.PLAIN, 18));
		Username_Label2.setBounds(114, 38, 61, 32);
		UpdateUser_Panel.add(Username_Label2);

		// 密码标签
		Password_Label2 = new JLabel("\u5BC6\u7801");
		Password_Label2.setFont(new Font("黑体", Font.PLAIN, 18));
		Password_Label2.setBounds(132, 93, 43, 32);
		UpdateUser_Panel.add(Password_Label2);

		// 角色标签
		Role_Label2 = new JLabel("\u89D2\u8272");
		Role_Label2.setFont(new Font("黑体", Font.PLAIN, 18));
		Role_Label2.setBounds(132, 150, 43, 32);
		UpdateUser_Panel.add(Role_Label2);

		// 用户名文本域
		Username_Txt2 = new JTextField();
		Username_Txt2.setColumns(10);
		Username_Txt2.setBounds(197, 44, 181, 24);
		UpdateUser_Panel.add(Username_Txt2);

		// 密码文本域
		Password_Txt2 = new JPasswordField();
		Password_Txt2.setBounds(197, 99, 181, 24);
		UpdateUser_Panel.add(Password_Txt2);

		// 角色选项栏
		Role_ComboBox2 = new JComboBox();
		Role_ComboBox2.setModel(new DefaultComboBoxModel(new String[] { "", "Administrator", "Browser", "Operator" }));
		Role_ComboBox2.setEditable(true);
		Role_ComboBox2.setBounds(197, 156, 181, 24);
		UpdateUser_Panel.add(Role_ComboBox2);

		// 修改按钮
		Confirm_Button3 = new JButton("\u4FEE\u6539");
		Confirm_Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 修改用户事件
				UpdateUserActionPerformed(user, e);
			}
		});
		Confirm_Button3.setFont(new Font("黑体", Font.PLAIN, 18));
		Confirm_Button3.setBounds(132, 222, 113, 27);
		UpdateUser_Panel.add(Confirm_Button3);

		// 返回按钮
		Return_Button3 = new JButton("\u8FD4\u56DE");
		Return_Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回事件
				ReturnActionPerformed(e);
			}
		});
		Return_Button3.setFont(new Font("黑体", Font.PLAIN, 18));
		Return_Button3.setBounds(329, 222, 113, 27);
		UpdateUser_Panel.add(Return_Button3);

		// 设置页面
		SetPane(choice);
	}

	// 用户表格构造
	private void ConstructUserTable() {

		// 表头数据
		String[] columnNames = { "\u7528\u6237\u540D", "\u5BC6\u7801", "\u89D2\u8272" };
		// 表格数据
		String[][] rowData = new String[20][3];

		Enumeration<User> u;
		try {
			// 获取哈希表信息
			u = DataProcessing.getAllUser();
			// 行数
			int row = 0;
			// 将哈希表信息导入至表格数据
			while (u.hasMoreElements()) {
				User user = u.nextElement();
				rowData[row][0] = user.getName();
				rowData[row][1] = user.getPassword();
				rowData[row][2] = user.getRole();
				row++;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		// 构造表格
		Users_table.setModel(new DefaultTableModel(rowData, columnNames) {

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});

	}

	// 增添
	private synchronized void AddUserActionPerformed(User user, ActionEvent evt) {

		// 获取输入内容
		String username = this.Username_Txt1.getText();
		String password = new String(this.Password_Txt1.getPassword());
		String role = (String) this.Role_ComboBox1.getSelectedItem();

		if (StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "未输入用户名！");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "未输入密码！");
			return;
		}
		if (StringUtil.isEmpty(role)) {
			JOptionPane.showMessageDialog(null, "未选择身份！");
			return;
		}

		try {
			if (DataProcessing.insertUser(username, password, role)) {

				// 发送增添用户的消息
				try {
					Client.SendMessage("增添用户");
					Client.ReceiveMessage();
				} catch (IOException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
					return;
				}

				// 更新表格数据
				ConstructUserTable();
				JOptionPane.showMessageDialog(null, "添加成功！");
				return;

			} else {
				JOptionPane.showMessageDialog(null, "添加失败！用户名已存在！");
				return;
			}

		} catch (HeadlessException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

	}

	// 删除
	private synchronized void DelUserActionPerformed(User user, ActionEvent evt) {

		// 获取所选行序号,若未选择其值为-1
		int selectedrow = Users_table.getSelectedRow();

		// 未选择用户的情况
		if (selectedrow == -1) {
			JOptionPane.showMessageDialog(null, "未选择用户！");
			return;
		} else {

			// 获取所选行的用户名
			String username = (String) Users_table.getValueAt(selectedrow, 0);
			// 若选择空行
			if (StringUtil.isEmpty(username)) {
				return;
			}
			// 选择自身用户的情况
			if (username.equals(user.getName())) {
				JOptionPane.showMessageDialog(null, "不能删除自身用户！");
				return;
			}

			// 显示确认界面: 信息, 标题, 选项个数
			int value = JOptionPane.showConfirmDialog(null, "确定要删除用户吗？", "用户删除确认界面", 2);
			// Yes=0 No=1
			if (value == 0) {
				try {
					if (DataProcessing.deleteUser(username)) {

						// 发送删除用户消息
						try {
							Client.SendMessage("删除用户");
							Client.ReceiveMessage();
						} catch (IOException | ClassNotFoundException e) {
							JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
							return;
						}

						// 更新表格数据
						ConstructUserTable();
						JOptionPane.showMessageDialog(null, "删除成功！");
						return;

					} else {
						JOptionPane.showMessageDialog(null, "删除失败！");
						return;
					}

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
				}

			} else if (value == 1) {
				return;
			}
		}
	}

	// 修改
	private synchronized void UpdateUserActionPerformed(User user, ActionEvent evt) {

		String username = this.Username_Txt2.getText();
		String password = new String(this.Password_Txt2.getPassword());
		String role = (String) this.Role_ComboBox2.getSelectedItem();

		if (StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "未输入用户名！");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "未输入密码！");
			return;
		}
		if (StringUtil.isEmpty(role)) {
			JOptionPane.showMessageDialog(null, "未选择身份！");
			return;
		}

		try {

			if (DataProcessing.searchUser(username, password) == null) {
				JOptionPane.showMessageDialog(null, "用户名与密码不匹配！");
				return;
			} else {
				// 显示确认界面：信息，标题，选项个数
				int value = JOptionPane.showConfirmDialog(null, "确定要修改信息吗？", "信息修改确认界面", 2);
				// Yes=0 No=1
				if (value == 0) {
					if (DataProcessing.updateUser(username, password, role)) {

						// 发送修改用户信息
						try {
							Client.SendMessage("修改用户");
							Client.ReceiveMessage();
						} catch (IOException | ClassNotFoundException e) {
							JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
							return;
						}

						// 更新表格数据
						ConstructUserTable();
						JOptionPane.showMessageDialog(null, "修改成功！");
						return;

					} else {
						JOptionPane.showMessageDialog(null, "修改失败！");
						return;
					}

				} else if (value == 1) {
					return;
				}
			}

		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}
	}

	// 设置页面
	private void SetPane(int value) {
		if (value == 0) {
			tabbedPane.setSelectedComponent(AddUser_Panel);
		} else if (value == 1) {
			tabbedPane.setSelectedComponent(DelUser_Panel);
		} else if (value == 2) {
			tabbedPane.setSelectedComponent(UpdateUser_Panel);
		}
	}

	// 返回
	private void ReturnActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
