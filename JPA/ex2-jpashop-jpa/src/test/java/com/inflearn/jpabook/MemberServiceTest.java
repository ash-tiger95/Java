package com.inflearn.jpabook;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.jpabook.domain.Member;
import com.inflearn.jpabook.repo.MemberRepo;
import com.inflearn.jpabook.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepo memberRepo;
	@Autowired EntityManager em;
	
	@Test
	public void 회원가입() throws Exception{
		// given (주어졌을 때)
		Member member = new Member();
		member.setName("kim");
		
		// when (실행할 때)
		Long saveId = memberService.join(member);
		
		// then (결과)
		em.flush();
		assertEquals(member, memberRepo.findOne(saveId));
		
	}
	
	@Test
	public void 중복_회원_예외() throws Exception{
		Member member1  = new Member();
		member1.setName("kim");
		
		Member member2= new Member();
		member2.setName("kim");
		
		memberService.join(member1);
		try {
			
			memberService.join(member2);
		} catch(IllegalStateException e) {
			return;
		}
		
		fail("예외가 발생해야 한다.");
	}
}