package com.inflearn.ex0604.일대일;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Team3 {
	
	@Id
	@GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	
	private String name;
	
	@OneToMany
	@JoinColumn(name = "TEAM_ID")
	private List<Member3> members = new ArrayList<>();

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

	public List<Member3> getMembers() {
		return members;
	}

	public void setMembers(List<Member3> members) {
		this.members = members;
	}
	
	
	
}
