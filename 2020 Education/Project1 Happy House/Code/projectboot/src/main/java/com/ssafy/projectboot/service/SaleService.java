package com.project.projectboot.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.project.projectboot.dto.HouseDealinfo;
import com.project.projectboot.dto.SaleImgDto;

public interface SaleService {
	void saveUploadFiles(List<MultipartFile> files, String userid, int saleno, HttpServletRequest request) throws IOException;
	
	public int getSaleNo(); // 마지막 매물 등록 번호 가져오기
	
	public List<SaleImgDto> selectAll();
	public void insertImg(SaleImgDto saleImgDto); 
	
}
