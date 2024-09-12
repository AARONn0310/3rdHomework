package controller.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Member;
import service.impl.MemberServiceImpl;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QueryMemberUI extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberServiceImpl msi = new MemberServiceImpl();
					List<Member> memberList = msi.findAllMember();  
					QueryMemberUI frame = new QueryMemberUI(memberList);
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
	public QueryMemberUI(List<Member> memberList) {
		setTitle("查詢所有會員");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);

        // Create panel and set layout
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Define column names
        String[] columnNames = {"ID", "MemberNo" ,"Name", "Username", "Address", "Phone"};

        // Create DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for(Member m : memberList) {
			Object[] row = {m.getId(), m.getMembernumber(), m.getName(),
					m.getUsername(), m.getPassword(), m.getAddress(),m.getPhone()};
			model.addRow(row);
		}
        

        // Create JTable
        JTable table = new JTable(model);

        // Create JScrollPane and add JTable to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 460, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add JScrollPane to contentPane
        contentPane.add(scrollPane);
        
        JButton UorDmemberButton = new JButton("我想刪除某個奧客");
        UorDmemberButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AdminDeleteMemberUI frame = new AdminDeleteMemberUI(memberList);
		        frame.setVisible(true);
		        dispose();
        	}
        });
        UorDmemberButton.setBounds(34, 328, 148, 23);
        contentPane.add(UorDmemberButton);
        
        JButton adminButton = new JButton("回管理頁");
        adminButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AdminUI frame = new AdminUI();
		        frame.setVisible(true);
		        dispose();
        	}
        });
        adminButton.setBounds(359, 328, 87, 23);
        contentPane.add(adminButton);

        // Set JFrame visible
        setVisible(true);
		
		
	}

}
