package com.inflearn.jpabook.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.inflearn.jpabook.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepo {
	
	private final EntityManager em;
	
	public void save(Member member) { // 저장
		em.persist(member);
	}
	
	public Member findOne(Long id) { // 하나 조회
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll(){ // 전체 조회
		return em.createQuery("select m from Member m", Member.class) // Member.class: 조회 타입
				.getResultList();
	}
	
	public List<Member> findByName(String name){
		return em.createQuery("select m from Member m where m.name = :name", Member.class) // :name는 파라미터를 바인딩해야한다.
				.setParameter("name", name) // 바인딩(구체적인 값을 할당하는 과정)
				.getResultList();
	}
}
