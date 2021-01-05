package com.sungho.tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(HomeController.class) // HomeController의 웹 페이지 테스트
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/")) // GET /를 수행한다.

				.andExpect(status().isOk()) // HTTP 200이 되어야한다.

				.andExpect(view().name("home")) // home 뷰가 있어야한다.

				.andExpect((ResultMatcher) content().string( 
						containsString("Welcome Taco Cloud"))); // 콘텐츠에 Welcome Taco Cloud가 포함되어야한다.
	}
}
