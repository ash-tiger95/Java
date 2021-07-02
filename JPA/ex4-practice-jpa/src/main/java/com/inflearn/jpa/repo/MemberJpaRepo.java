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
	
	public void delete(Member member) {
		em.remove(member);
	}
	
	public List<Member> findAll(){
		// JPQL(객체를 대상으로 하는 쿼리)에서 Member는 Member 엔티티, Member.class는 반환타입
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
	
	public Member find(Long id) {
		return em.find(Member.class, id);
	}
	
	public Optional<Member> findById(Long id){
		Member member = em.find(Member.class, id);
		
		// ofNullable(): member가 null일 수 있고 아닐 수 있다라는 의미
		return Optional.ofNullable(member);
	}
	
	public long count() {
		// count의 결과는 Long 타입, 단건 조회
		return em.createQuery("select count(m) from Member m", Long.class).getSingleResult();
	}
	
	public List<Member> findByUsernameAndAgeGreaterThan(String username,int age){
		return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
				.setParameter("username", username)
				.setParameter("age", age)
				.getResultList();
	}
	
	public List<Member> findByUsername(String username){
		return em.createNamedQuery("Member.findByUsername", Member.class)
				.setParameter("username", username)
				.getResultList();
	}
	
	// [JPA 실전] 6. 페이징과 정렬
	public List<Member> findByPage(int age, int offset, int limit){
		return em.createQuery("select m from Member m where m.age = :age order by m.username desc")
			.setParameter("age", age)
			.setFirstResult(offset) // offset: 몇 번째 부터
			.setMaxResults(limit) // limit: 몇 개를 가지고 올 것인지
			.getResultList();
	}
	
	// [JPA 실전] 6. 페이징과 정렬
	public long totalCount(int age) {
		return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
				.setParameter("age", age)
				.getSingleResult();
	}
	
	// [JPA 실전] 7. 벌크성 수정 쿼리
	public int bulkAgePlus(int age) {
		return em.createQuery("update Member m set m.age = m.age + 1 where m.age >= :age")
			.setParameter("age", age)
			.executeUpdate();
	}
}
