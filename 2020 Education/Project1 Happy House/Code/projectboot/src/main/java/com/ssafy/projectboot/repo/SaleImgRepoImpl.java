package com.project.projectboot.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.projectboot.dto.HouseDealinfo;
import com.project.projectboot.dto.SaleImgDto;

@Repository
public class SaleImgRepoImpl implements SaleImgRepo{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getSaleNo() {
		return sqlSession.selectOne("com.project.projectboot.repo.SaleImgRepo.getSaleNo");
	}
	
	@Override
	public List<SaleImgDto> selectAll() {
		return sqlSession.selectList("com.project.projectboot.repo.SaleImgRepo.selectAll");
	}

	@Override
	public void insertImg(SaleImgDto saleImgDto) {
		sqlSession.insert("com.project.projectboot.repo.SaleImgRepo.insertImg", saleImgDto);
	}

}
