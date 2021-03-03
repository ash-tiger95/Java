package com.sungho.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.sungho.domain.Member;

class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	// Test는 순서에 관계없이 설계를 해야한다. 저장소를 깔끔히 비워줘야한다. 그래서 하나의 테스트가 끝날때마다 clear를 시켜줘야한다.
	@AfterEach // 메소드가 끝날 때마다 실행되는 메소드 (콜백메소드 같은 거)
	public void afterEach() {
		repository.clearStore();
	}
	
	
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get(); // optional은 get()으로 꺼낼 수 있다.
//		System.out.println("result = " + (result == member)); // 방법 1
//		Assertions.assertEquals(result, member); // 방법 2 import org.junit.jupiter.api.Assertions;
		Assertions.assertThat(member).isEqualTo(result); // 방법3 import org.assertj.core.api.Assertions;
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		Assertions.assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		Assertions.assertThat(result.size()).isEqualTo(2);
		
	}
}
