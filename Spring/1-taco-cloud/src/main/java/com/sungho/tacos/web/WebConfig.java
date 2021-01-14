package com.sungho.tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 모델 데이터나 사용자 입력을 처리하지 않는 간단한 컨트롤러의 경우는 가른 방법으로 컨트롤러를 정의할 수 있다.
// 뷰 컨트롤러: 뷰에 요청을 전달하는 일만 하는 컨트롤러

@Configuration
public class WebConfig implements WebMvcConfigurer { 
	/*
	 * WebMvcConfigurer 인터페이스는 스프링 MVC를 구성하는 메서드를 정의하고 있다.
	 * 인터페이스임에도 불구하고 정의된 모든 메서드의 기본적인 구현을 제공하여 오버라이딩(재정의)만 하면 된다.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) { // addViewControllers: 하나 이상의 뷰 컨트롤러를 등록하기 위해 사용
	  registry.addViewController("/").setViewName("home"); // 뷰 컨트롤러가 GET요청을 처리하는 경로인 "/"를 인자로 전달하여 addViewController()를 호출한다.
	}

}