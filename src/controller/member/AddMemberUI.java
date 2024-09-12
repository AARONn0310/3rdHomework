package controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoginUI;
import model.Member;
import service.impl.MemberServiceImpl;

public class AddMemberUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField phoneField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemberUI frame = new AddMemberUI();
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
	public AddMemberUI() {
		setTitle("註冊頁面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 10, 246, 345);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea titleArea = new JTextArea();
		titleArea.setText("歡迎您! 請先註冊");
		titleArea.setEditable(false);	// 不可輸入，只可輸出
		titleArea.setBounds(10, 10, 131, 21);
		panel.add(titleArea);
		
		JLabel accountLabel = new JLabel("地址:");
		accountLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		accountLabel.setBounds(37, 195, 47, 21);
		panel.add(accountLabel);
		
		JLabel passwordLabel = new JLabel("密碼:");
		passwordLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		passwordLabel.setBounds(37, 111, 47, 31);
		panel.add(passwordLabel);
		
		passwordField = new JPasswordField(); // 使用 JPasswordField
		passwordField.setBounds(94, 116, 131, 21);
		panel.add(passwordField);
		
		JButton signupButton = new JButton("註冊");
		signupButton.setBounds(92, 277, 69, 23);
		panel.add(signupButton);
		
		JLabel nameLabel = new JLabel("姓名:");
		nameLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		nameLabel.setBounds(37, 54, 47, 21);
		panel.add(nameLabel);
		
		JLabel accountLabel_2 = new JLabel("會員名稱:");
		accountLabel_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		accountLabel_2.setBounds(10, 85, 74, 21);
		panel.add(accountLabel_2);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(94, 54, 131, 21);
		panel.add(nameField);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(94, 85, 131, 21);
		panel.add(usernameField);
		
		JLabel accountLabel_1 = new JLabel("電話:");
		accountLabel_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		accountLabel_1.setBounds(37, 246, 47, 21);
		panel.add(accountLabel_1);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(94, 246, 131, 21);
		panel.add(phoneField);
		
		JTextArea addressArea = new JTextArea();
		addressArea.setBounds(94, 192, 131, 44);
		panel.add(addressArea);
		
		JCheckBox CheckBox = new JCheckBox("顯示密碼");
		CheckBox.setBounds(94, 144, 81, 23);
		panel.add(CheckBox);
		CheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(CheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
				}else {
					passwordField.setEchoChar('*');
				}
			}
		});
		CheckBox.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		
		signupButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		        String Name = nameField.getText();
		        String Username = usernameField.getText();
		        String Password = String.valueOf(passwordField.getPassword());
		        String Address = addressArea.getText();
		        String Phone = phoneField.getText();
		      
		        // 检查是否有任何字段为空
		        if (Name.isEmpty() || Username.isEmpty() || Password.isEmpty() || Address.isEmpty() || Phone.isEmpty()) {
		            JOptionPane.showMessageDialog(
		                AddMemberUI.this, // 父组件
		                "所有欄位必須填寫，請勿留空!", // 错误消息
		                "錯誤", // 标题
		                JOptionPane.ERROR_MESSAGE // 消息类型
		            );
		            return; // 阻止继续执行
		        }
		        
		        try{
		            MemberServiceImpl msi = new MemberServiceImpl();
		            
		            // 调用查询方法检查用户名是否已存在
		            List<Member> existingMembers = msi.findByUsername(Username);
		            if (existingMembers != null && !existingMembers.isEmpty()) {
		                JOptionPane.showMessageDialog(
		                    AddMemberUI.this, // 父组件
		                    "帳號重複,請重新註冊", // 错误消息
		                    "錯誤", // 标题
		                    JOptionPane.ERROR_MESSAGE // 消息类型
		                );
		            } else {
		                // 如果用户名未注册，继续添加会员
		                msi.addMember(Name, Username, Password, Address, Phone);
		                
		                JOptionPane.showMessageDialog(
		                    AddMemberUI.this,
		                    "註冊成功!", // 成功消息
		                    "會員您好!", // 标题
		                    JOptionPane.INFORMATION_MESSAGE // 消息类型
		                );
		                
		                // 打开登录窗口并关闭当前窗口
		                LoginUI lo = new LoginUI();
		                lo.setVisible(true);
		                dispose();
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(
		                AddMemberUI.this,
		                "出現錯誤: " + ex.getMessage(),
		                "錯誤",
		                JOptionPane.ERROR_MESSAGE
		            );
		        }
		    }
		});

	}
}
