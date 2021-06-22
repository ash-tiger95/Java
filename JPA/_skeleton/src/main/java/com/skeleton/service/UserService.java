package com.skeleton.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeleton.domain.User;
import com.skeleton.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepo userRepo;
	
	@Transactional
	public User findById(Long id) {
		return userRepo.findById(id).get(); // selectOne, 단일 검색
	}
	
	@Transactional
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	@Transactional
	public void signUp(User user) {
		userRepo.save(user); // insert, update
	}
	
	@Transactional
	public void removeById(Long id) {
		userRepo.deleteById(id); // delete
	}
}
