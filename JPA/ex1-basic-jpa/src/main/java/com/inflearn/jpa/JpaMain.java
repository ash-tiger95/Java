package com.inflearn.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.inflearn.jpa.domain.Member;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // Application loading 시점에 딱 하나만 만들어놔야 한다.
		EntityManager em = emf.createEntityManager(); // Transaction 단위(고객 요청 단위)
		EntityTransaction tx = em.getTransaction(); // JPA에서 데이터가 변경되는 모든 작업은 트랜잭션 안에서 실행되어야 한다.

		tx.begin();

		try {
			// 비영속
			Member member = new Member();
			member.setId(100L);
			member.setName("HelloJPA");
			
			// 영속: 엔티티 매니저 안에 있는 영속성 컨텍스트를 통해 member가 관리된다.
			System.out.println("=== BEFORE ===");
			em.persist(member); // 이때 DB에 저장되는 것이 아니다.
			System.out.println("=== AFTER ===");

			tx.commit(); // DB에 쿼리가 날라간다.
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();

	}
}
