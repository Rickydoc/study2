package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CS.Client;

import java.text.SimpleDateFormat;
import java.util.Enumeration;
import common.*;

@SuppressWarnings("serial")
public class FileFrame extends JFrame {

	// 中间容器
	private JPanel contentPane;
	// 多页面容器
	private JTabbedPane tabbedPane;

	// 上传文件页面及组件
	private JPanel Upload_Panel;
	private JLabel FileID_Label;
	private JLabel Filedescription_Label;
	private JLabel Filename_Label;
	private JTextField FileID_Txt;
	private JTextArea Filedescription_Txt;
	private JTextField Filepath_Txt;
	private JButton Upload_Button;
	private JButton OpenFile_Button;
	private JButton Return_Button1;

	// 下载文件页面及组件
	private JPanel Download_Panel;
	private JButton Download_Button;
	private JButton Return_Button2;
	private JScrollPane scrollPane;
	private JTable Files_table;

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

					FileFrame frame = new FileFrame(new Administrator("jack", "123", "operator"), 0);
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
	public FileFrame(User user, int choice) {
		// 传入用户及页面选项: 0上传文件 1下载文件
		// 框架
		setTitle("\u6587\u4EF6\u7BA1\u7406\u754C\u9762");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 581);

		// 中间容器
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 多页面容器
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(38, 35, 713, 469);
		contentPane.add(tabbedPane);

		// 上传页面
		Upload_Panel = new JPanel();
		tabbedPane.addTab("\u6587\u4EF6\u4E0A\u4F20", null, Upload_Panel, null);
		Upload_Panel.setLayout(null);

		// 档案号标签
		FileID_Label = new JLabel("\u6863\u6848\u53F7");
		FileID_Label.setFont(new Font("黑体", Font.PLAIN, 20));
		FileID_Label.setBounds(125, 33, 60, 36);
		Upload_Panel.add(FileID_Label);

		// 文件描述标签
		Filedescription_Label = new JLabel("\u6863\u6848\u63CF\u8FF0");
		Filedescription_Label.setFont(new Font("黑体", Font.PLAIN, 20));
		Filedescription_Label.setBounds(105, 90, 80, 36);
		Upload_Panel.add(Filedescription_Label);

		// 文件名标签
		Filename_Label = new JLabel("\u6863\u6848\u6587\u4EF6\u540D");
		Filename_Label.setFont(new Font("黑体", Font.PLAIN, 20));
		Filename_Label.setBounds(85, 314, 100, 36);
		Upload_Panel.add(Filename_Label);

		// 档案号文本域
		FileID_Txt = new JTextField();
		FileID_Txt.setBounds(215, 40, 272, 27);
		Upload_Panel.add(FileID_Txt);
		FileID_Txt.setColumns(10);

		// 文件描述文本域
		Filedescription_Txt = new JTextArea();
		Filedescription_Txt.setBounds(215, 96, 272, 199);
		Upload_Panel.add(Filedescription_Txt);
		Filedescription_Txt.setColumns(10);

		// 文件名文本域
		Filepath_Txt = new JTextField();
		Filepath_Txt.setColumns(10);
		Filepath_Txt.setBounds(215, 321, 272, 27);
		Upload_Panel.add(Filepath_Txt);

