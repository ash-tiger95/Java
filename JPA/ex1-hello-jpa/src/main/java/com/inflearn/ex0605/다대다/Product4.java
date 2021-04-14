package com.inflearn.ex0605.다대다;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Product4 {
	
	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	// 다대다 양방향
//	@ManyToMany(mappedBy = "products")
//	private List<Member4> members = new ArrayList<>();
	
	@OneToMany(mappedBy = "product")
	private List<MemberProduct> memberProducts = new ArrayList<>();

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
