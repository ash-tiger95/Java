package com.sungho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sungho.domain.Member;
import com.sungho.service.MemberService;

@Controller
public class MemberController {
	
	
	/* private final MemberService memberService = new MemberService();
	 * 이렇게 하면 안되는 이유
	 * 하나만 생성해서 공유해서 쓰는 것이 효율적이다.
	 */
	
	private MemberService memberService;
	
	
	// 필드 주입
	// @Autowired private MemberService memberService;
	
	// setter 주입, 단점: public으로 열려있어야되서 노출이 될 수 있다.
	/*
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	*/
	
	// 생성자 주입 - 권장
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members",members); // model에 담아서 view로 넘긴다.
		return "members/memberList";
	}
	
}
