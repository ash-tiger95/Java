package com.inflearn.jpabook.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable // 내장될 수 있다?
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;
	
	protected Address() {
		super();
	}
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

}
