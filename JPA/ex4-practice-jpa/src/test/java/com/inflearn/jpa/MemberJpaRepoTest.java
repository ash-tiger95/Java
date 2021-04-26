package com.inflearn.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.jpa.entity.Member;
import com.inflearn.jpa.repo.MemberJpaRepo;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberJpaRepoTest {

	@Autowired MemberJpaRepo memberJpaRepository;
	
	@Test
	public void testMember() {
		Member member = new Member("member1");
		Member savedMember = memberJpaRepository.save(member);
		
		Member findMember = memberJpaRepository.find(savedMember.getId());
		
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		
		assertThat(findMember).isEqualTo(member); // JPA에서 같은 인스턴스를 보장한다. (1차 캐시)
	}
	
	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		memberJpaRepository.save(member1);
		memberJpaRepository.save(member2);
		
		// 단건 조회 검증
		Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
		Member findMember2 = memberJpaRepository.findById(member2.getId()).get();
		assertThat(findMember1).isEqualTo(findMember1);
		assertThat(findMember2).isEqualTo(findMember2);
		
		// 리스트 조회 검증
		List<Member> all = memberJpaRepository.findAll();
		assertThat(all.size()).isEqualTo(2);
		
		// 카운트 검증
		long count = memberJpaRepository.count();
		assertThat(count).isEqualTo(2);
		
		// 삭제 검증
		memberJpaRepository.delete(member1);
		memberJpaRepository.delete(member2);
		long deletedCount = memberJpaRepository.count();
		assertThat(deletedCount).isEqualTo(0);
	}
	
	@Test
	public void paging() {
		memberJpaRepository.save(new Member("member1",10));
		memberJpaRepository.save(new Member("member2",10));
		memberJpaRepository.save(new Member("member3",10));
		memberJpaRepository.save(new Member("member4",10));
		memberJpaRepository.save(new Member("member5",10));
		memberJpaRepository.save(new Member("member6",10));
		
		// page1 -> offset=0, limit=10 / page2 -> offset=10, limit=10
		int age = 10;
		int offset = 1;
		int limit = 3;
		
		// when
		List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
		long totalCount = memberJpaRepository.totalCount(age);
		
		// then
		assertThat(members.size()).isEqualTo(3);
		assertThat(totalCount).isEqualTo(6);
	}
}
