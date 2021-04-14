package com.inflearn.ex0603.일대다양방향;

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

/**
 * 
 * @author 성호
 * 
 * @ManyToOne
 * @JoinColumn(name = "TEAM_ID")
 * -> 이렇게 하면 연관관계 주인이 되어 연관관계 주인이 2개가 되어 꼬인다.
 * 
 * 
 * @ManyToOne
 * @JoinColumn(name = "TEAM_ID", insertable = false, updatable=false)
 * 옵션을 넣으면 읽기 전용이 된다.
 * -> 최종적으로 insert, update를 하지 않는다.
 * 
 * <결론>
 * 양방향 매핑을 만들 수 있지만, 사용하지는 않는다.
 * 다대일 양방향을 쓰자.
 *
 */
@Entity
public class Member2 {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable=false)
	private Team2 team;

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
