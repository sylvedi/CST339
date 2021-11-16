package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.OrdersDataAccessInterface;
import com.gcu.model.OrderModel;

public class AnotherOrdersBusinessService implements OrdersBusinessServiceInterface {
	
	@Autowired
	OrdersDataAccessInterface ordersDAO;

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Another OrderBusinessService");
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("The test method of the ANOTHER OrdersBusinessService appears to be working if you can see this text");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy method of ANOTHER OrderBusinessService was just called");
		
	}

	@Override
	public List<OrderModel> getOrders() {
		// TODO Auto-generated method stub
		return ordersDAO.getOrders();
	}

	@Override
	public OrderModel getOne(int id) {
		// TODO Auto-generated method stub
		return (OrderModel) ordersDAO.getById(id);
	}

	@Override
	public List<OrderModel> searchOrders(String searchTerm) {
		// TODO Auto-generated method stub
		return ordersDAO.searchOrders(searchTerm);
	}

	@Override
	public int addOne(OrderModel newOrder) {
		// TODO Auto-generated method stub
		return ordersDAO.addOne(newOrder);
	}

	@Override
	public boolean deleteOne(int id) {
		// TODO Auto-generated method stub
		return ordersDAO.deleteOne(id);
	}

	@Override
	public OrderModel updateOne(int idToUpdate, OrderModel updateOrder) {
		// TODO Auto-generated method stub
		return (OrderModel) ordersDAO.updateOne(idToUpdate, updateOrder);
	}



}
