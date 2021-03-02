package com.sungho.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sungho.domain.Member;

public class MemoryMemberRepository implements MemberRepository{
	
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;

	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id)); // null일수도 있으니 Optional로 감싼다. 클라이언트에서 뭔갈 할 수 있데
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream() // 람다식
			.filter(member -> member.getName().equals(name)) // member를 찾아서 같은 name을 찾는다.
			.findAny(); // 그 중 찾으면 반환을 해준다. (findAny는 하나여도 반환한다.) 없으면 Optional에 null이 포함되서 반환
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values()); // store.values는 Member들
	}

}
