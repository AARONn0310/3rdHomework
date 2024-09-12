package controller.cart;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.member.MemberUI;
import model.Cart;
import model.Member;
import service.impl.CartServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class CartUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField gameA;
    private JTextField gameB;
    private JTextField gameC;
    private Cart cart;
    private Integer A;
    private Integer B;
    private Integer C;
    
    private Member member;
    private String membership;

    public static void main(String[] args) {
    	String membership = "0";
    	Member m = new Member("testUser");
        EventQueue.invokeLater(() -> {
            try {
            	
            	CartUI frame = new CartUI(membership,m);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CartUI(String membership,Member m) {
    	this.member = m;
    	this.membership = membership;
    	setTitle("購物頁面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(340, 0, 564, 441);
        panel.setBackground(Color.WHITE);
        panel.setOpaque(false);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setIcon(new ImageIcon(CartUI.class.getResource("/pic/mine4.png")));
        lblNewLabel_2_1.setBounds(210, 61, 150, 96);
        panel.add(lblNewLabel_2_1);

        JLabel lblNewLabel_1 = new JLabel("gameA:$109");
        lblNewLabel_1.setBounds(28, 167, 73, 15);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("gameB:$129");
        lblNewLabel_1_1.setBounds(210, 167, 72, 15);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("gameC:$159");
        lblNewLabel_1_2.setBounds(395, 167, 73, 15);
        panel.add(lblNewLabel_1_2);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(CartUI.class.getResource("/pic/mine1.png")));
        lblNewLabel_2.setBounds(18, 61, 150, 96);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1_1 = new JLabel("");
        lblNewLabel_2_1_1.setIcon(new ImageIcon(CartUI.class.getResource("/pic/mine9.png")));
        lblNewLabel_2_1_1.setBounds(385, 61, 150, 96);
        panel.add(lblNewLabel_2_1_1);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(340, 10, 564, 441);
        contentPane.add(lblNewLabel_3);
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setIcon(new ImageIcon(CartUI.class.getResource("/pic/mine3.png")));

        gameA = new JTextField();
        gameA.setOpaque(false);
        gameA.setColumns(10);
        gameA.setBounds(104, 164, 53, 21);
        panel.add(gameA);

        gameB = new JTextField();
        gameB.setOpaque(false);
        gameB.setColumns(10);
        gameB.setBounds(290, 164, 53, 21);
        panel.add(gameB);

        gameC = new JTextField();
        gameC.setOpaque(false);
        gameC.setColumns(10);
        gameC.setBounds(468, 164, 53, 21);
        panel.add(gameC);

        JTextArea output = new JTextArea();
        output.setForeground(Color.BLACK);
        output.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        output.setText("滿一千元享九折優惠!\r\n請按放入購物車以試算金額:");
        output.setOpaque(false);  // 设置为透明背景
        output.setEditable(false);  // 不可輸入，只可輸出
        output.setBounds(194, 233, 149, 60);
        panel.add(output);
		
		JButton resetButton = new JButton("重設");
		resetButton.setBackground(new Color(255, 255, 255));		
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
	        public void mouseEntered(MouseEvent e) {
				resetButton.setBackground(Color.RED);
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            // 鼠标离开按钮区域时恢复按钮默认颜色
	        	resetButton.setBackground(UIManager.getColor("Button.background"));
	        }
			@Override
			public void mouseClicked(MouseEvent e) {
				gameA.setText("");
				gameB.setText("");
				gameC.setText("");
				
				output.setText("滿一千元享九折優惠!\r\n請按加入購物車以計算金額:");
			}
		});
		resetButton.setForeground(new Color(0, 0, 0));
		resetButton.setBorder(new LineBorder(Color.WHITE));
		resetButton.setContentAreaFilled(false);
		resetButton.setBounds(474, 335, 61, 23);
		resetButton.setContentAreaFilled(true);
		panel.add(resetButton);
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 10, 564, 41);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to mine shopping");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(170, 15, 166, 15);
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(CartUI.class.getResource("/pic/creep_1.png")));
		lblNewLabel_4.setBounds(336, 0, 60, 41);
		panel_1.add(lblNewLabel_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(0, 128, 128));
		panel_2.setBounds(22, 0, 308, 441);
		contentPane.add(panel_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(0, 0, 337, 61);
		panel_2.add(panel_1_1);
		
		JLabel lblNewLabel_5 = new JLabel("Welcome to mine shopping");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(78, 20, 166, 31);
		panel_1_1.add(lblNewLabel_5);
		
		JTextArea lbln = new JTextArea("歡迎蒞臨購物!\r\n您過去曾在本網站消費的金額是?\n>5000:享五折優惠!\n>3000:享七折優惠!\n>1000:享九折優惠!\n無會員滿千亦享九折優惠!");
		lbln.setBounds(36, 83, 244, 145);
		panel_2.add(lbln);
		lbln.setOpaque(true);
		lbln.setEditable(false);  // 不可輸入，只可輸出
		lbln.setBackground(Color.WHITE);
		
		ButtonGroup memberGroup = new ButtonGroup();
		JRadioButton diamondRadioButton = new JRadioButton(">5000:鑽石會員");
		diamondRadioButton.setBounds(94, 256, 138, 23);
		panel_2.add(diamondRadioButton);
		diamondRadioButton.setActionCommand("1");
		memberGroup.add(diamondRadioButton);
		
		JRadioButton goldRadioButton = new JRadioButton(">3000:金卡會員");
		goldRadioButton.setBounds(94, 302, 138, 23);
		panel_2.add(goldRadioButton);
		goldRadioButton.setActionCommand("2");
		memberGroup.add(goldRadioButton);
		
		JRadioButton silverRadioButton = new JRadioButton(">1000:銀卡會員");
		silverRadioButton.setBounds(94, 344, 138, 23);
		panel_2.add(silverRadioButton);
		silverRadioButton.setActionCommand("3");
		memberGroup.add(silverRadioButton);
		
		JRadioButton normalRadioButton = new JRadioButton("未曾消費");
		normalRadioButton.setBounds(94, 386, 138, 23);
		panel_2.add(normalRadioButton);
		normalRadioButton.setActionCommand("0");
		memberGroup.add(normalRadioButton);
		
		switch(membership) {
		case "1":
			diamondRadioButton.setSelected(true);
			break;
		case "2":
			goldRadioButton.setSelected(true);
			break;
		case "3":
			silverRadioButton.setSelected(true);
			break;
		case "0":
			normalRadioButton.setSelected(true);
			break;							
		}
		System.out.println(membership);
		
		JButton btnNewButton = new JButton("放入購物車");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 检查是否选择了会员类型
                if (memberGroup.getSelection() != null) {
                    String membership = memberGroup.getSelection().getActionCommand();
                    
                    // 读取输入框中的内容
                    String GameA = gameA.getText();
                    String GameB = gameB.getText();
                    String GameC = gameC.getText();
                    
                    try {
                        A = GameA.isEmpty() ? 0 : Integer.valueOf(GameA);
                        B = GameB.isEmpty() ? 0 : Integer.valueOf(GameB);
                        C = GameC.isEmpty() ? 0 : Integer.valueOf(GameC);
                        
                        // 创建 Student 对象
                        cart = new Cart(A, B, C, membership);
                        
                        // 计算折扣后的价格
                        output.setText("總金額" + cart.getSum() + "\n折扣後價格: " + cart.calculateDiscountedPrice(membership));
                        
                    } catch (NumberFormatException ex) {
                        output.setText("請輸入有效的數字");
                    }
                } else {
                    // 如果没有选择任何按钮，显示错误信息
                    JOptionPane.showMessageDialog(
                        CartUI.this,
                        "請選擇一個會員類型",
                        "錯誤",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBorder(new LineBorder(Color.WHITE));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(225, 209, 97, 23);
		panel.add(btnNewButton);
					
		JButton paymentButton = new JButton("進入訂單確認頁面");
		paymentButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        CartServiceImpl csi = new CartServiceImpl();

		        // 检查是否选择了会员类型
		        if (memberGroup.getSelection() != null) {
		            String membership = memberGroup.getSelection().getActionCommand();

		            // 读取输入框中的内容
		            String GameA = gameA.getText();
		            String GameB = gameB.getText();
		            String GameC = gameC.getText();

		            try {
		                A = GameA == null || GameA.trim().isEmpty() ? 0 : Integer.valueOf(GameA);
		                B = GameB == null || GameB.trim().isEmpty() ? 0 : Integer.valueOf(GameB);
		                C = GameC == null || GameC.trim().isEmpty() ? 0 : Integer.valueOf(GameC);

		                // 检查是否所有的商品数量都是 0
		                if (A == 0 && B == 0 && C == 0) {
		                    JOptionPane.showMessageDialog(
		                        CartUI.this,
		                        "請至少下單一件商品",
		                        "錯誤",
		                        JOptionPane.WARNING_MESSAGE
		                    );
		                    return;  // 阻止继续执行
		                }

		                // 创建 Cart 对象
		                cart = new Cart(A, B, C, membership);
		                System.out.println("Cart: " + cart);  // 打印 cart 对象信息

		                // 计算折扣后的价格
		                output.setText("總金額:" + cart.getSum() + "\n折扣後價格: " + cart.calculateDiscountedPrice(membership));

		                // 获取当前日期和时间
		                LocalDateTime now = LocalDateTime.now();
		                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		                String formattedDate = now.format(formatter);

		                // 添加购物车
		                csi.addCart(m.getUsername(), A, B, C,membership, cart.getSum(), formattedDate);

		                // 进入支付页面
		                PaymentUI frame = new PaymentUI(cart, m, membership); //這裡
		                frame.setVisible(true);
		                dispose();

		            } catch (NumberFormatException ex) {
		                output.setText("請輸入有效的數字");
		            }
		        } else {
		            // 如果没有选择任何会员类型，显示错误信息
		            JOptionPane.showMessageDialog(
		                CartUI.this,
		                "請選擇一個會員類型",
		                "錯誤",
		                JOptionPane.WARNING_MESSAGE
		            );
		        }
		    }
		});

		paymentButton.setForeground(Color.BLACK);
		//exitButton.setContentAreaFilled(false);
		paymentButton.setBorder(new LineBorder(Color.BLACK));
		paymentButton.setBounds(406, 295, 129, 23);
		panel.add(paymentButton);
		
		JButton btnNewButton_1 = new JButton("回會員管理頁");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberUI mui = new MemberUI(m);
				mui.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(28, 335, 129, 23);
		panel.add(btnNewButton_1);

	}
}
