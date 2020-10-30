package com.project.projectboot.repo;

import java.util.List;

import com.project.projectboot.dto.GreenDto;

public interface GreenRepo {

	//녹지 리스트 
	public List<GreenDto> selectGreenList();
}
