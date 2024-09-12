package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CartDao;
import dao.DbConnection;
import model.Cart;
import service.impl.CartServiceImpl;

public class CartDaoImpl implements CartDao{
	private static final Connection conn = DbConnection.getDb();
	
	public static void main(String[] args) {
		
		/*CartDaoImpl cdi = new CartDaoImpl();
		Cart c = new Cart(1, 10, 10);
		c.setOrderID(31);
		
		cdi.updateCartByorderID(c);*/
	}

	@Override
	public void addCart(String username, /*String orderNumber, */Integer gameA, 
			Integer gameB, Integer gameC, String membership, Double sum, String date) {
		String sqlGetLast = "SELECT MAX(ordernumber) FROM cart";
		String SQL="insert into cart(username,ordernumber, "
				+ "gameA,gameB,gameC,membership,sum, date) "
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			PreparedStatement psLast = conn.prepareStatement(sqlGetLast);
			ResultSet rs = psLast.executeQuery();
			String lastOrderNumber = "c00";
	        if (rs.next()) {
	            lastOrderNumber = rs.getString(1);
	        }
	        
	     // 如果 lastMemberNumber 为 null，设定为 "m00"
	        if (lastOrderNumber == null) {
	            lastOrderNumber = "c00";
	        }
	        
	        int lastNumber = Integer.parseInt(lastOrderNumber.substring(1));
	        String cartNumber = String.format("c%02d", lastNumber + 1);
	        
			ps.setString(1, username);
			ps.setString(2, cartNumber);
			ps.setInt(3, gameA);
			ps.setInt(4, gameB);
			ps.setInt(5, gameC);
			ps.setString(6, membership);
			ps.setDouble(7, sum);
			
			// 处理日期
	        if (date == null || date.isEmpty()) {
	            ps.setString(8, java.time.LocalDate.now().toString());  // 如果没有传递日期，使用当前日期
	        } else {
	            ps.setString(8, date);  // 使用传递的日期
	        }
			System.out.println("Username to be inserted: " + username);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Cart> queryAll() {
		
		String SQL="select * from cart";
		List<Cart> l=new ArrayList<>();
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Cart c=new Cart();
				c.setOrderID(rs.getInt("orderID"));
				c.setUsername(rs.getString("username"));
				c.setOrdernumber(rs.getString("orderNumber"));
				c.setGameA(rs.getInt("gameA"));	
				c.setGameB(rs.getInt("gameB"));	
				c.setGameC(rs.getInt("gameC"));
				c.setMembership(rs.getString("membership"));
				c.setSum(rs.getDouble("sum"));
				c.setDate(rs.getString("date"));
				
				l.add(c);
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return l;
	}
	
	@Override
	public List<Cart> queryByUsername(String username) {
		List<Cart> l = new ArrayList<Cart>();
		String SQL = "select * from cart where username = ? ORDER BY ordernumber DESC";
		
		
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Cart c = new Cart();
				c.setOrderID(rs.getInt("orderID"));
				c.setOrdernumber(rs.getString("ordernumber"));
				c.setGameA(rs.getInt("gameA"));
				c.setGameB(rs.getInt("gameB"));
				c.setGameC(rs.getInt("gameC"));
				c.setMembership(rs.getString("membership"));
				c.setSum(rs.getDouble("sum"));
				c.setDate(rs.getString("date"));
				l.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public List<Cart> queryByorderID(Integer id) {
		List<Cart> l = new ArrayList<Cart>();
		String SQL = "select * from cart where orderID = ? ORDER BY orderID DESC";
		
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Cart c = new Cart();
				c.setOrderID(rs.getInt("orderID"));
				c.setOrdernumber(rs.getString("ordernumber"));
				c.setGameA(rs.getInt("gameA"));
				c.setGameB(rs.getInt("gameB"));
				c.setGameC(rs.getInt("gameC"));
				c.setMembership(rs.getString("membership"));
				c.setSum(rs.getDouble("sum"));
				c.setDate(rs.getString("date"));
				l.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Cart> queryByOrderNumber(String ordernumber) {
		String SQL = "select * from cart where ordernumber = ?";
		List<Cart> l = new ArrayList<>();
		
		try (PreparedStatement ps = conn.prepareStatement(SQL)) {
	        ps.setString(1, ordernumber);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Cart c = new Cart();
	                c.setOrderID(rs.getInt("orderID"));
	                c.setUsername(rs.getString("username"));
	                c.setGameA(rs.getInt("gameA"));
	                c.setGameB(rs.getInt("gameB"));
	                c.setGameC(rs.getInt("gameC"));
	                c.setMembership(rs.getString("membership"));
	                c.setSum(rs.getDouble("sum"));
	                c.setDate(rs.getString("date"));
	                l.add(c);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return l;
	}

	
	@Override
	public void updateCartByorderID(Cart c) {
		String SQL="update cart set gameA=?,gameB=?,gameC=?,"
				+ " membership = ?, sum = ? where orderID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			
			ps.setInt(1, c.getGameA());
			ps.setInt(2, c.getGameB());
			ps.setInt(3, c.getGameC());
			ps.setString(4, c.getMembership());
			ps.setDouble(5, c.getSum());
			
			ps.setInt(6, c.getOrderID());
			System.out.println("Executing SQL: " + SQL);
	        System.out.println("Parameters: " +
	                           c.getGameA() + ", " +
	                           c.getGameB() + ", " +
	                           c.getGameC() + ", " +
	                           c.getMembership() + ", " +
	                           c.getSum() + ", " +
	                           c.getOrderID());
	        int rowsUpdated = ps.executeUpdate();
	        System.out.println("Rows updated: " + rowsUpdated);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void updateCart(Cart c) {
		String SQL="update cart set gameA=?,gameB=?,gameC=?,membership = ?,"
				+ "sum = ? where ordernumber = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			
			ps.setInt(1, c.getGameA());
			ps.setInt(2, c.getGameB());
			ps.setInt(3, c.getGameC());
			ps.setString(4, c.getMembership());
			ps.setDouble(5, c.getSum());
			ps.setString(6, c.getOrdernumber());
	        System.out.println("Parameters: " +
	                           c.getGameA() + ", " +
	                           c.getGameB() + ", " +
	                           c.getGameC() + ", " +
	                           c.getMembership() + ", " +
	                           c.getSum() + ", " +
	                           c.getOrdernumber());
	        int rowsUpdated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateDate(Cart c) {
		String SQL="update cart set date=? where ordernumber = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			
			ps.setString(1, c.getDate());
			ps.setString(2, c.getOrdernumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCartByOrderNumber(String orderNumber) {
		String SQL="delete from cart where ordernumber = ?";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			
			ps.setString(1, orderNumber);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteCartByorderID(Integer orderID) {
		String SQL="delete from cart where orderID = ?";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			
			ps.setInt(1, orderID);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Integer getLatestOrderID() {
	    Integer latestOrderID = null;
	    String query = "SELECT orderID FROM cart ORDER BY orderID DESC LIMIT 1";
	    
	    try (
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {
	        
	        if (rs.next()) {
	            latestOrderID = rs.getInt("orderID");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return latestOrderID;
	}
	

}
