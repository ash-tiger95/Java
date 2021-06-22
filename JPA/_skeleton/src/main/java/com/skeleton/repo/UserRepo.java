package com.skeleton.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skeleton.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
}
