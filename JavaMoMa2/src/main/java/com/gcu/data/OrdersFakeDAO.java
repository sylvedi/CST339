package com.gcu.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.gcu.model.OrderModel;

@Repository
public class OrdersFakeDAO implements OrdersDataAccessInterface<OrderModel>{

	private List<OrderModel> orders = new ArrayList<OrderModel>();
	
	public OrdersFakeDAO() {
		orders = new ArrayList<OrderModel>();
		orders.add(new OrderModel(0, "001", "Sky diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(1, "002", "Run diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(2, "003", "Walk diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(3, "004", "Dance diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(4, "005", "Swim diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(5, "006", "Zip diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(6, "007", "Sled diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(7, "008", "Dive diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(8, "009", "Lane diving expereince", 1500.0f, 1));
		orders.add(new OrderModel(9, "010", "Hike diving expereince", 1500.0f, 1));
		}

	@Override
	public OrderModel getById(int id) {
		// TODO Auto-generated method stub
		return orders.stream()
				.filter(order -> order.getId() == id)
				.findFirst()
				.get();
	}

	@Override
	public List<OrderModel> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	public List<OrderModel> searchOrders(String searchTerm) {
		// TODO Auto-generated method stub
		List<OrderModel> foundItems = orders
				.stream()
				.filter(order -> order.getProductName().toLowerCase()
						.contains(searchTerm.toString().toLowerCase()))
				.collect(Collectors.toList());
		
		System.out.println("I search for " + searchTerm + "and found " + foundItems.size() + "items ");
		return foundItems;
	}

	@Override
	public int addOne(OrderModel newOrder) {
		// TODO Auto-generated method stub
		boolean success = orders.add(newOrder);
		System.out.println("I added one item. Now there are" + orders.size() + " items in the list");
		if (success) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean deleteOne(int id) {
		// TODO Auto-generated method stub
	orders.removeIf(order -> order.getId() == id);
		
		// for testing, print a status message to the server console
		System.out.println("I removed one item. Now there are " + orders.size() + " items in the list");
		return true;
	}

	@Override
	public OrderModel updateOne(int idToUpdate, OrderModel updateOrder) {
		// TODO Auto-generated method stub
		orders.stream().forEach(order -> {
			if (order.getId() == idToUpdate) {
				order.setOrderNo(updateOrder.getOrderNo());
				order.setPrice(updateOrder.getPrice());
				order.setProductName(updateOrder.getProductName());
				order.setQuantity(updateOrder.getQuantity());
				System.out.println("Updated order " + updateOrder);
				
			}
		});
		
		// for testing print a status message to the server console.
		System.out.println("You asked me to update order number " + idToUpdate + ". The updated order is " +
		updateOrder.toString());
		
		System.out.println("I tried to find order number " + idToUpdate + " but couldn't find one that matches");
		return null;
	}
	
}
