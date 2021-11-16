package com.gcu.data;

import java.util.List;

import com.gcu.model.OrderModel;

public interface OrdersDataAccessInterface<T> {
	public T getById(int id);
	public List<T> getOrders();
	public List<T> searchOrders(String searchTerm);
	public int addOne(T newOrder);
	public boolean deleteOne(int id);
	public T updateOne(int idToUpdate, T updateOrder);
}
