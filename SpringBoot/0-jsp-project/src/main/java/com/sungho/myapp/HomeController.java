package com.sungho.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping(value="/home/test")
	@ResponseBody
	public String showTest() {
		return "안녕";
	}
	
	@RequestMapping(value="/")
	public String showMain() {
		return "home/hello";
	}
}
