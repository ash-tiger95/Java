package com.inflearn.ex0603.일대다양방향;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 일대다 단방향
 * 
 * @author 성호
 * 
 * <특징>
 * 일대다 단방향은 일대다에서 일(1)이 연관관계의 주인
 * 테이블 일대다 관계는 항상 다(N)에 외래키가 있다.
 * 객체와 테이블의 차이 떄문에 반대편 테이블의 외래키를 관리하는 특이한 구조
 * @JoinColumn을 꼭 사용해야 한다. 그렇지 않으면 조인 테이블 방식을 사용한다.
 * (조인 테이블(중간 테이블)이 생성된다.)
 * 
 * <단점>
 * 엔티티가 관리하는 외래키가 다른 테이블에 있음
 * 연관관계 관리를 위해 추가로 UPDATE SQL 실행
 * 
 * <결론>
 * 일대다 단방향 매핑보다는 다대일 양방향 매핑을 사용하자.
 *
 */

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member2 member = new Member2();
			member.setUsername("member1");
			em.persist(member);
			
			Team2 team = new Team2();
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
