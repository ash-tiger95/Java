package com.inflearn.jpabook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!"); // thymleaf의 ${data}에 hello!!를 넣는다.
		return "hello";
	}
}
