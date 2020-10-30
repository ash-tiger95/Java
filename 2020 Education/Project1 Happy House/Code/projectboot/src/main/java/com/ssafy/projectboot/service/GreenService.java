package com.project.projectboot.service;

import java.util.List;

import com.project.projectboot.dto.GreenDto;

public interface GreenService {

	//녹지 리스트 
	public List<GreenDto> selectGreenList();

}
