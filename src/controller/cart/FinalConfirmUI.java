package controller.cart;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class FinalConfirmUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public static void main(String[] args) {
    	//String membership = "0";
        EventQueue.invokeLater(() -> {
            try {
            	Cart cart = new Cart();
            	Member m = new Member("testUser");
            	String membership = "0";
            	LocalDateTime l = LocalDateTime.now();
        		DateTimeFormatter t1 = DateTimeFormatter.ISO_LOCAL_DATE;
        		String l2 = l.format(t1);
        		cart.setDate(l2);
        		FinalConfirmUI frame = new FinalConfirmUI(cart,m,membership);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FinalConfirmUI(Cart cart, Member m, String membership) {
    	setTitle("訂單完成");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Define column names
        String[] columnNames = {"流水號", "會員編號" ,"訂單編號", "A遊戲數量", "B遊戲數量", "C遊戲數量","應付金額","訂單成立時間"};
        
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
                c.calculateDiscountedPrice(membership),   // 應付金額
                c.getDate()
                
            };
            System.out.println("Date: " + c.getDate());
            model.addRow(row);
        }
        

        // Create JTable
        JTable table = new JTable(model);

        // Create JScrollPane and add JTable to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 664, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add JScrollPane to contentPane
        contentPane.add(scrollPane);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 664, 363);
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
    	exitButton.setBounds(581, 322, 61, 23);
    	panel.add(exitButton);
        
        JButton CartUIButton = new JButton("回購買頁面");
        CartUIButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		csi.deleteCartByOrderNumber(cart.getOrdernumber());
        		CartUI cui = new CartUI("0",m);
        		cui.setVisible(true);
        		dispose();
        	}
        });
        CartUIButton.setBounds(146, 322, 119, 23);
        panel.add(CartUIButton);
        

		JButton printButton = new JButton("列印");
		printButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		printButton.setForeground(new Color(0, 0, 0));
		printButton.setContentAreaFilled(true);
		printButton.setBorder(new LineBorder(Color.WHITE));
		printButton.setBounds(30, 323, 89, 21);
		panel.add(printButton);
		
		JButton memberUIButton = new JButton("回會員管理頁");
		memberUIButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			MemberUI mui = new MemberUI(m);
    			mui.setVisible(true);
    			dispose();
    		}
    	});
		memberUIButton.setBounds(287, 322, 119, 23);
		panel.add(memberUIButton);
		
	}
}