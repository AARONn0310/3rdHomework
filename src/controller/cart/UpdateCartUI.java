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
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class UpdateCartUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table; // 声明JTable为类成员变量
    private DefaultTableModel model; // 声明TableModel为类成员变量
    private JTextField aField;
    private JTextField bField;
    private JTextField cField;
    private JComboBox<Cart> comboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Cart cart = new Cart();
                Member m = new Member("testUser");
                String membership = "3";
                LocalDateTime l = LocalDateTime.now();
                DateTimeFormatter t1 = DateTimeFormatter.ISO_LOCAL_DATE;
                String l2 = l.format(t1);
                cart.setDate(l2);
                UpdateCartUI frame = new UpdateCartUI(cart, m, membership);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateCartUI(Cart cart, Member m, String membership) {
        setTitle("更新訂單頁面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Define column names
        String[] columnNames = {"流水號", "會員編號", "訂單編號", "A遊戲數量", "B遊戲數量", "C遊戲數量", "應付金額", "訂單成立時間"};
        
        model = new DefaultTableModel(columnNames, 0);

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
                c.calculateDiscountedPrice(membership),               // 應付金額
                c.getDate()
            };
            model.addRow(row);
        }

        // Create JTable
        table = new JTable(model);
        table.getColumnModel().getColumn(7).setPreferredWidth(300); // 增加列宽
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 664, 181);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
                exitButton.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(UIManager.getColor("Button.background"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        exitButton.setForeground(Color.BLACK);
        exitButton.setBorder(new LineBorder(Color.white));
        exitButton.setBounds(553, 322, 89, 23);
        panel.add(exitButton);

        JButton memberUIButton = new JButton("回會員頁面");
        memberUIButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MemberUI mui = new MemberUI(m);
                mui.setVisible(true);
                dispose();
            }
        });
        memberUIButton.setBounds(523, 276, 119, 23);
        panel.add(memberUIButton);

        JLabel lblNewLabel = new JLabel("您希望修改哪一筆訂單?流水號為:");
        lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 14));
        lblNewLabel.setBounds(26, 201, 219, 23);
        panel.add(lblNewLabel);

        comboBox = new JComboBox<>();
        comboBox.setBounds(256, 201, 93, 23);
        panel.add(comboBox);
        for (Cart cartno : cartList) {
            comboBox.addItem(cartno);
        }

     // 自定义渲染器
        comboBox.setRenderer(new ListCellRenderer<Cart>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Cart> list, Cart value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel();
                if (value != null) {
                    label.setText(String.valueOf(value.getOrderID()));  // 显示订单编号
                } else {
                    label.setText("无订单");  // 或者设置为其他适当的提示文本
                }
                if (isSelected) {
                    label.setBackground(Color.LIGHT_GRAY);
                    label.setOpaque(true);
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        });
        
        JLabel lblNewLabel_1 = new JLabel("A遊戲數量:");
        lblNewLabel_1.setBounds(26, 234, 70, 23);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("B遊戲數量:");
        lblNewLabel_1_1.setBounds(26, 276, 70, 23);
        panel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("C遊戲數量:");
        lblNewLabel_1_2.setBounds(26, 322, 70, 23);
        panel.add(lblNewLabel_1_2);
        
        aField = new JTextField();
        aField.setBounds(106, 235, 96, 21);
        panel.add(aField);
        aField.setColumns(10);
        
        bField = new JTextField();
        bField.setColumns(10);
        bField.setBounds(106, 277, 96, 21);
        panel.add(bField);
        
        cField = new JTextField();
        cField.setColumns(10);
        cField.setBounds(106, 323, 96, 21);
        panel.add(cField);
        
        JButton updateButton = new JButton("更新");
        updateButton.setBounds(262, 322, 87, 23);
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 获取选中的订单编号
                Cart selectedCart = (Cart) comboBox.getSelectedItem();
                
                if (selectedCart != null) {
                    Integer orderID = selectedCart.getOrderID();

                    // 从文本框中获取并解析游戏数量
                    Integer gameA = parseTextField(aField);
                    Integer gameB = parseTextField(bField);
                    Integer gameC = parseTextField(cField);
                    
                 

                    // 计算新的总金额
                    Cart c = new Cart(gameA, gameB, gameC,membership);
                    Double sum = c.getSum();
                    
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                    String formattedDate = now.format(formatter);
                    c.setDate(formattedDate);
                    
                    // 使用CartServiceImpl更新订单
                    CartServiceImpl csi = new CartServiceImpl();
                    csi.updateCartByorderID(orderID, gameA, gameB, gameC, sum);
                    csi.updateDate(c, formattedDate); 
                    
                    UpdateCartUI frame = new UpdateCartUI(cart, m, membership);
                    frame.setVisible(true);
                    dispose();

                }
            }
        });

        
        panel.add(updateButton);
        
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
		printButton.setBounds(553, 235, 89, 21);
		panel.add(printButton);
        
    }
    
    
        
    // 辅助方法：从文本框中获取并解析整数值，处理空白和负数
    private Integer parseTextField(JTextField textField) { 
        try {
            String text = textField.getText().trim();
            if (text.isEmpty()) {
                return 0;
            }
            Integer value = Integer.parseInt(text);
            return (value >= 0) ? value : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
}
