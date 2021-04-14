package com.inflearn.ex0605.다대다;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Member4 {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable=false)
	private Team4 team;
	
	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker4 locker;
	
	// 다대다 - 단방향
//	@ManyToMany
//	@JoinTable(name = "MEMBER_PRODUCT")
//	private List<Product4> products = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<MemberProduct> memberProducts = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
