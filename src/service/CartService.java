package service;

import java.util.List;

import model.Cart;

public interface CartService {
	//create
	void addCart(String membername,/* String ordernumber, */
			Integer gameA, Integer gameB,Integer gameC,
			String membership, Double sum, String date);
	
	
	//read
	List<Cart> findAllCart();
	List<Cart> findByUsername(String username);
	List<Cart> findById(String ordernumber);
	
	//update
	void updateCartByOrderNumber(String ordernumber, Integer gameA, Integer gameB,Integer gameC,Double sum);
	void updateDate(Cart c,String date);
	void updateCartByorderID(Integer orderID, Integer gameA, Integer gameB,Integer gameC,Double sum);
	
	
	//delete
	void deleteCartByOrderNumber(String ordernumber);
	void deleteCartByorderID(Integer orderID);

	//獲得最新id
	Integer getLatestOrderID();


}
