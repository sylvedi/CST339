package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.OrderModel;
import com.gcu.model.OrdersMapper;

@Repository
public class OrdersDataService implements OrdersDataAccessInterface<OrderModel>{
	
	@Autowired
	DataSource dataSource;
	JdbcTemplate jdbcTemplate;
	
	public OrdersDataService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 

	@Override
	public OrderModel getById(int id) {
		// TODO Auto-generated method stub
		OrderModel result = jdbcTemplate.queryForObject(
				"select * from orders where id = ?",
				new OrdersMapper(),
				new Object[] {id});
				
		return result;
	}

	@Override
	public List<OrderModel> getOrders() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(
				"select * from orders",
				new OrdersMapper());
	}

	@Override
	public List<OrderModel> searchOrders(String searchTerm) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(
				"select * from orders where PRODUCT_NAME LIKE ?",
				new OrdersMapper(),
				new Object[] {"%" + searchTerm + "%"});
	}

	@Override
	public int addOne(OrderModel newOrder) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(
				"insert into orders (ID, ORDER_NUMBER, PRODUCT_NAME, PRICE, QTY) values(?,?,?,?,?)",
				newOrder.getId(),
				newOrder.getOrderNo(),
				newOrder.getProductName(),
				newOrder.getPrice(),
				newOrder.getQuantity());
	}

	@Override
	public boolean deleteOne(int id) {
		// TODO Auto-generated method stub
		int updateResult = jdbcTemplate.update(
				"delete from orders where id = ?",
				new Object[] {id} );
		return (updateResult > 0);
	}

	@Override
	public OrderModel updateOne(int idToUpdate, OrderModel updateOrder) {
		// TODO Auto-generated method stub
		int result = jdbcTemplate.update(
				"update orders set ORDER_NUMBER = ?, PRODUCT_NAME =?, PRICE = ?, QTY = ? where id = ?",
				updateOrder.getOrderNo(),
				updateOrder.getProductName(),
				updateOrder.getPrice(),
				updateOrder.getQuantity(),
				idToUpdate );
		if (result > 0) {
			return updateOrder;
		}
		else {
			return null;
		}
	}

}
