package com.gcu.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcu.model.User;

@Repository
public interface UserInterface extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
