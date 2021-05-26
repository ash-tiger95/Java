package com.inflearn.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
			Member member = new Member();
			member.setId(1L);
			member.setTestLocalDate(LocalDate.now());
			member.setTestLocalDateTime(LocalDateTime.now());
			
			em.persist(member);
			
			tx.commit(); // DB에 쿼리가 날라간다.
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();

	}
}