		// 上传按钮
		Upload_Button = new JButton("\u4E0A\u4F20");
		Upload_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 上传文件事件
				UploadActionPerformed(user, e);
			}
		});
		Upload_Button.setBounds(215, 380, 95, 27);
		Upload_Button.setFont(new Font("黑体", Font.PLAIN, 20));
		Upload_Panel.add(Upload_Button);

		// 返回按钮
		Return_Button1 = new JButton("\u8FD4\u56DE");
		Return_Button1.setBounds(395, 380, 95, 27);
		Return_Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回事件
				ReturnActionPerformed(e);
			}
		});

		// 打开按钮
		OpenFile_Button = new JButton("\u6253\u5F00");
		OpenFile_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 打开文件事件
				OpenFileActionPerformed(e);
			}
		});
		OpenFile_Button.setFont(new Font("黑体", Font.PLAIN, 18));
		OpenFile_Button.setBounds(532, 319, 95, 27);
		Upload_Panel.add(OpenFile_Button);
		Return_Button1.setFont(new Font("黑体", Font.PLAIN, 20));
		Upload_Panel.add(Return_Button1);

		// 下载页面
		Download_Panel = new JPanel();
		tabbedPane.addTab("\u6587\u4EF6\u4E0B\u8F7D", null, Download_Panel, null);
		tabbedPane.setEnabledAt(1, true);
		Download_Panel.setLayout(null);

		// 下载按钮
		Download_Button = new JButton("\u4E0B\u8F7D");
		Download_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 下载文件事件
				DownloadActionPerformed(user, e);
			}
		});
		Download_Button.setFont(new Font("黑体", Font.PLAIN, 20));
		Download_Button.setBounds(215, 380, 95, 27);
		Download_Panel.add(Download_Button);

		// 返回按钮
		Return_Button2 = new JButton("\u8FD4\u56DE");
		Return_Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回事件
				ReturnActionPerformed(e);
			}
		});
		Return_Button2.setFont(new Font("黑体", Font.PLAIN, 20));
		Return_Button2.setBounds(395, 380, 95, 27);
		Download_Panel.add(Return_Button2);

		// 可下拉容器
		scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 32, 637, 322);
		Download_Panel.add(scrollPane);

		// 下载文件列表
		Files_table = new JTable();
		// 构造表格
		ConstructFileTable();
		// 加入可下拉区域
		scrollPane.setViewportView(Files_table);

		// 设置权限及页面
		setPane(user, choice);
	}

	// 表格构造
	private void ConstructFileTable() {

		// 表头数据
		String[] columnNames = { "\u6863\u6848\u53F7", "\u521B\u5EFA\u8005", "\u65F6\u95F4", "\u6587\u4EF6\u540D",
				"\u6587\u4EF6\u63CF\u8FF0" };
		// 表格数据
		String[][] rowData = new String[20][5];

		Enumeration<Doc> f;
		try {
			// 获取哈希表信息
			f = DataProcessing.getAllDocs();

			// 行数
			int row = 0;
			// 将哈希表信息导入至表格
			while (f.hasMoreElements()) {
				Doc doc = f.nextElement();
				rowData[row][0] = doc.getID();
				rowData[row][1] = doc.getCreator();
				rowData[row][2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(doc.getTimestamp()); // Time转String
				rowData[row][3] = doc.getFilename();
				rowData[row][4] = doc.getDescription();
				row++;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		// 构造表格
		Files_table.setModel(new DefaultTableModel(rowData, columnNames) {

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});

	}

	// 打开文件
	private void OpenFileActionPerformed(ActionEvent evt) {

		// 弹出文件选择框
		FileDialog OpenFileDialog = new FileDialog(this, "选择上传文件");
		OpenFileDialog.setVisible(true);

		// 获取文件路径
		String filepath = OpenFileDialog.getDirectory() + OpenFileDialog.getFile();
		Filepath_Txt.setText(filepath);

	}

	// 上传文件
	private synchronized void UploadActionPerformed(User user, ActionEvent evt) {

		String filepath = Filepath_Txt.getText();
		String fileID = FileID_Txt.getText();
		String filedescription = Filedescription_Txt.getText();

		if (StringUtil.isEmpty(filepath)) {
			JOptionPane.showMessageDialog(null, "未选择文件！");
			return;
		}
		if (StringUtil.isEmpty(fileID)) {
			JOptionPane.showMessageDialog(null, "未输入档案号！");
			return;
		}
		if (StringUtil.isEmpty(filedescription)) {
			JOptionPane.showMessageDialog(null, "未输入文件描述！");
			return;
		}

		if (user.uploadFile(fileID, filepath, filedescription)) {

			// 发送上传文件消息
			try {
				Client.SendMessage("上传文件");
				Client.ReceiveMessage();
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
				return;
			}

			// 更新表格数据
			ConstructFileTable();
			JOptionPane.showMessageDialog(null, "上传成功！");
			return;

		} else {
			JOptionPane.showMessageDialog(null, "上传失败！");
			return;
		}

	}

	// 下载文件
	private synchronized void DownloadActionPerformed(User user, ActionEvent evt) {

		// 获取所选行序号, 若未选择其值为-1
		int selectedrow = Files_table.getSelectedRow();

		// 未选择文件的情况
		if (selectedrow == -1) {
			JOptionPane.showMessageDialog(null, "未选择文件！");
			return;
		} else {

			// 获取档案号
			String fileID = (String) Files_table.getValueAt(selectedrow, 0);
			// 若选择空行
			if (StringUtil.isEmpty(fileID)) {
				return;
			}

			// 显示确认界面: 信息, 标题, 选项个数
			int value = JOptionPane.showConfirmDialog(null, "确定要下载文件吗？", "文件下载确认界面", 2);
			// Yes=0 No=1
			if (value == 0) {
				if (user.downloadFile(fileID)) {

					// 发送下载文件消息
					try {
						Client.SendMessage("下载文件");
						Client.ReceiveMessage();
					} catch (IOException | ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
						return;
					}

					JOptionPane.showMessageDialog(null, "下载成功！");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "下载失败！");
					return;
				}

			} else if (value == 1) {
				return;
			}
		}
	}

	// 设置页面
	private void setPane(User user, int choice) {

		if (!user.getRole().equalsIgnoreCase("operator")) {
			FileID_Txt.setEditable(false);
			Filedescription_Txt.setEditable(false);
			Filepath_Txt.setEditable(false);
			Upload_Button.setEnabled(false);
			OpenFile_Button.setEnabled(false);
		}

		if (choice == 0) {
			tabbedPane.setSelectedComponent(Upload_Panel);
		} else if (choice == 1) {
			tabbedPane.setSelectedComponent(Download_Panel);
		}

	}

	// 返回
	private void ReturnActionPerformed(ActionEvent evt) {
		this.dispose();
	}
}
