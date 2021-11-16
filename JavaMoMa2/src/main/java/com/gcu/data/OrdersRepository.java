package com.gcu.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gcu.model.OrderEntity;

public interface OrdersRepository extends CrudRepository<OrderEntity, Long>{
	List<OrderEntity> findByProductNameContainingIgnoreCase(String searchTerm);
}
