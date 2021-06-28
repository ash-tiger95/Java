package com.skeleton.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String password;
	@Override
	public String toString() {
		return "UserDto [" + id + ", " + name + ", " + email + ", " + password + "]";
	}
	
	
}
