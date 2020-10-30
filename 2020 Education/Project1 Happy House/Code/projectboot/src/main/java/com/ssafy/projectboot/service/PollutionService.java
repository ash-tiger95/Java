package com.project.projectboot.service;

import java.util.List;

import com.project.projectboot.dto.PollutionDto;

public interface PollutionService {
	public List<PollutionDto> selectAll();
	public List<PollutionDto> search(String word);
}
