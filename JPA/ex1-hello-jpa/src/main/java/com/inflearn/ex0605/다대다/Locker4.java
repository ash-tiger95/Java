package com.inflearn.ex0605.다대다;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker4 {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	// 일대일 양방향일 때 추가
	@OneToOne(mappedBy = "locker") // Member에 있는 locker 변수
	private Member4 member;
}
