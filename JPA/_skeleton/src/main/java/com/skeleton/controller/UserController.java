package com.skeleton.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeleton.dto.UserDto;
import com.skeleton.entity.User;
import com.skeleton.service.UserService;
import com.skeleton.util.BasicResponse;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;

	@GetMapping(value = "/{userId}", produces = "application/json; charset=utf8")
	public Object getUser(@PathVariable(value = "userId") Long userId) {

		final BasicResponse basicResponse = new BasicResponse();

		try {
			Map<String, Object> map = new HashMap<>();

			User result = userService.findById(userId);

			map.put("user", result);
			basicResponse.body = map;
			basicResponse.status = "success";

		} catch (Exception e) {
			basicResponse.status = "error";
			e.printStackTrace();
		} finally {

			return new ResponseEntity<>(basicResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/all", produces = "application/json; charset=utf8")
	public Object findAll() {
		
		final BasicResponse basicResponse = new BasicResponse();
		
		try {
			Map<String, Object> map = new HashMap<>();
			
//			map.put("user", userService.findAll()); // 출력: "body" : "user" : [...]
			
			basicResponse.body = userService.findAll(); // 출력: "body" : [...]
			basicResponse.status = "success";
		} catch (Exception e) {
			basicResponse.status = "error";
			e.printStackTrace();
		} finally {
			return new ResponseEntity<>(basicResponse, HttpStatus.OK);
		}
	}

	@PostMapping(value = "", produces = "application/json; charset=utf8")
	public Object signUp(@RequestBody UserDto userDto) {

		final BasicResponse basicResponse = new BasicResponse();

		try {
			Map<String, Object> map = new HashMap<>();

			User user = new User(userDto);
			System.out.println("postmapping: " + user.toString());

			userService.signUp(user);

			basicResponse.body = map;
			basicResponse.status = "success";

		} catch (Exception e) {
			basicResponse.status = "error";
			e.printStackTrace();
		} finally {
			return new ResponseEntity<>(basicResponse, HttpStatus.OK);
		}
	}

	@PutMapping(value = "", produces = "application/json; charset=utf8")
	public Object updateUser(@RequestBody UserDto userDto) { // 내 스타일

		final BasicResponse basicResponse = new BasicResponse();

		try {
			userService.signUp(new User(userDto));

			basicResponse.status = "success";
		} catch (Exception e) {
			basicResponse.status = "error";
			e.printStackTrace();
		} finally {
			return new ResponseEntity<>(basicResponse, HttpStatus.OK);
		}
	}

	@PutMapping(value = "/{userId}", produces = "application/json; charset=utf8")
	public Object updateUser2(@PathVariable(name = "userId") Long userId,
			@RequestBody Map<String, Object> jsonRequest) { // 다른 스타일

		final BasicResponse basicResponse = new BasicResponse();

		Map<String, Object> bodyObject = (Map<String, Object>) jsonRequest.get("body");

//		Map<String, Object> userObject = new JSONObject();
//		userObject.put("name", (String)bodyObject.get("name"));
//		userObject.put("email", (String)bodyObject.get("email"));
//		userObject.put("password", (String)bodyObject.get("password"));

		try {
			// id를 Long형으로 형변환을 못한 에러 발생 -> @PathVariable로 받아옴
//			User user = userService.findById((Long)bodyObject.get("id"));
			User user = userService.findById(userId);

			System.out.println("before: " + user.toString());

			user.setName((String) bodyObject.get("name"));
			user.setEmail((String) bodyObject.get("email"));
			user.setPassword((String) bodyObject.get("password"));

			System.out.println("After: " + user.toString());

			userService.signUp(user);

			basicResponse.status = "success";
		} catch (Exception e) {
			basicResponse.status = "error";
			e.printStackTrace();
		} finally {
			return new ResponseEntity<>(basicResponse, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/{userId}", produces = "application/json; charset=utf8")
	public Object removeById(@PathVariable(name = "userId") Long userId) {

		final BasicResponse basicResponse = new BasicResponse();

		try {
			userService.removeById(userId);

			basicResponse.status = "success";
		} catch (Exception e) {
			basicResponse.status = "error";
			e.printStackTrace();
		} finally {
			return new ResponseEntity<>(basicResponse, HttpStatus.OK);
		}
	}
}
