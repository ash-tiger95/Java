package com.inflearn.jpa.repo;

import java.util.List;

import com.inflearn.jpa.entity.Member;

public interface MemberRepoCustom {
	
	List<Member> findMemberCuston();
}
