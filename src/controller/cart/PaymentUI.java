package controller.cart;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.member.MemberUI;
import model.Cart;
import model.Member;
import service.impl.CartServiceImpl;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class PaymentUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cart cart;
    private Member member;
    private String membership;

    public static void main(String[] args) {
    	//String membership = "0";
        EventQueue.invokeLater(() -> {
            try {
            	Cart cart = new Cart();
            	Member m = new Member("testUser");
            	String membership = "0";
            	PaymentUI frame = new PaymentUI(cart,m,membership);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PaymentUI(Cart cart,Member m, String membership) {
        
    	setTitle("訂單確認頁面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 593, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Define column names
        String[] columnNames = {"流水號", "會員編號" ,"訂單編號", "A遊戲數量", "B遊戲數量", "C遊戲數量","應付金額"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        CartServiceImpl csi = new CartServiceImpl();
        List<Cart> cartList = csi.findByUsername(m.getUsername());

        for (Cart c : cartList) {
            Object[] row = {
                c.getOrderID(),           // 流水號
                m.getMembernumber(),      // 會員編號
                c.getOrdernumber(),       // 訂單編號
                c.getGameA(),             // A遊戲數量
                c.getGameB(),             // B遊戲數量
                c.getGameC(),             // C遊戲數量
                c.calculateDiscountedPrice(membership)	// 應付金額
                
            };
            model.addRow(row);
        
        }
        

        // Create JTable
        JTable table = new JTable(model);

        // Create JScrollPane and add JTable to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 557, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add JScrollPane to contentPane
        contentPane.add(scrollPane);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 557, 368);
        panel.setBackground(new Color(0, 128, 128));
        panel.setOpaque(true);
        contentPane.add(panel);
        panel.setLayout(null);
                	
    	JButton exitButton = new JButton("退出");
    	exitButton.addMouseListener(new MouseAdapter() {
    		 @Override
    		 public void mouseEntered(MouseEvent e) {
			    // 鼠标进入按钮区域时改变按钮颜色
	            exitButton.setBackground(Color.RED);
    		 }

			@Override
			public void mouseExited(MouseEvent e) {
			    // 鼠标离开按钮区域时恢复按钮默认颜色
				exitButton.setBackground(UIManager.getColor("Button.background"));
			}
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		System.exit(0);			
	    	}
    	});
    	exitButton.setForeground(new Color(0, 0, 0));
    	exitButton.setContentAreaFilled(true);
    	exitButton.setBorder(new LineBorder(Color.white));
    	exitButton.setBounds(474, 325, 61, 23);
    	panel.add(exitButton);
        
        JButton memberUIButton = new JButton("回會員管理頁");
        memberUIButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		csi.deleteCartByOrderNumber(cart.getOrdernumber());
        		MemberUI mui = new MemberUI(m);
        		mui.setVisible(true);
        		dispose();
        	}
        });
        memberUIButton.setBounds(292, 325, 119, 23);
        panel.add(memberUIButton);
        
        JButton updateCartButton = new JButton("修改本次訂單");
        updateCartButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// 获取最新的 orderID
                Integer latestOrderID = csi.getLatestOrderID();
                
                // 使用最新的 orderID 删除该订单
                if (latestOrderID != null) {
                    csi.deleteCartByorderID(latestOrderID);
                } else {
                    // 如果没有找到最新订单，显示提示
                    JOptionPane.showMessageDialog(
                        PaymentUI.this,
                        "未找到最新订单",
                        "錯誤",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
                
        		CartUI cui = new CartUI(membership, m);
        		cui.setVisible(true);
        		dispose();
        	}
        });
        updateCartButton.setBounds(151, 325, 119, 23);
        panel.add(updateCartButton);
        
        JButton finalConfirmButton = new JButton("送出訂單");
        finalConfirmButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		LocalDateTime l = LocalDateTime.now();
        		DateTimeFormatter t1 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        		String l2 = l.format(t1);
        		cart.setDate(l2);
        		
        		System.out.println(cart.getDate());
        		FinalConfirmUI fcui = new FinalConfirmUI(cart, m, membership);
        		fcui.setVisible(true);
        		dispose();
        	}
        });
        finalConfirmButton.setBounds(10, 326, 119, 21);
        panel.add(finalConfirmButton);
		
	}
}
