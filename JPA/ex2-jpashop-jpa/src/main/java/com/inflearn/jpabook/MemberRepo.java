package com.inflearn.jpabook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.inflearn.jpabook.domain.Member;

@Repository
public class MemberRepo {
	
	@PersistenceContext
	private EntityManager em;
	
	public Long save(Member member) {
		em.persist(member);
		
		return member.getId();
		/*
		 * return Member를 하지 않는 이유
		 * 
		 * 코드 스타일, command와 쿼리를 분리한다.
		 * 저장하고 난 후 사이드 이팩트를 일으키는 커멘드이기 때문에 return 값을 거의 안 만든다?
		 * id정도 있으면 다음에 조회할 수 있으니깐 id를 주로 return한다.
		 */
	}
	
	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
