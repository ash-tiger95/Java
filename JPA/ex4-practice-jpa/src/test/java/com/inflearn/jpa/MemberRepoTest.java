package com.inflearn.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
	
	@Autowired MemberRepo memberRepo;
	@Autowired TeamRepo teamRepo;
	
	@PersistenceContext EntityManager em;

	/*
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
		Member m3 = new Member("AAA", 30);
		Member m4 = new Member("AAA", 40);

		memberRepo.save(m1);
		memberRepo.save(m2);
		memberRepo.save(m3);
		memberRepo.save(m4);
		
		List<Member> result = memberRepo.findByUsernameAndAgeGreaterThan("AAA", 15);
		
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

		memberRepo.save(m1);
		memberRepo.save(m2);
		memberRepo.save(m3);
		memberRepo.save(m4);

		List<Member> result = memberRepo.findByUsername("BBB");

		for (Member m : result) {
			System.out.println(m.getUsername() + " " + m.getAge());
		}
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
			System.out.println("findUsernameList = " + s);
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
	public void 컬렉션파라미터바인딩() {
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
	public void 반환타입() {
		Member m1 = new Member("AAA",10);
		Member m2 = new Member("BBB",20);
		
		memberRepo.save(m1);
		memberRepo.save(m2);
		
		
		// 1. 여러 개 조회
		List<Member> result = memberRepo.findListByUsername("AAA");
		for(Member member : result) {
			System.out.println("List Member = " + member);
		}
		// 주의!
		// 컬렉션 조회에서 데이터가 없는 경우 null을 반환하는 것이 아니라 빈 컬렉션을 반환해준다. isEmpty()로 판별
		
		
		// 2. 단건 조회
		Member result2 = memberRepo.findMemberByUsername("AAA");
		System.out.println("Member: " + result2);
		// 주의!
		// 없는 경우 null 반환
		
		
		// 3. Optional(null일 수 있고 아닐 수 있다.) 조회
		Optional<Member> result3 = memberRepo.findOptionalByUsername("AAA");
		System.out.println("Optional Member: " + result3);
		// 위의 2개를 해결 = Optional, 없으면 Optional.empty 반환
		// Optional은 단건조회인데 데이터가 2개 이상이면 exception
		
		
		// 단건 조회인데 데이터가 여러 개면 예외처리한다.
	}
	
	
	@Test
	public void 페이징() {
		memberRepo.save(new Member("member1", 10));
		memberRepo.save(new Member("member2", 10));
		memberRepo.save(new Member("member3", 10));
		memberRepo.save(new Member("member4", 10));
		memberRepo.save(new Member("member5", 10));
		memberRepo.save(new Member("member6", 10));

		int age = 10;
		// 페이지 시작은 0부터
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
		// 0 페이지에서 3개 가져와서 username으로 정렬
		
		// when
		Page<Member> page = memberRepo.findByAge(age, pageRequest);

		// then
		List<Member> content = page.getContent();
		long totalElements = page.getTotalElements(); // total Count
		
		for(Member member : content) {
			System.out.println("Member: " + member);
		}
		System.out.println("totalElements: "+totalElements);

		assertThat(content.size()).isEqualTo(3);
		assertThat(page.getTotalElements()).isEqualTo(6);
		assertThat(page.getNumber()).isEqualTo(0); // 페이지 번호
		assertThat(page.getTotalPages()).isEqualTo(2); // 전체 페이지 개수
		assertThat(page.isFirst()).isTrue(); // 첫 번째 페이지인지
		assertThat(page.hasNext()).isTrue(); // 다음 페이지가 있는지
	}
	
	@Test
	public void 슬라이스() {
		memberRepo.save(new Member("member1", 10));
		memberRepo.save(new Member("member2", 10));
		memberRepo.save(new Member("member3", 10));
		memberRepo.save(new Member("member4", 10));
		memberRepo.save(new Member("member5", 10));
		memberRepo.save(new Member("member6", 10));

		int age = 10;
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username")); // 페이지 0부터 시작
		// 0 페이지에서 3개 가져와서 username으로 정렬
		
		// when
		Slice<Member> page = memberRepo.findByAge(age, pageRequest);
		
		// then
		List<Member> content = page.getContent();
		
		for(Member member : content) {
			System.out.println("Member: " + member);
		}
		
		assertThat(content.size()).isEqualTo(3);
//		assertThat(page.getTotalElements()).isEqualTo(6);
		assertThat(page.getNumber()).isEqualTo(0); // 페이지 번호
//		assertThat(page.getTotalPages()).isEqualTo(2); // 전체 페이지 개수
		assertThat(page.isFirst()).isTrue(); // 첫 번째 페이지인지
		assertThat(page.hasNext()).isTrue(); // 다음 페이지가 있는지
	}
	
	
	@Test
	public void 카운트최적화() {
		memberRepo.save(new Member("member1", 10));
		memberRepo.save(new Member("member2", 10));
		memberRepo.save(new Member("member3", 10));
		memberRepo.save(new Member("member4", 10));
		memberRepo.save(new Member("member5", 10));
		memberRepo.save(new Member("member6", 10));

		int age = 10;
		// 페이지 시작은 0부터
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
		// 0 페이지에서 3개 가져와서 username으로 정렬
		
		// when
		Page<Member> page = memberRepo.findByAge2(age, pageRequest);
		
		
		// ★. 중요! Page<Member>를 Controller에서 그대로 반환하면 절대 안된다.
		// 엔티티를 외부에 노출시키면 안되
		// DTO로 변환해서 리턴해줘야 한다.
		Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

		// then
		List<Member> content = page.getContent();
		long totalElements = page.getTotalElements(); // total Count
		
		for(Member member : content) {
			System.out.println("Member: " + member);
		}
		System.out.println("totalElements: "+totalElements);

		assertThat(content.size()).isEqualTo(3);
		assertThat(page.getTotalElements()).isEqualTo(6);
		assertThat(page.getNumber()).isEqualTo(0); // 페이지 번호
		assertThat(page.getTotalPages()).isEqualTo(2); // 전체 페이지 개수
		assertThat(page.isFirst()).isTrue(); // 첫 번째 페이지인지
		assertThat(page.hasNext()).isTrue(); // 다음 페이지가 있는지
	}
	
	
	@Test
	public void bulkUpdate() {
		// given
		memberRepo.save(new Member("member1", 10));
		memberRepo.save(new Member("member2", 19));
		memberRepo.save(new Member("member3", 20));
		memberRepo.save(new Member("member4", 21));
		memberRepo.save(new Member("member5", 23));
		memberRepo.save(new Member("member6", 34));

		// when
		int resultCount = memberRepo.bulkAgePlus(20);
		
//		em.flush();
//		em.clear();
		
		List<Member> result = memberRepo.findByUsername("member5");
		Member member5 = result.get(0);
		System.out.println("member5: " + member5);
		
//		System.out.println("resultCount: " + resultCount);
//		assertThat(resultCount).isEqualTo(4);
	}
	
	
	@Test
	public void findMemberLazy() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		
		teamRepo.save(teamA);
		teamRepo.save(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member1", 10, teamB);
		
		memberRepo.save(member1);
		memberRepo.save(member2);
		
		em.flush(); // 영속성 컨텍스트에 있는 정보를 DB에 반영하고
		em.clear(); // 영속성 컨텍스트를 깔끔하게 날린다
		
		List<Member> members = memberRepo.findNamedQueryByUsername("member1");
		
		for(Member m : members) {
			System.out.println("member: " + m.getUsername());
			System.out.println("member.teamClass: "+m.getTeam().getClass());
			System.out.println("member.team: " + m.getTeam().getName());
		}
	}
	
	
	@Test
	public void queryHint() {
		Member m1 = new Member("member1", 10);
		memberRepo.save(m1);
		
		em.flush();
		em.clear();
		
		Member findMember = memberRepo.findReadOnlyByUsername("member1");
		findMember.setUsername("member2");
		
		em.flush(); // 변경 감지가 되어 Update 쿼리가 나간다.
	}
	
	@Test
	public void lock() {
		Member m1 = new Member("member1", 10);
		memberRepo.save(m1);
		
		em.flush();
		em.clear();
		
		List<Member> result = memberRepo.findLockByUsername("member1");
	}
	*/
	
	@Test
	public void callCustom() {
		List<Member> result = memberRepo.findMemberCuston();
	}

}
