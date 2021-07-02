package com.inflearn.jpa.controller;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.inflearn.jpa.entity.Member;
import com.inflearn.jpa.repo.MemberRepo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberRepo memberRepo;

	// [JPA 실전] 12. Web 확장 - 순수 JPA
	@GetMapping("/members/{id}")
	public String findMember(@PathVariable("id") Long id) {
		Member member = memberRepo.findById(id).get();
		return member.getUsername();
	}

	// [JPA 실전] 12. Web 확장 - 도메인 클래스 컨버터
	@GetMapping("/members2/{id}")
	public String findMember2(@PathVariable("id") Member member) {
		return member.getUsername();
	}

	// [JPA 실전] 13. 페이징과 정렬
	@GetMapping("/members")
	public Page<Member> list(@PageableDefault(size = 3, sort = "username") Pageable pageable) {
		Page<Member> page = memberRepo.findAll(pageable);
		return page;
	}

	@PostConstruct
	public void init() {
//		memberRepo.save(new Member("userA"));

		for (int i = 0; i < 100; i++) {
			memberRepo.save(new Member("user" + i, i));
		}
	}
}
