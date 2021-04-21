package com.inflearn.jpabook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.jpabook.domain.Member;
import com.inflearn.jpabook.repo.MemberRepo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepo memberRepo;
	
	/**
	 * 회원 가입
	 * @param member
	 * @return
	 */
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복회원 검사
		memberRepo.save(member);
		
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		// 중복회원이 있으면 Exception
		List<Member> findMembers = memberRepo.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 회원 전체 조회
	public List<Member> findMembers(){
		return memberRepo.findAll();
	}
	
	// 회원 단건 조회
	public Member findOne(Long memberId) {
		return memberRepo.findOne(memberId);
	}
}
