package com.inflearn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	
	@Id
	@GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="team") // 일대다, 반대편에 내가 뭐와 연결되어 있는지 적어둬야한다.
	private List<Member> members = new ArrayList<>(); // 최기화. add 할때 nullpoint가 안뜨려고, 이걸 관례로 많이 쓴다.

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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	
}
