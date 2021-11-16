package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.model.OrderEntity;

@Service
public class OrdersDataServiceForRepository implements OrdersDataAccessInterface<OrderEntity>{

	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public OrdersDataServiceForRepository(OrdersRepository ordersRepository, DataSource dataSource)
	{
		this.ordersRepository = ordersRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public OrderEntity getById(int id) {
		// TODO Auto-generated method stub
		return ordersRepository.findById((long) id).orElse(null);
	}

	@Override
	public List<OrderEntity> getOrders() {
		// TODO Auto-generated method stub
		List<OrderEntity> orders = (List<OrderEntity>) ordersRepository.findAll();
		 
		 return orders;
	}

	@Override
	public List<OrderEntity> searchOrders(String searchTerm) {
		// TODO Auto-generated method stub
		List<OrderEntity> result = ordersRepository.findByProductNameContainingIgnoreCase(searchTerm);
		return result;
	}

	@Override
	public int addOne(OrderEntity newOrder) {
		// TODO Auto-generated method stub
		OrderEntity result = ordersRepository.save(newOrder);
		if (result == null) {
			return 0;
		}
		return (int) result.getId();
	}

	@Override
	public boolean deleteOne(int id) {
		// TODO Auto-generated method stub
		ordersRepository.deleteById((long) id);
		return true;
	}

	@Override
	public OrderEntity updateOne(int idToUpdate, OrderEntity updateOrder) {
		// TODO Auto-generated method stub
		OrderEntity result = ordersRepository.save(updateOrder);
		return result;
	}

}
