package com.skeleton.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skeleton.dto.MemberDto;

@Repository
public class MemberRepoImpl implements MemberRepo{

	@Autowired SqlSession sqlSession;

	@Override
	public int join(MemberDto memberDto) {
		return sqlSession.insert("com.skeleton.repo.MemberRepo.join",memberDto);
	}
	
}
