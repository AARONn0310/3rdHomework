package dao;

import java.util.List;

import model.Cart;

public interface CartDao {
	// create
	void addCart(String username, /*String orderNumber, */
			Integer gameA, Integer gameB, Integer gameC, String membership, Double sum,String date);
	
	// read
	List<Cart> queryAll();
	List<Cart> queryByOrderNumber(String ordernumber);
	List<Cart> queryByUsername(String username);
	List<Cart> queryByorderID(Integer id);
	
	// update
	void updateCart(Cart c);
	void updateCartByorderID(Cart c);
	void updateDate(Cart c);
	
	// delete
	void deleteCartByOrderNumber(String ordernumber);

	void deleteCartByorderID(Integer orderID);

	//獲得最新id
	Integer getLatestOrderID();

	

	
}
