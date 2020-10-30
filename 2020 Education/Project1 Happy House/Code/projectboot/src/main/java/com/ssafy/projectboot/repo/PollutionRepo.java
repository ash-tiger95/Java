package com.project.projectboot.repo;

import java.util.List;

import com.project.projectboot.dto.PollutionDto;

public interface PollutionRepo {
	public List<PollutionDto> selectAll();
	public List<PollutionDto> search(String word);
}
