package com.project.projectboot.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.projectboot.dto.HouseInfoDto;

@Repository
public class HouseInfoRepoImpl implements HouseInfoRepo{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertHouseInfo(HouseInfoDto houseinfo) {
		sqlSession.insert("com.project.projectboot.repo.HouseInfoRepo.insertHouseInfo",houseinfo);
	}

}
