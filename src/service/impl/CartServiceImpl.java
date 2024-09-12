package service.impl;

import java.util.List;

import dao.impl.CartDaoImpl;
import model.Cart;
import service.CartService;

public class CartServiceImpl implements CartService{

	private static CartDaoImpl cdi = new CartDaoImpl(); 
	
	public static void main(String[] args) {
		
	}

	@Override
	public void addCart(String username, Integer gameA, Integer gameB, Integer gameC,
			String membership,Double sum, String date) {
		cdi.addCart(username, gameA, gameB, gameC,membership, sum, date);
		
	}

	@Override
	public List<Cart> findAllCart() {
		List<Cart> l = cdi.queryAll();
		return l;
	}

	@Override
	public List<Cart> findByUsername(String username) {
		List<Cart> l = cdi.queryByUsername(username);
		return l;
	}
	
	@Override
	public List<Cart> findById(String ordernumber) {
		List<Cart> l = cdi.queryByOrderNumber(ordernumber);
		return l;
	}
	
	@Override
	public void updateCartByorderID(Integer orderID, Integer gameA, Integer gameB, Integer gameC, Double sum) {
		List<Cart> l = cdi.queryByorderID(orderID);
		if (l.isEmpty()) {
	        System.out.println("No cart found with orderID " + orderID);
	        return;
	    }else {
	    	System.out.println(l.size());
	    }
		Cart c = l.get(0);
		System.out.println("Current cart details: " + c);
	    System.out.println("OrderID: " + c.getOrderID()); // Ensure ordernumber is set
	   
		c.setGameA(gameA);
		c.setGameB(gameB);
		c.setGameC(gameC);
		c.setSum(sum);
		
		cdi.updateCart(c);
	}

	@Override
	public void updateCartByOrderNumber(String ordernumber, Integer gameA, Integer gameB, Integer gameC,
			Double sum) {
		List<Cart> l = cdi.queryByOrderNumber(ordernumber);
		if (l.isEmpty()) {
	        System.out.println("No cart found with order number: " + ordernumber);
	        return;
	    }else {
	    	System.out.println(l.size());
	    }
		Cart c = l.get(0);
		System.out.println("Current cart details: " + c);
	    System.out.println("Order Number: " + c.getOrdernumber()); // Ensure ordernumber is set
	   
		c.setGameA(gameA);
		c.setGameB(gameB);
		c.setGameC(gameC);
		c.setSum(sum);
		
		cdi.updateCart(c);
	}
	
	@Override
	public void updateDate(Cart c,String date) {
		c.setDate(date);
    
	    // 使用CartDaoImpl的updateDate方法更新数据库中的日期
	    cdi.updateDate(c);
		
	}

	@Override
	public void deleteCartByOrderNumber(String ordernumber) {
		cdi.deleteCartByOrderNumber(ordernumber);
	}

	@Override
	public void deleteCartByorderID(Integer orderID) {
		cdi.deleteCartByorderID(orderID);
	}
	
	@Override
	public Integer getLatestOrderID() {
	    return cdi.getLatestOrderID(); // 调用 CartDaoImpl 中的方法
	}


	

	

	
}
