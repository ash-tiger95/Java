package com.inflearn.jpabook.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inflearn.jpabook.domain.Delivery;
import com.inflearn.jpabook.domain.Member;
import com.inflearn.jpabook.domain.Order;
import com.inflearn.jpabook.domain.OrderItem;
import com.inflearn.jpabook.domain.item.Item;
import com.inflearn.jpabook.repo.ItemRepo;
import com.inflearn.jpabook.repo.MemberRepo;
import com.inflearn.jpabook.repo.OrderRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepo orderRepo;
	private final MemberRepo memberRepo;
	private final ItemRepo itemRepo;

	/**
	 * 주문
	 */
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {

		// 엔티티 조회
		Member member = memberRepo.findOne(memberId);
		Item item = itemRepo.findOne(itemId);

		// 배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		// 주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);

		// 주문 저장
		orderRepo.save(order);
		return order.getId();
	}

	/**
	 * 주문 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) {
		
		// 주문 엔티티 조회
		Order order=  orderRepo.findOne(orderId);
		
		// 주문 취소
		order.cancel();
		
	}

	// 검색
}
