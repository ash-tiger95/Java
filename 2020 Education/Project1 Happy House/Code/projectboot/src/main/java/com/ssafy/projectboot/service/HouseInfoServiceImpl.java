package com.project.projectboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.projectboot.dto.HouseInfoDto;
import com.project.projectboot.repo.HouseInfoRepo;

@Service
public class HouseInfoServiceImpl implements HouseInfoService{

	@Autowired
	private HouseInfoRepo houseinfoRepo;
	
	@Override
	public void insertHouseInfo(HouseInfoDto houseinfo) {
		houseinfoRepo.insertHouseInfo(houseinfo);
	}
	
}
