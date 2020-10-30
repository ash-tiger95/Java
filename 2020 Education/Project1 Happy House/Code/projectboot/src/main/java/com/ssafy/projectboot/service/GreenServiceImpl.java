package com.project.projectboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.projectboot.dto.GreenDto;
import com.project.projectboot.repo.GreenRepo;

@Service
public class GreenServiceImpl implements GreenService {

	@Autowired
	private GreenRepo greenRepo;
	
	//녹지 리스트 
	@Override
	public List<GreenDto> selectGreenList() {
		return greenRepo.selectGreenList();
	}

}
