package com.gcu.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrdersMapper implements RowMapper<OrderModel>{

	@Override
	public OrderModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		OrderModel order = new OrderModel(
				resultSet.getInt("ID"),
				resultSet.getString("ORDER_NUMBER"),
				resultSet.getString("PRODUCT_NAME"),
				resultSet.getFloat("PRICE"),
				resultSet.getInt("QTY")
				);
		
		return order;

	}

}
