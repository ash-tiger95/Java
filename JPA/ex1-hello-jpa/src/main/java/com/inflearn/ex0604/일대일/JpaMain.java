package com.inflearn.ex0604.일대일;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 일대일
 * 
 * @author 성호
 * 
 * <특징>
 * 일대일 관계는 그 반대도 일대일
 * 주 테이블이나 대상 테이블 중에 외래키 선택 가능
 * 외래키에 데이터베이스 유티크 제약조건 추가
 *
 * 다대일 양방향 매핑처럼 외래키가 있는 곳이 연관관계의 주인
 * 반대편은 mappedBy 적용
 * 
 */

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member3 member = new Member3();
			member.setUsername("member1");
			em.persist(member);
			
			Team3 team = new Team3();
			team.setName("teamA");
			
			// update쿼리가 한 번 더 나간다. (성능상 단점)
			// 왜? Team에 FK가 없고 Member의 FK를 업데이트 시켜줘야 하기 때문이다.
			team.getMembers().add(member);
			em.persist(team);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
