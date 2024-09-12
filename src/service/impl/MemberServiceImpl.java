package service.impl;

import java.util.List;

import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService{

	private static MemberDaoImpl mdi = new MemberDaoImpl(); 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMember(/*String membernumber,*/String name, String username, String password, String address, String phone) {
		mdi.addMember(/*membernumber, */name, username, password, address , phone);
	}

	@Override
	public List<Member> findAllMember() {
		List<Member> l = mdi.queryAll();
		return l;
	}

	@Override
	public List<Member> findByUsername(String username){
		List<Member> l = mdi.queryByUsername(username);
		return l;
	}
	
	@Override
	public List<Member> loginMember(String username, String password){
		List<Member> l = mdi.queryMember(username, password);
		return l;
	}
	

	@Override	//msi,mdi的update方法引數不需相同,符合需求即可
	public void updateMember(String name, String username, String password, String address, String phone) {
		List<Member> l = mdi.queryByUsername(username);
		Member m = l.get(0);
		m.setName(name);
		m.setPassword(password);
		m.setAddress(address);
		m.setPhone(phone);
		
		mdi.updateMember(m);
	}

	@Override
	public void deleteMemberById(int id) {
		mdi.deleteMemberById(id);
	}
	
	@Override
	public void deleteMemberByUsername(String username) {
		mdi.deleteMemberByUsername(username);
	}

}
