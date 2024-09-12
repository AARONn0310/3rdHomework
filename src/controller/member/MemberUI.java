package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.cart.CartUI;
import controller.cart.QUDChooseUI;
import model.Cart;
import model.Member;
import service.impl.MemberServiceImpl;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member m = new Member();
					MemberUI frame = new MemberUI(m);
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
	public MemberUI(Member m) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 10, 464, 491);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to mine shopping");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(108, 33, 244, 45);
		panel.add(lblNewLabel);
		
		
		
		JRadioButton diamondRadioButton = new JRadioButton(">5000:鑽石會員(五折)");
		diamondRadioButton.setBounds(161, 274, 147, 23);
		diamondRadioButton.setActionCommand("1");  // 设置对应数字
		panel.add(diamondRadioButton);
		
		JRadioButton goldRadioButton = new JRadioButton(">3000:金卡會員(七折)");
		goldRadioButton.setBounds(161, 314, 147, 23);
		goldRadioButton.setActionCommand("2");
		panel.add(goldRadioButton);
		
		JRadioButton silverRadioButton = new JRadioButton(">1000:銀卡會員(九折)");
		silverRadioButton.setBounds(161, 354, 147, 23);
		silverRadioButton.setActionCommand("3");
		panel.add(silverRadioButton);
		
		JRadioButton normalRadioButton = new JRadioButton("未曾消費");
		normalRadioButton.setBounds(161, 396, 147, 23);
		normalRadioButton.setActionCommand("0");	//這是一個setter方法
		panel.add(normalRadioButton);
		
		ButtonGroup memberGroup = new ButtonGroup();
		memberGroup.add(diamondRadioButton);
		memberGroup.add(goldRadioButton);
		memberGroup.add(silverRadioButton);
		memberGroup.add(normalRadioButton);
		
		JTextArea lbln = new JTextArea("歡迎蒞臨購物!\r\n您過去曾在本網站消費的金額是?\n超過五千元:享五折優惠!\n超過三千元:享七折優惠!\n超過一千元:享九折優惠!\n無會員滿千亦享九折優惠!");
		lbln.setFont(new Font("Monospaced", Font.PLAIN, 14));
		lbln.setOpaque(true);
		lbln.setBackground(Color.WHITE);
		lbln.setBounds(108, 115, 244, 133);
		panel.add(lbln);
		
		JButton startButton = new JButton("開始購物");
		startButton.addActionListener(e -> {
		    // 检查是否选择了一个 RadioButton
		    if (memberGroup.getSelection() != null) {
		    	
		        String Membership = memberGroup.getSelection().getActionCommand();	//這是一個getter,拿到剛剛setter所設定的數字
		        //Student s = new Student(Membership);	//注意這邊已經new了,這個new沒用到，會被垃圾回收
		        CartUI frame = new CartUI(Membership,m);
		        frame.setVisible(true);
		        dispose();
		    } else {
		        // 如果没有选择任何按钮，显示一个错误信息
		        JOptionPane.showMessageDialog(
		            MemberUI.this, // 父组件
		            "請選擇一個會員類型", // 消息内容
		            "錯誤", // 标题
		            JOptionPane.WARNING_MESSAGE // 消息类型
		        );
		    }
		});

		startButton.setBounds(328, 442, 87, 23);
		panel.add(startButton);
		
		JButton changeButton = new JButton("修改會員資料");
		changeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateMemberUI frame = new UpdateMemberUI(m);
		        frame.setVisible(true);
		        dispose();
			}
		});
		changeButton.setBounds(34, 442, 121, 23);
		panel.add(changeButton);
		
		JButton uordButton = new JButton("查看/修改/刪除訂單");
		uordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 检查是否选择了一个 RadioButton
			    if (memberGroup.getSelection() != null) {
			    	
			        String Membership = memberGroup.getSelection().getActionCommand();	//這是一個getter,拿到剛剛setter所設定的數字
			        //Student s = new Student(Membership);	//注意這邊已經new了,這個new沒用到，會被垃圾回收
			        QUDChooseUI Qudui = new QUDChooseUI(m,Membership);
					Qudui.setVisible(true);
					dispose();
			    } else {
			        // 如果没有选择任何按钮，显示一个错误信息
			        JOptionPane.showMessageDialog(
			            MemberUI.this, // 父组件
			            "請選擇一個會員類型", // 消息内容
			            "錯誤", // 标题
			            JOptionPane.WARNING_MESSAGE // 消息类型
			        );
			    }
				
			}
		});
		uordButton.setBounds(165, 442, 143, 23);
		panel.add(uordButton);
	}
}
