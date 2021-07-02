package com.inflearn.jpa.repo;

import java.util.List;

import javax.persistence.EntityManager;

import com.inflearn.jpa.entity.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepoImpl implements MemberRepoCustom{

	private final EntityManager em;
	
	@Override
	public List<Member> findMemberCuston() {
		return em.createQuery("select m from Member m")
				.getResultList();
	}
	
}
