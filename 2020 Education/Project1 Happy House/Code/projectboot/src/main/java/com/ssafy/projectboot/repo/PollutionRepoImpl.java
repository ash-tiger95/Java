package com.project.projectboot.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.projectboot.dto.PollutionDto;

@Repository
public class PollutionRepoImpl implements PollutionRepo{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PollutionDto> selectAll() {
		return sqlSession.selectList("com.project.projectboot.repo.PollutionRepo.selectAll");
	}

	@Override
	public List<PollutionDto> search(String word) {
		return sqlSession.selectList("com.project.projectboot.repo.PollutionRepo.search", word);
	}
	
	
}
