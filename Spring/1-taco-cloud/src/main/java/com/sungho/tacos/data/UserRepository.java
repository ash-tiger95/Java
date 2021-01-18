package com.sungho.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.sungho.tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
}