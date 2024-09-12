package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import dao.MemberDao;
import model.Member;

public class MemberDaoImpl implements MemberDao{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMember(/*String membernumber, */String name, String username, String password, String address, String phone) {
		Connection conn = DbConnection.getDb();
		String sqlGetLast = "SELECT MAX(membernumber) FROM member";
        
        String sql = "insert into member (membernumber, name, username, password, address, phone) values (?,?,?,?,?,?)";
		

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement psLast = conn.prepareStatement(sqlGetLast);
	        ResultSet rs = psLast.executeQuery();
	        
	        String lastMemberNumber = "m00";
	        if (rs.next()) {
	            lastMemberNumber = rs.getString(1);
	        }
	        
	        // 如果 lastMemberNumber 为 null，设定为 "m00"
	        if (lastMemberNumber == null) {
	            lastMemberNumber = "m00";
	        }
	        
	        // 生成新的会员编号
	        int lastNumber = Integer.parseInt(lastMemberNumber.substring(1));
	        String memberNumber = String.format("m%02d", lastNumber + 1);
	        
			ps.setString(1, memberNumber);
			ps.setString(2, name);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, address);
			ps.setString(6, phone);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Member> queryAll() {
		Connection conn = DbConnection.getDb();
		String sql = "select * from member";
		List<Member> l = new ArrayList<Member>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setId(rs.getInt("id"));
				m.setMembernumber(rs.getString("membernumber"));
				m.setName(rs.getString("name"));
				m.setUsername(rs.getString("username"));
				m.setPassword(rs.getString("password"));
				m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				l.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return l;
	}

	@Override
	public List<Member> queryByUsername(String username) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from member where username = ?";
		List<Member> l = new ArrayList<Member>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Member m = new Member();
				m.setId(rs.getInt("id"));
				m.setMembernumber(rs.getString("membernumber"));
				m.setName(rs.getString("name"));
				m.setUsername(rs.getString("username"));
				m.setPassword(rs.getString("password"));
				m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				l.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return l;
	}

	@Override
	public List<Member> queryMember(String username, String password) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from member where username = ? and password = ?";
	    List<Member> l = new ArrayList<Member>();  // 初始化为 null，表示没有找到结果时返回 null

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, username);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();

	        // 如果找到结果，创建 Member 对象并返回
	        if (rs.next()) {
	            Member m = new Member();
	            m.setId(rs.getInt("id"));
	            m.setMembernumber(rs.getString("membernumber"));
	            m.setName(rs.getString("name"));
	            m.setUsername(rs.getString("username"));
	            m.setPassword(rs.getString("password"));
	            m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				l.add(m);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return l;
	}

	@Override
	public void updateMember(Member m) {
		Connection conn = DbConnection.getDb();
		String sql = "update member set name = ?, password = ?, address = ?, phone = ? "
					+ "where username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getName());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getAddress());
			ps.setString(4, m.getPhone());
			ps.setString(5, m.getUsername());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMemberById(int id) {
		Connection conn = DbConnection.getDb();
		String sql = "delete from member where memberID = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteMemberByUsername(String username) {
		Connection conn = DbConnection.getDb();
		String sql = "delete from member where username = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
