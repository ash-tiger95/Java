package com.skeleton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeleton.dto.MemberDto;
import com.skeleton.repo.MemberRepo;


@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepo memberRepo;
	
	@Override
	public int join(MemberDto memberDto){
		
		return memberRepo.join(memberDto);
	}
}