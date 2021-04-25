package com.inflearn.jpabook;

import static junit.framework.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.jpabook.domain.Address;
import com.inflearn.jpabook.domain.Member;
import com.inflearn.jpabook.domain.Order;
import com.inflearn.jpabook.domain.OrderStatus;
import com.inflearn.jpabook.domain.item.Book;
import com.inflearn.jpabook.domain.item.Item;
import com.inflearn.jpabook.exception.NotEnoughStockException;
import com.inflearn.jpabook.repo.OrderRepo;
import com.inflearn.jpabook.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
	
	@Autowired
	EntityManager em;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepo orderRepo;

	@Test
	public void 상품주문() throws Exception{
		// given
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);
		
		Item book = new Book();
		book.setName("시골 JPA");
		book.setPrice(10000);
		book.setStockQuantity(10);
		em.persist(book);

		int orderCount = 2;
		
		// when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		// then
		Order getOrder = orderRepo.findOne(orderId);
		
		assertEquals("상품 주문시 상태는 Order", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("주문한 상품 종류 수가 정확해야한다.", 1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량이다", 10000*orderCount, getOrder.getTotalPrice());
		assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
		
	}
	
//	@Test(excepted = NotEnoughStockException.class)
//	public void 상품주문_재고수량초과() throws Exception{
//		// given
//		Member member = new Member();
//		member.setName("회원1");
//		member.setAddress(new Address("서울", "강가", "123-123"));
//		em.persist(member);
//		
//		Item book = new Book();
//		book.setName("시골 JPA");
//		book.setPrice(10000);
//		book.setStockQuantity(10);
//		em.persist(book);
//		
//		// when
//		
//		// then
//	}
	
	@Test
	public void 주문취소() throws Exception{
		// given
		
		// when
		
		// then
	}
	
}
