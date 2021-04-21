package com.inflearn.jpabook.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	private String username;
	
	@Embedded // 내장 타입?
	private Address address;
	
	@OneToMany(mappedBy = "member") // Order 클래스의 필드명
	private List<Order> orders = new ArrayList<>();
	
}
