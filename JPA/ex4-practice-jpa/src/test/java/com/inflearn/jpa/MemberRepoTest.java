package com.inflearn.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.jpa.dto.MemberDto;
import com.inflearn.jpa.entity.Member;
import com.inflearn.jpa.entity.Team;
import com.inflearn.jpa.repo.MemberRepo;
import com.inflearn.jpa.repo.TeamRepo;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepoTest {

	@Autowired
	MemberRepo memberRepo;
	@Autowired
	TeamRepo teamRepo;

	@Test
	public void testMember() {
		Member member = new Member("member1");
		Member savedMember = memberRepo.save(member);

		Member findMember = memberRepo.findById(savedMember.getId()).get();

		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		memberRepo.save(member1);
		memberRepo.save(member2);

		// 단건 조회 검증
		Member findMember1 = memberRepo.findById(member1.getId()).get();
		Member findMember2 = memberRepo.findById(member2.getId()).get();
		assertThat(findMember1).isEqualTo(findMember1);
		assertThat(findMember2).isEqualTo(findMember2);

		// 리스트 조회 검증 
		List<Member> all = memberRepo.findAll();
		assertThat(all.size()).isEqualTo(2);

		// 카운트 검증
		long count = memberRepo.count();
		assertThat(count).isEqualTo(2);

		// 삭제 검증
		memberRepo.delete(member1);
		memberRepo.delete(member2);
		long deletedCount = memberRepo.count();
		assertThat(deletedCount).isEqualTo(0);
	}

	@Test
	public void findByUsernameAndAgeGreaterThan() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("AAA", 20);
		memberRepo.save(m1);
		memberRepo.save(m2);
		List<Member> result = memberRepo.findByUsernameAndAgeGreaterThan("AAA", 15);
		assertThat(result.get(0).getUsername()).isEqualTo("AAA");
		assertThat(result.get(0).getAge()).isEqualTo(20);
		assertThat(result.size()).isEqualTo(1);
	}
	
	@Test
	public void testQuery() {
		Member m1 = new Member("AAA",10);
		Member m2 = new Member("BBB",20);
		
		memberRepo.save(m1);
		memberRepo.save(m2);
		
		List<Member> result = memberRepo.findUser("AAA", 10);
		assertThat(result.get(0)).isEqualTo(m1);
		
	}
	
	@Test
	public void findUsernameList() {
		Member m1 = new Member("AAA",10);
		Member m2 = new Member("BBB",20);
		
		memberRepo.save(m1);
		memberRepo.save(m2);
		
		List<String> result = memberRepo.findUsernameList();
		for(String s : result) {
			System.out.println("S = " + s);
		}
		
	}
	
	@Test
	public void findMemberDto() {
		Team team = new Team("teamA");
		teamRepo.save(team);
		
		Member m1 = new Member("AAA",10);
		m1.setTeam(team);
		memberRepo.save(m1);
		
		
		List<MemberDto> result = memberRepo.findMemberDto();
		for(MemberDto dto : result) {
			System.out.println("Dto = " + dto);
		}
		
	}
	
	@Test
	public void findByNames() {
		Member m1 = new Member("AAA",10);
		Member m2 = new Member("BBB",20);
		
		memberRepo.save(m1);
		memberRepo.save(m2);
		
		List<Member> result = memberRepo.findByNames(Arrays.asList("AAA", "BBB"));
		for(Member member : result) {
			System.out.println("Member = " + member);
		}
		
	}
	
	@Test
	public void returnType() {
		Member m1 = new Member("AAA",10);
		Member m2 = new Member("BBB",20);
		memberRepo.save(m1);
		memberRepo.save(m2);
		
		
		List<Member> result = memberRepo.findListByUsername("AAA");
		for(Member member : result) {
			System.out.println("findListByUsername = " + member);
		}
		// 주의! 컬렉션 조회에서 데이터가 없는 경우 null을 반환하는 것이 아니라 빈 컬렉션을 반환해준다. isEmpty()로 판별
		
		/*
		Member result2 = memberRepo.findMemberByUsername("AAA");
		System.out.println("findMemberByUsername: " + result2);
		// 주의! 없는 경우 null 반환
		
		Optional<Member> result3 = memberRepo.findOptionalByUsername("AAA"); // optional: null일 수 있고 아닐 수 있다.
		System.out.println("findOptionalByUsername: " + result3);
		// 위의 2개를 해결 = Optional, 없으면 Optional.empty 반환
		// Optional은 단건조회인데 데이터가 2개 이상이면 exception
		*/
		
		// 단건 조회인데 데이터가 여러 개면 예외처리한다.
	}
}
