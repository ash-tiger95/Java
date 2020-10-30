package com.project.projectboot.service;

import java.util.List;

import com.project.projectboot.dto.SubwayDto;

public interface SubwayService {

	// 지하철 정보 가져오기
	public List<SubwayDto> listSubway();

}
