package com.skeleton.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.skeleton.dto.UserDto;
import com.skeleton.util.BasicResponse;

public class Controller {

	@PostMapping(value = "", produces = "application/json; charset=utf8")
	public Object methodName(@RequestBody UserDto userDto) {
		
		final BasicResponse basicResponse = new BasicResponse();
		
		try {

			basicResponse.status = "success";
		} catch (Exception e) {
			basicResponse.status = "error";
			e.printStackTrace();
		} finally {
			return new ResponseEntity<>(basicResponse, HttpStatus.OK);
		}
	}

}
