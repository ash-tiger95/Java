package com.project.projectboot.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.projectboot.dto.NoticeDto;

@Repository
public class NoticeRepoImpl implements NoticeRepo{
	
	@Autowired
	private SqlSession sqlSession;

	// 목록 보기
	@Override
	public List<NoticeDto> listNotice() {
		return sqlSession.selectList("com.project.projectboot.repo.NoticeRepo.listNotice");
	}

	// 글 하나 가져오기
	@Override
	public NoticeDto getNotice(int articleno) {
		return sqlSession.selectOne("com.project.projectboot.repo.NoticeRepo.getNotice", articleno);
	}

	// 글쓰기
	@Override
	public void writeNotice(NoticeDto noticeDto) {
		sqlSession.insert("com.project.projectboot.repo.NoticeRepo.writeNotice",noticeDto);
	}

	// 글 수정
	@Override
	public void modifyNotice(NoticeDto noticeDto) {
		sqlSession.update("com.project.projectboot.repo.NoticeRepo.modifyNotice", noticeDto);
	}

	//글 삭제
	@Override
	public void deleteNotice(int articleno) {
		sqlSession.delete("com.project.projectboot.repo.NoticeRepo.deleteNotice", articleno);
	}
	
	
}
