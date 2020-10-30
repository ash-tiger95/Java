package com.project.projectboot.repo;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.projectboot.dto.HouseDealDto;
import com.project.projectboot.dto.HouseDealinfo;
import com.project.projectboot.dto.HouseInfoDto;
import com.project.projectboot.dto.LocationDto;

@Repository
public class HouseDealRepoImpl implements HouseDealRepo{

	@Autowired
	private SqlSession sqlSession;

	//전체 매매거래 목록 가져오기
	@Override
	public List<HouseDealinfo> listHouseDealinfo() {
		return sqlSession.selectList("com.project.projectboot.repo.HouseDealRepo.listHouseDealinfo");
	}

	//전체 집정보 목록 가져오기
	@Override
	public List<HouseInfoDto> listHouseinfo() {
		return sqlSession.selectList("com.project.projectboot.repo.HouseDealRepo.listHouseinfo");
	}
	
	// 아파트명+동 으로 매물정보 가져오기
	@Override
	public List<HouseDealDto> listHouseDeal(String aptname, String dong, String pageNo, String spp) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		int pagenum = Integer.parseInt(pageNo);
		int sppnum = Integer.parseInt(spp);
		int start = (pagenum-1)*sppnum;
		RowBounds row = new RowBounds(start, sppnum);
		map.put("aptname", aptname);
		map.put("dong", dong);
		return sqlSession.selectList("com.project.projectboot.repo.HouseDealRepo.listHouseDeal",map,row);
	}

	// 아파트명+동 으로 매물정보 검색시 총 갯수 얻기.
	@Override
	public int dealCount(String aptname, String dong) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("aptname", aptname);
		map.put("dong", dong);
		return sqlSession.selectOne("com.project.projectboot.repo.HouseDealRepo.dealCount", map);
	}

	// 코드 가져오기
	@Override
	public List<LocationDto> listCode() {
		return sqlSession.selectList("com.project.projectboot.repo.HouseDealRepo.listCode");
	}

	// 매물번호로 특정매물 가져오기
	@Override
	public HouseDealinfo getHouseDealinfo(int no) {
		return sqlSession.selectOne("com.project.projectboot.repo.HouseDealRepo.getHouseDealinfo",no);
	}

	// 매물등록페이지 -> 매물 등록하기
	@Override
	public void insertHouseDeal(HouseDealDto houseDealDto) {
		sqlSession.insert("com.project.projectboot.repo.HouseDealRepo.insertHouseDeal", houseDealDto);
	}

	// 매물등록페이지 -> 내가 등록한 매물 불러오기
	@Override
	public List<HouseDealDto> searchUserRegister(String userid) {
		return sqlSession.selectList("com.project.projectboot.repo.HouseDealRepo.searchUserRegister", userid);
	}

	// 매물등록페이지 -> 상세정보
	@Override
	public HouseDealDto getHouseDeal(int saleno) {
		return sqlSession.selectOne("com.project.projectboot.repo.HouseDealRepo.getHouseDeal", saleno);
	}

	// 매물등록페이지 -> 업데이트
	@Override
	public void updateHouseDeal(HouseDealDto housedealDto, int saleno) {
		sqlSession.update("com.project.projectboot.repo.HouseDealRepo.updateHouseDeal",housedealDto);
	}

	// 매물등록페이지 -> 삭제
	@Override
	public void deleteHouseDeal(int saleno) {
		sqlSession.delete("com.project.projectboot.repo.HouseDealRepo.deleteHouseDeal",saleno);
	}

	@Override
	public void selectDelete(int saleno) {
		sqlSession.delete("com.project.projectboot.repo.HouseDealRepo.selectDelete", saleno);
	}
	
	// 매물등록페이지 -> 최근 등록 매물 불러오기
	public List<HouseDealDto> getRecentList(){
		return sqlSession.selectList("com.project.projectboot.repo.HouseDealRepo.getRecentList");
	}
}
