package com.woori.wohfy.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parent")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parent {

	@Id @GeneratedValue
	@Column(name = "parent_id")
	private Long id;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int account;
	
}
