package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.admin.AdminLoginUI;
import controller.member.AddMemberUI;
import controller.member.MemberUI;
import model.Member;
import service.impl.MemberServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginUI extends JFrame {

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
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setTitle("歡迎");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(10, 10, 264, 341);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton loginButton = new JButton("登入");
		loginButton.setBounds(30, 260, 87, 23);
		panel_1.add(loginButton);
		
		JLabel titleLabel = new JLabel("歡迎購物 請先登入!");
		titleLabel.setOpaque(true); // 使标签背景不透明
		titleLabel.setBackground(new Color(255, 255, 255)); // 设置背景颜色为白色
		titleLabel.setBackground(new Color(255, 255, 255));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中对齐
		titleLabel.setBounds(50, 30, 171, 29);
		titleLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16)); // 设置字体
		panel_1.add(titleLabel);
		
		JLabel accountLabel = new JLabel("帳號:");
		accountLabel.setBounds(45, 154, 40, 29);
		panel_1.add(accountLabel);
		
		JLabel passwordLabel = new JLabel("密碼:");
		passwordLabel.setBounds(45, 206, 40, 29);
		panel_1.add(passwordLabel);
		
		accountField = new JTextField();
		accountField.setBounds(91, 158, 96, 21);
		panel_1.add(accountField);
		accountField.setColumns(10);
		
		passwordField = new JPasswordField(); // 使用 JPasswordField
        passwordField.setBounds(91, 210, 96, 21);
        panel_1.add(passwordField);
        
        JButton signupButton = new JButton("註冊");
        signupButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AddMemberUI frame = new AddMemberUI();
                frame.setVisible(true);
                dispose();
        	}
        });
        signupButton.setBounds(146, 260, 87, 23);
        panel_1.add(signupButton);
        
        JButton adminButton = new JButton("管理人員");
        adminButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AdminLoginUI frame = new AdminLoginUI();
                frame.setVisible(true);
                dispose();
        	}
        });
        adminButton.setBounds(30, 297, 87, 23);
        panel_1.add(adminButton);
		
		loginButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String inputUsername = accountField.getText();
				String inputPassword = new String((passwordField).getPassword()); // 获取密码				
				MemberServiceImpl msi = new MemberServiceImpl();
				List<Member> existingMembers = msi.findByUsername(inputUsername);

		        if (existingMembers.isEmpty()) {
		            // 用户名不存在
		            JOptionPane.showMessageDialog(
		            		LoginUI.this,
		                "用戶名不存在!\n請先註冊",
		                "登入失敗",
		                JOptionPane.ERROR_MESSAGE
		            );
		        } else {
		            // 用户名存在，接着验证密码
		            List<Member> loginMembers = msi.loginMember(inputUsername, inputPassword);

		            if (loginMembers.isEmpty()) {
		                // 密码错误
		                JOptionPane.showMessageDialog(
		                    LoginUI.this,
		                    "密碼錯誤!",
		                    "登入失敗",
		                    JOptionPane.ERROR_MESSAGE
		                );
		            } else {
		                // 登录成功
		                JOptionPane.showMessageDialog(
		                    LoginUI.this,
		                    "登入成功!",
		                    "歡迎!",
		                    JOptionPane.INFORMATION_MESSAGE
		                );

		                //跳转到其他界面
		                MemberUI frame = new MemberUI(loginMembers.get(0));
		                frame.setVisible(true);
		                dispose();  // 关闭当前窗口
		            }
		        }
            }
		});
	}
}
