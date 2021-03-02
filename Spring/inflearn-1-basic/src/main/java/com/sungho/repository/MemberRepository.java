package com.sungho.repository;

import java.util.List;
import java.util.Optional;

import com.sungho.domain.Member;

public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id); // Optional은 가져오는 값이 없으면 null이 반환될텐데 null그냥 반환하는 것보다 Optional로 감싸서 반환한다. 추후
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
