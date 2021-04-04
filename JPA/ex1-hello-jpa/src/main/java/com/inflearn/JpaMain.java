package com.inflearn;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		// EntityManagerFactory는 WAS를 할 때, Web Server가 올라가는 시점에 DB당 하나만 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // /META-INF/persistence.xml에서 name="hello" 설정을 확인한다.

		// EntityManager: 고객의 요청이 올때마다 켰다가 끄고 켰다가 끄고 반복 -> 쓰레드 공유 x
		EntityManager em = emf.createEntityManager(); // 자바 컬렉션같이 객체를 대신 저장해주는 역할
		// JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
		EntityTransaction tx = em.getTransaction(); // JPA에서는 트랜잭션이 매우 중요!

		tx.begin(); // 트랜잭션 시작

		// 정석 코드
		try {
			// 회원 추가
//			Member member = new Member();
//			member.setId(2L);
//			member.setName("HelloB"); // error
//			// JPA에서 데이터변경 작업은 트랜잭션 안에서 이뤄져야한다.
//			em.persist(member); // JPA 저장
			
			// 회원 수정
//			Member findMember = em.find(Member.class, 1L); // PK(1L)로 Member 찾고
//			findMember.setName("HelloJPA"); // em.persist(member)같이 저장 작업이 필요없다. 여기서 끝
			// 왜 그럴까? JPA를 통해서 Entity를 가져오면 JPA가 관리를 한다.
			// 트랜잭션 커밋하는 시점에 JPA가 검사를 해서 바뀐값이 있으면
			// update 쿼리를 날리고 트랜잭션 실행

			// 회원 삭제
//			em.remove(findMember);
			
			// 전체 조회 JPQL -> 쿼리 작성 가능(약간 달라, 테이블 대상이 아니라 객체 대상으로 쿼리를 짠다.) (객체지향 쿼리)
			List<Member> result = em.createQuery("select m from Member as m", Member.class) // 여기서 Member 객체를 다 가져와(m)
				.getResultList();
			
			for(Member member:result) {
				System.out.println(member.getName());
			}
			// 무슨 장점?  DB 방언에 맞게 알아서 처리를 해준다. (페이지네이션이 쉽게 가능한거와 같이 등등)
			List<Member> result2 = em.createQuery("select m from Member as m", Member.class) // 여기서 Member 객체를 다 가져와(m)
					.setFirstResult(5) // 5번부터
					.setMaxResults(10) // 10개 가져와
					.getResultList();
			for(Member member:result2) {
				System.out.println(member.getName());
			}
			// 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색조건이 포함된 SQL이 필요하다.
			// JPQL은 엔티티 객체를 대상으로 쿼리
			// SQL은 데이터베이스 테이블을 대상으로 쿼리
			
			tx.commit(); // ★
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close(); // WAS가 내려갈 때 팩토리 닫아줘야한다.
	}
}
