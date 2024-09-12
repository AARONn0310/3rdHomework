package controller.cart;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Cart;
import model.Member;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QUDChooseUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member m = new Member("testUser");
					String membership = "3";
					QUDChooseUI frame = new QUDChooseUI(m, membership);
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
	public QUDChooseUI(Member m,String membership) {
		setTitle("修改/刪除訂單");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 10, 211, 241);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("您希望查詢、修改或是刪除訂單?");
		lblNewLabel.setBounds(10, 10, 191, 28);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("修改訂單");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cart c = new Cart();
				UpdateCartUI uui = new UpdateCartUI(c,m,membership);
				uui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(62, 137, 87, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("刪除訂單");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DeleteUI dui = new DeleteUI(m, membership);
				dui.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(62, 185, 87, 23);
		panel.add(btnNewButton_1);
		
		JButton queryButton = new JButton("查詢訂單");
		queryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cart c = new Cart();
				FinalConfirmUI fcui = new FinalConfirmUI(c,m,membership);
				fcui.setVisible(true);
				dispose();
				
			}
		});
		queryButton.setBounds(62, 92, 87, 23);
		panel.add(queryButton);
	}
}
