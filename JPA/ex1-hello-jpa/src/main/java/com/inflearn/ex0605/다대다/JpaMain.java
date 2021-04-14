package com.inflearn.ex0605.다대다;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 다대다
 * 
 * @author 성호
 * 
 * <특징>
 * 실무에서 안 쓴다.
 * 
 * 중간 테이블(MemberProduct)을 새로 만들어서 다대일, 일대다 모양으로 사용한다.
 * 
 * 
 */

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member4 member = new Member4();
			member.setUsername("member1");
			em.persist(member);
			
			Team4 team = new Team4();
			team.setName("teamA");

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
