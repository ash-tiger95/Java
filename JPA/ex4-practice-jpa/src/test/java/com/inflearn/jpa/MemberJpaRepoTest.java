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
import com.inflearn.jpa.repo.MemberRepo;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberJpaRepoTest {

	@Autowired
	MemberJpaRepo memberJpaRepository;

	/*
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

		findMember1.setUsername("member!!!!");

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
	public void findByUsernameAndAgeGreaterThanTest() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("AAA", 20);
		Member m3 = new Member("AAA", 30);
		Member m4 = new Member("AAA", 40);

		memberJpaRepository.save(m1);
		memberJpaRepository.save(m2);
		memberJpaRepository.save(m3);
		memberJpaRepository.save(m4);

		List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

		for (Member m : result) {
			System.out.println(m.getUsername() + " " + m.getAge());
		}
	}
	
	@Test
	public void testNamedQuery() {
		Member m1 = new Member("BBB", 10);
		Member m2 = new Member("BBB", 20);
		Member m3 = new Member("BBB", 30);
		Member m4 = new Member("BBB", 40);

		memberJpaRepository.save(m1);
		memberJpaRepository.save(m2);
		memberJpaRepository.save(m3);
		memberJpaRepository.save(m4);

		List<Member> result = memberJpaRepository.findByUsername("BBB");

		for (Member m : result) {
			System.out.println(m.getUsername() + " " + m.getAge());
		}
	}
	

	
	@Test
	public void 페이징() {
		memberJpaRepository.save(new Member("member1", 10));
		memberJpaRepository.save(new Member("member2", 10));
		memberJpaRepository.save(new Member("member3", 10));
		memberJpaRepository.save(new Member("member4", 10));
		memberJpaRepository.save(new Member("member5", 10));
		memberJpaRepository.save(new Member("member6", 10));

		// page1 -> offset=0, limit=10 / page2 -> offset=10, limit=10
		int age = 10;
		int offset = 1;
		int limit = 3;

		// when
		List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
		long totalCount = memberJpaRepository.totalCount(age);

		// then
		for (Member member : members) {
			System.out.println("Member: " + member);
		}
		System.out.println("totalCount: " + totalCount);
		
		assertThat(members.size()).isEqualTo(3);
		assertThat(totalCount).isEqualTo(6);
	}
	*/
	
	@Test
	public void bulkUpdate() {
		// given
		memberJpaRepository.save(new Member("member1", 10));
		memberJpaRepository.save(new Member("member2", 19));
		memberJpaRepository.save(new Member("member3", 20));
		memberJpaRepository.save(new Member("member4", 21));
		memberJpaRepository.save(new Member("member5", 23));
		memberJpaRepository.save(new Member("member6", 34));

		// when
		int resultCount = memberJpaRepository.bulkAgePlus(20);
		
		System.out.println("resultCount: " + resultCount);
		assertThat(resultCount).isEqualTo(4);
	}

}
