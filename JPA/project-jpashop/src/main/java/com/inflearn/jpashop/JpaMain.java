package com.inflearn.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.inflearn.jpashop.domain.Order;
import com.inflearn.jpashop.domain.OrderItem;


public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			// 핵심: 단방향으로 설계하는 것이 가장 중요하다. (양방향은 필요에 따라)
			Order order = new Order();
			em.persist(order);
//			order.addOrderItem(new OrderItem());
			
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			em.persist(orderItem);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
