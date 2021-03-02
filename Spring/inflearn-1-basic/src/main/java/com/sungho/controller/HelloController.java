package com.sungho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@GetMapping("hello") // http://localhost:8080/hello
	public String hello(Model model) {
		model.addAttribute("data","hello!"); // (attributeName, attributeValue)
		return "hello";
	}
	
	
	// 정적 컨텐츠 방식
	// http://localhost:8080/hello-static.html
	
	
	// MVC와 템플릿 엔진 방식
	@GetMapping("hello-mvc") // http://localhost:8080/hello-mvc?name=spring
	public String helloMvc(@RequestParam(name="name") String name, Model model) { 
		model.addAttribute("name",name);
		return "hello-template";
	}
	
	
	// API 방식
	@GetMapping("hello-string") // http://localhost:8080/hello-string?name=test
	@ResponseBody // http의 body부분에 데이터를 직접 넣겠다는 의미
	public String helloString(@RequestParam("name") String name) {
		return "hello "+name; // 이 부분이 데이터로 넘어간다.
	}
	
	
	// API 방식 json으로 넘겨준다. (xml 방식은 너무 무겁고 불편)
	@GetMapping("hello-api") // http://localhost:8080/hello-api?name=test
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello; // 객체 넘기기
	}
	
	static class Hello{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}