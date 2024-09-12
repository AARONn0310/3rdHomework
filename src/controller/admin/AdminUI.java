package controller.admin;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginUI;
import controller.member.UpdateMemberUI;
import model.Cart;
import model.Member;
import service.impl.CartServiceImpl;
import service.impl.MemberServiceImpl;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AdminUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static MemberServiceImpl msi = new MemberServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = new AdminUI();
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
	public AdminUI() {
		setTitle("管理人員頁面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 10, 413, 408);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("管理人員您好，本頁面可管理所有會員之訂單:");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 359, 41);
		panel.add(lblNewLabel);
		
		JButton querymemberButton = new JButton("查看所有會員");
		querymemberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberServiceImpl msi = new MemberServiceImpl();
				List<Member> memberList = msi.findAllMember();
				QueryMemberUI frame = new QueryMemberUI(memberList);
		        frame.setVisible(true);
		        dispose();
			}
		});
		querymemberButton.setBounds(51, 146, 121, 23);
		panel.add(querymemberButton);
		
		JLabel lblNewLabel_1 = new JLabel("會員管理");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(29, 92, 96, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("訂單管理");
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(29, 245, 96, 28);
		panel.add(lblNewLabel_1_1);
		
		JButton querycartButton = new JButton("查看所有訂單");
		querycartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CartServiceImpl csi = new CartServiceImpl();
				List<Cart> cartList = csi.findAllCart();
				QueryCartUI frame = new QueryCartUI(cartList);
		        frame.setVisible(true);
		        dispose();
			}
		});
		querycartButton.setBounds(51, 296, 121, 23);
		panel.add(querycartButton);
		
		JButton loginMemberButton = new JButton("返回會員登入頁");
		loginMemberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI frame = new LoginUI();
                frame.setVisible(true);
                dispose();
			}
		});
		loginMemberButton.setBounds(268, 361, 124, 23);
		panel.add(loginMemberButton);
	}
}
