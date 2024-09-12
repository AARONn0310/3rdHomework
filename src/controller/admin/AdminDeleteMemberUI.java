package controller.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Member;
import service.impl.MemberServiceImpl;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;

public class AdminDeleteMemberUI extends JFrame {

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
					
					List<Member> memberList = msi.findAllMember();  
					AdminDeleteMemberUI frame = new AdminDeleteMemberUI(memberList);
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
	public AdminDeleteMemberUI(List<Member> memberList) {
		setTitle("刪除會員");
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
					m.getUsername(), m.getAddress(),m.getPhone()};
			model.addRow(row);
		}
        

        // Create JTable
        JTable table = new JTable(model);

        // Create JScrollPane and add JTable to it
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 460, 200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add JScrollPane to contentPane
        contentPane.add(scrollPane);
        
        JComboBox<Member> comboBox = new JComboBox<>();
        comboBox.setBounds(177, 251, 93, 23);
        contentPane.add(comboBox);
        
        for(Member name : memberList) {
        	comboBox.addItem(name);
        }
        
     // 自定义渲染器
        comboBox.setRenderer(new ListCellRenderer<Member>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Member> list, Member value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel(value.getUsername());  // 或者其他你希望显示的属性
                if (isSelected) {
                    label.setBackground(Color.LIGHT_GRAY);
                    label.setOpaque(true);
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });
        
        JButton deleteMemberButton = new JButton("確認刪除");
        deleteMemberButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Member selectedMember = (Member) comboBox.getSelectedItem();
                if (selectedMember != null) {
                    // 通过 selectedMember 获取会员 username
                    String membername = selectedMember.getUsername();
                    // 删除会员
                    msi.deleteMemberByUsername(membername);
                    JOptionPane.showMessageDialog(AdminDeleteMemberUI.this, "會員已刪除");
                    	List<Member> l = msi.findAllMember();
                    	AdminDeleteMemberUI admui = new AdminDeleteMemberUI(l);
                    	admui.setVisible(true);
                    	dispose();
                } else {
                    JOptionPane.showMessageDialog(AdminDeleteMemberUI.this, "請先選擇一個會員");
                }
        	}
        });
        deleteMemberButton.setBounds(359, 251, 87, 23);
        contentPane.add(deleteMemberButton);
        
        JButton adminButton = new JButton("回管理頁");
        adminButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AdminUI frame = new AdminUI();
		        frame.setVisible(true);
		        dispose();
        	}
        });
        adminButton.setBounds(359, 305, 87, 23);
        contentPane.add(adminButton);
        
        JLabel lblNewLabel = new JLabel("您要刪除哪一位會員?");
        lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 14));
        lblNewLabel.setBounds(27, 251, 139, 23);
        contentPane.add(lblNewLabel);
        
        

        // Set JFrame visible
        setVisible(true);
		
		
	}
}
