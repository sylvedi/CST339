package com.gcu.business;

import java.util.List;

import com.gcu.model.OrderModel;

public interface OrdersBusinessServiceInterface {
	public void test();
	public List<OrderModel> getOrders();
	public OrderModel getOne(int id);
	public List<OrderModel> searchOrders(String searchTerm);
	public int addOne(OrderModel newOrder);
	public boolean deleteOne(int id);
	public OrderModel updateOne(int idToUpdate, OrderModel updateOrder);
	public void init();
	public void destroy();
}
