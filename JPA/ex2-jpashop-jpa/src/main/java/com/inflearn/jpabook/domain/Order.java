package com.inflearn.jpabook.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // OrderItem의 order 필드
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	private LocalDateTime orderDate; // 주문시간: Date타입과 차이점은 어노테이션 매핑을 해야하는데 java8에서는 LocalDateTime지원해준다.
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status; // 주문상태 [ORDER, CANCEL]
	
	// 연관관계 편의 메서드 -> 양방향 관계에서 들고있는 쪽이 좋다.
	public void setMember(Member member) {
		this.member = member;
		/*
		Member member = new Member();
		Order order = new Order();
		
		member.getOrders().add(order);
		order.setMember(member);
		*/
		// 이와 같은 작업에 실수를 안하기 위해 만든다.
		member.getOrders().add(this);
		
		/*
		Member member = new Member();
		Order order = new Order();
		
		order.setMember(member);
		*/
		// 작업이 이렇게 줄어든다.
		
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
}
