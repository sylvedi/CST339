package com.gcu.business;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gcu.dto.UserRegistrationDto;
import com.gcu.model.User;

public interface UserService extends UserDetailsService{
	User save (UserRegistrationDto registrationDto);
}
