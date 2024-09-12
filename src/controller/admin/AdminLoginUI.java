package controller.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoginUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField accountField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginUI frame = new AdminLoginUI();
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
	public AdminLoginUI() {
		setTitle("管理人員登入頁面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 10, 268, 277);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea titleArea = new JTextArea();
		titleArea.setText("歡迎! 請先登入\n預設管理員帳號/密碼為:root/1234");
		titleArea.setEditable(false);	// 不可輸入，只可輸出
		titleArea.setBounds(10, 10, 248, 50);
		panel.add(titleArea);
		
		JLabel accountLabel = new JLabel("帳號:");
		accountLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		accountLabel.setBounds(37, 90, 47, 21);
		panel.add(accountLabel);
		
		JLabel passwordLabel = new JLabel("密碼:");
		passwordLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		passwordLabel.setBounds(37, 135, 47, 31);
		panel.add(passwordLabel);
		
		accountField = new JTextField();
		accountField.setBounds(94, 90, 107, 21);
		panel.add(accountField);
		accountField.setColumns(10);
		
		passwordField = new JPasswordField(); // 使用 JPasswordField
        passwordField.setBounds(94, 140, 107, 21);
        panel.add(passwordField);
        
        JButton loginButton = new JButton("登入");
		loginButton.setBounds(108, 199, 69, 23);
		panel.add(loginButton);
		
		JButton loginMemberButton = new JButton("返回會員登入頁");
		loginMemberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI frame = new LoginUI();
                frame.setVisible(true);
                dispose();
			}
		});
		loginMemberButton.setBounds(77, 244, 124, 23);
		panel.add(loginMemberButton);
		
		loginButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String inputAccount = accountField.getText();
				String inputPassword = new String((passwordField).getPassword()); // 获取密码				
              
                if ((!inputAccount.equals("root")) || (!inputPassword.equals("1234"))) {
                    // 弹出错误消息框
                    JOptionPane.showMessageDialog(
                        AdminLoginUI.this, // 父组件
                        "帳號密碼錯誤", // 消息内容
                        "錯誤", // 标题
                        JOptionPane.ERROR_MESSAGE // 消息类型
                    );
                } else {
                	AdminUI frame = new AdminUI();
	                frame.setVisible(true);
	                dispose();
                }
            }
		});

	}

}
