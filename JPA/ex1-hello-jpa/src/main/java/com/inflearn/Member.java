package com.inflearn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "MEMBER") // DB테이블이랑 Mapping
public class Member {

	@Id // PK라는 걸 알려준다.
	private Long id;

	public Member() {
		
	}
	
	public Member(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

//	@Column(name="username") // DB컬럼이랑 Mapping
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
