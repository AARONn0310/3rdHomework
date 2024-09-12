package service;

import java.sql.Connection;

public interface OrderService {
	//生成訂單編號
	String generateOrderNumber(Connection conn);
}
