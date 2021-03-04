package com.sungho.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sungho.domain.Member;
import com.sungho.repository.MemberRepository;
import com.sungho.repository.MemoryMemberRepository;

@SpringBootTest
@Transactional // 테스트 실행 할 때 트랜잭션 -> 디비에 CRUD -> Rollback 그래서 테스트 디비에 데이터가 안 쌓임
public class MemberServiceIntegrationTest { // 앞에 테스틑 자바, 여기는 스프링
	
	// 테스트 할때는 필드값으로 Autowired (대충해도 되자나)
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	
	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("spring");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		// when
		memberService.join(member1);
//		memberService.join(member2); // 이름이 같으니 여기서 걸리겠지
		
		// 방법1 try-catch로 잡기
		/*
		try {
			memberService.join(member2);
			fail();
		} catch(IllegalStateException e) {
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}
		*/
		
		// 방법2
		IllegalStateException e =  assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		// then
	}
	

}
