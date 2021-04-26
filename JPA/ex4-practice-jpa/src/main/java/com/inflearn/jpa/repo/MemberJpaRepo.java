package com.inflearn.jpa.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.inflearn.jpa.entity.Member;

@Repository
public class MemberJpaRepo {

	@PersistenceContext
	private EntityManager em;
	
	public Member save(Member member) {
		em.persist(member);
		return member;
	}
	// JPA은 변경 감지라는 기능으로 데이터를 변경한다. 그래서 update라는 메서드가 필요없다.
	// EntityManager에서 조회를 하고, Entity를 수정하고 트랜잭션 커밋을 하면 변경을 인지하고 DB에 update쿼리를 보낸다.
	
	public void delete(Member member) {
		em.remove(member);
	}
	
	public List<Member> findAll(){
		// JPQL: 객체를 대상으로 하는 쿼리
		return em.createQuery("select m from Member m", Member.class)
				.getResultList(); // JPQL에서 Member는 Member 엔티티, Member.class는 반환타입
	}
	
	public Optional<Member> findById(Long id){
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member); // ofNullable(): member가 null일 수 있고 아닐 수 있다라는 의미
	}
	
	public long count() {
		return em.createQuery("select count(m) from Member m", Long.class) // count의 결과는 long
				.getSingleResult(); // 단건 조회
	}
	
	public Member find(Long id) {
		return em.find(Member.class, id);
	}
	
	// 페이징과 정렬
	public List<Member> findByPage(int age, int offset, int limit){
		return em.createQuery("select m from Member m where m.age = :age order by m.username desc")
			.setParameter("age", age)
			.setFirstResult(offset)
			.setMaxResults(limit)
			.getResultList();
	}
	
	public long totalCount(int age) {
		return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
				.setParameter("age", age)
				.getSingleResult();
	}
}
