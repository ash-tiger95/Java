package com.skeleton.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skeleton.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
}
