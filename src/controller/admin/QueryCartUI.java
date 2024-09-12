package controller.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Cart;
import service.impl.CartServiceImpl;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryCartUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField deleteField;
	//private static CartServiceImpl csi = new CartServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartServiceImpl csi = new CartServiceImpl();
					List<Cart> cartList = csi.findAllCart(); 
					QueryCartUI frame = new QueryCartUI(cartList);
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
	public QueryCartUI(List<Cart> cartList) {
		setTitle("查詢所有訂單");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Define column names
        String[] columnNames = {"流水號", "訂單編號", "A遊戲數量", "B遊戲數量", "C遊戲數量","應付金額"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Cart c : cartList) {
            Object[] row = {
                c.getOrderID(),           // 流水號
               // m.getMembernumber(),      // 會員編號
                c.getOrdernumber(),       // 訂單編號
                c.getGameA(),             // A遊戲數量
                c.getGameB(),             // B遊戲數量
                c.getGameC(),             // C遊戲數量
                c.calculateDiscountedPrice(c.getMembership()),  // 應付金額
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
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 10, 557, 408);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("您希望刪除哪筆訂單?請輸入訂單編號:");
		lblNewLabel_1.setBounds(21, 316, 211, 24);
		panel.add(lblNewLabel_1);
		
		deleteField = new JTextField();
		deleteField.setColumns(10);
		deleteField.setBounds(51, 364, 66, 21);
		panel.add(deleteField);
		
		JButton deleteButton = new JButton("刪除");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String orderID = deleteField.getText();
				CartServiceImpl csi = new CartServiceImpl();
				csi.deleteCartByOrderNumber(orderID);
				
				QueryCartUI ctui = new QueryCartUI(csi.findAllCart());
				ctui.setVisible(true);
				dispose();
				
			}
		});
		deleteButton.setBounds(138, 363, 66, 23);
		panel.add(deleteButton);
		
		JButton adminButton = new JButton("回管理人員頁面");
		adminButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminUI aui = new AdminUI();
				aui.setVisible(true);
				dispose();
			}
		});
		adminButton.setBounds(373, 363, 154, 23);
		panel.add(adminButton);
	}
}
