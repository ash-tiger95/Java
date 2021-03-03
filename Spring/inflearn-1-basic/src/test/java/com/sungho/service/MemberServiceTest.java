package com.sungho.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sungho.domain.Member;
import com.sungho.repository.MemoryMemberRepository;

public class MemberServiceTest { // test는 과감히 한글로 적어도 된다.
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	/*
	 * given, when, then 형식으로 짜면 도움이 된다.
	 * given: 뭐가 주어졌을 때
	 * when: 이걸 실행했을 때
	 * then: 결과가 이것이 나와야 한다.
	 */
	
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
