package com.inflearn;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Team team = new Team();
			team.setName("TEAM A");
			em.persist(team);
			
			Team team2 = new Team();
			team.setName("TEAM B");
			em.persist(team2);
			
			Member member = new Member();
			member.setUsername("hello");
			member.setTeam(team);
			em.persist(member);
			
			Member member2 = new Member();
			member.setUsername("hello");
			member.setTeam(team2);
			em.persist(member2);
			
			em.flush();
			em.clear();
			
//			Member m = em.find(Member.class, member.getId());
			
			List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		emf.close();
	}
}
