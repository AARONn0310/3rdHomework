package controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

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
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UpdateMemberUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField phoneField;
	private static MemberServiceImpl msi = new MemberServiceImpl();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member m = new Member();
					UpdateMemberUI frame = new UpdateMemberUI(m);
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
	public UpdateMemberUI(Member m) {
		setTitle("變更會員資料");
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
		
		JLabel nameLabel = new JLabel("姓名:");
		nameLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		nameLabel.setBounds(37, 72, 47, 21);
		panel.add(nameLabel);
		
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(94, 72, 131, 21);
		panel.add(nameField);
		
		
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
		
		JButton updateButton = new JButton("修改完成");
		updateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Name = nameField.getText();
				String Username = m.getUsername();
				String Password = String.valueOf(passwordField.getPassword());
				String Address = addressArea.getText();
				String Phone = phoneField.getText();
				
				msi.updateMember(Name, Username, Password, Address, Phone);
				
				JOptionPane.showMessageDialog(
	                    UpdateMemberUI.this,
	                    "輸入沒有問題",
	                    "修改成功!",
	                    JOptionPane.INFORMATION_MESSAGE
	            );
				MemberUI frame = new MemberUI(m);
                frame.setVisible(true);
                dispose();  // 关闭当前窗口
			}
		});
		updateButton.setBounds(88, 285, 87, 23);
		panel.add(updateButton);
		
		JTextArea textArea = new JTextArea(); 
		List<Member> l = msi.findByUsername(m.getUsername());
		Member member = l.get(0);  // 获取列表中的第一个会员
	    textArea.setText("親愛的" + member.getUsername() + "會員:\n您好，請輸入您欲修改的資料:");
		textArea.setBounds(10, 10, 226, 34);
		panel.add(textArea);
	}

}
