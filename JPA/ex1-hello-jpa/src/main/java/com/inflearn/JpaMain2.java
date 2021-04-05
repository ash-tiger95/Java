package com.inflearn;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin(); 
		try {
			/*
			Member member = new Member();
			member.setId(101L);
			member.setName("helloJPA");
			// -- 비영속(JPA와 관련 없는 상태)
			
			
			em.persist(member); // 이때 DB에 저장되는 것이 아니다.
			// -- 영속 상태
//			em.detach(member); // 준영속 상태: 영속성 컨텍스트에서 지우는 것
//			em.refresh(member); // 삭제 상태: DB에서 데이터 삭제
			
			// 1차 캐시 -> 쿼리가 나가지 않고 기존에 있는 1차 캐시에 저장된 데이터를 불러온다.
			Member findMember1 = em.find(Member.class, 101L);
			System.out.println("find Member: "+findMember1.getId());
			System.out.println("find Member: "+findMember1.getName());
			
			// 동일성 보장: 1차 캐시가 있기에 가능
			Member findMember2 = em.find(Member.class, 101L);
			System.out.println("동일성 보장: "+ (findMember1 == findMember2));
			*/
			
			/*
			Member member1 = new Member(150L,"A");
			Member member2 = new Member(160L,"B");
			
			em.persist(member1);
			em.persist(member2);
			System.out.println("==============");
			*/
			
			// flush() 예제
			/*
			Member member = new Member(200L,"member200");
			em.persist(member); // 영속성 컨텍스트에 담기고, 쿼리도 저장소에 담겨져있다.
			
			em.flush(); // 트랜잭션에 커밋 전에 DB에 반영하고 싶거나 쿼리를 미리 보고싶을 때, 강제로 호출
			System.out.println("=== BEFORE TRANSACTION ===");
			*/
			
			
			// 준영속 상태 예제
			Member member = em.find(Member.class, 150L); // 1. 150L이 없으니 조회를 해서 영속성 컨텍스트에 올린다.
			member.setName("AAAAA"); // 2. Dirty Checking
			// ㄴ> 영속 상태: JPA에서 관리하는 상태
			
			// 준영속 상태로 만드는 방법 3가지
			em.detach(member); // 방법1: 특정 엔티티만 삭제
			em.clear(); // 방법2: em에 있는 영속성 컨텍스트를 다 지운다.
			em.close(); // 방법3. 영속성 컨텍스트 종료
			// ㄴ> 준영속 상태: JPA에서 관리 안 하는 상태
			// - 트랜잭션 커밋을 하면 아무 일도 일어나지 않는다.
			
			Member member2 = em.find(Member.class, 150L); // 다시 조회해서 연속성 컨텍스트에 올린다.
			
			System.out.println("=== BEFORE TRANSACTION ===");
			
			
			
			tx.commit(); // 영속성 컨텍스트에 있는 애가 DB에 쿼리로 날라간다.
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
