package com.project.projectboot.repo;

import java.util.List;

import com.project.projectboot.dto.HouseDealDto;
import com.project.projectboot.dto.HouseInfoDto;
import com.project.projectboot.dto.SiDoCodeDto;

public interface SelectBoxRepo {
	List<SiDoCodeDto> selectSido();
	List<SiDoCodeDto> selectGugun(String sido);
	List<HouseInfoDto> selectDong(String gugun);
	List<HouseDealDto> selectApt(String dong);
	List<HouseInfoDto> selectHouseInfo(String dong);
	// 구,동으로 매물 검색 + 페이징
	List<HouseDealDto> selectAptbyGudong(String gugun,String dong, int pageNo, int spp);
	// 구,동으로 매물검색해서 총갯수 리턴
	int dealCount(String gugun, String dong);
}
