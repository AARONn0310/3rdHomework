package service;

import java.util.List;

import model.Member;

public interface MemberService {
	//create
	void addMember(/*String membernumber,*/ String name, String username, String password, String address, String phone);
	
	//read
	List<Member> findAllMember();
	List<Member> findByUsername(String username);
	List<Member> loginMember(String username, String password);
	
	//update
	void updateMember(String name, String username, String password, String address, String phone);
	
	//delete
	void deleteMemberById(int id);
	void deleteMemberByUsername(String username);

	
}
