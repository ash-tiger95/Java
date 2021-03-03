package com.sungho;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sungho.repository.MemberRepository;
import com.sungho.repository.MemoryMemberRepository;
import com.sungho.service.MemberService;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
