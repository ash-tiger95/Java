package com.skeleton.domain;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.skeleton.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String name;
	private String email;
	private String password;

	public User() {
		
	};

	public User(UserDto userDto) {
		super();
		this.id = userDto.getId();
		this.name = userDto.getName();
		this.email = userDto.getEmail();
		this.password = userDto.getPassword();
	}

	public User(Map<String, Object> userObject) {
		super();
		this.name = (String) userObject.get("name");
		this.email = (String) userObject.get("email");
		this.password = (String) userObject.get("password");
	}

	@Override
	public String toString() {
		return "User: [" + id + ", " + name + ", " + email + ", " + password + "]";
	}
	
	
}
