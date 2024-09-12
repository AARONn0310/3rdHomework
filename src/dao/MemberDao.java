package dao;

import java.util.List;

import model.Member;

public interface MemberDao {
	// create
	void addMember(/*String membernumber,*/ String name, String username, String password, String address, String phone);
	
	// read,都是List<>!
	List<Member> queryAll();
	List<Member> queryByUsername(String username);
	List<Member> queryMember(String username, String password);
	
	// update
	void updateMember(Member data);
	
	// delete
	void deleteMemberById(int id);
	void deleteMemberByUsername(String username);
}
