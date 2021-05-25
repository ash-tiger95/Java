package com.inflearn.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.inflearn.jpa.domain.Member;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
			// 영속
			Member memberA = em.find(Member.class, 150L);
			memberA.setName("AAAAA");
			
			// 준영속으로 변경
			em.detach(memberA);
			
			System.out.println("==============");
			
			tx.commit(); // DB에 쿼리가 날라간다.
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();

	}
}
