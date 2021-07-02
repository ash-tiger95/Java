package com.inflearn.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.jpa.entity.Member;
import com.inflearn.jpa.entity.Team;
import com.inflearn.jpa.repo.MemberRepo;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberTest {

	@PersistenceContext
	EntityManager em;
	
	@Autowired MemberRepo memberRepo;
	
	/*
	@Test
	public void testEntity() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		
		em.persist(teamA);
		em.persist(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);
		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		
		em.persist(member1); // persist: 영속성 컨택스트에 담아 놓는다. DB에 쿼리 안 보냄
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
		
		// 초기화
		em.flush(); // 강제로 DB에 insert 쿼리를 날린다.
		em.clear(); // 영속성 컨텍스트의 캐시를 날린다.
		
		// 확인
		List<Member> members = em.createQuery("select m from Member m",Member.class)
				.getResultList();
		
		for(Member member: members) {
			System.out.println("member = " + member);
			System.out.println("member Team = " + member.getTeam());
		}
	}
	
	

	@Test
	public void JpaEventBaseEntity() throws Exception {
		// given
		Member member = new Member("member1");
		memberRepo.save(member); // @PrePersist 발생
		
		Thread.sleep(100);
		member.setUsername("member2");
		
		em.flush();
		em.clear();
		
		// when
		Member findMember = memberRepo.findById(member.getId()).get();
		
		// then
		System.out.println("findMember.createdDate : " + findMember.getCreateDate());
		System.out.println("findMember.modifiedDate : " + findMember.getLastModifiedDate());
		System.out.println("findMember.createdBy : " + findMember.getCreateBy());
		System.out.println("findMember.modifiedBy : " + findMember.getLastModifiedBy());
	}
	
	*/
}
