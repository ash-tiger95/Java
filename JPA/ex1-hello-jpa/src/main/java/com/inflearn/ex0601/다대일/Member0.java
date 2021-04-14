package com.inflearn.ex0601.다대일;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Member0 {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;
	
//	@Column(name = "TEAM_ID")
//	private Long teamId;
	
	@ManyToOne // 다대일
	@JoinColumn(name = "TEAM_ID") // mapping
	private Team0 team; // 연관관계의 주인

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

	public Team0 getTeam() {
		return team;
	}

	public void changeTeam(Team0 team) {
		this.team = team;
		
		team.getMembers().add(this);
	}
	
}
