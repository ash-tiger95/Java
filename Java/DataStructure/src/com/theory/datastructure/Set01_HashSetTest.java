package com.theory.datastructure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Set01_HashSetTest {
	static class Member { // Member 클래스
		String name; // 필드
		String id;

		public Member(String name, String id) { // 생성자
			this.name = name; // 필드값 초기화
			this.id = id;
		}
	}

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("one"); // 데이터 저장(추가)
		set.add("two");
		set.add("three");
		set.add("one");
		set.add("two");
		set.add("4");
		set.add("5");
		set.add("six");

		System.out.println("저장된 데이터 수 : " + set.size()); // 데이터 수 출력

		Iterator<String> it = set.iterator(); // Iterator(반복자) 생성

		while (it.hasNext()) { // hasNext() : 데이터가 있으면 true 없으면 false
			System.out.println(it.next()); // next() : 다음 데이터 리턴
		}

		System.out.println("--------------------");

		set.remove("three"); // 데이터 제거
		System.out.println("저장된 데이터 수 : " + set.size()); // 저장된 데이터 수 출력
		it = set.iterator(); // 반복자 재생성(위의 반복문에서 next 메서드로 데이터를 다 가져왔기 때문에 재생성을 해야함)

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("--------------------");

		Set<Member> setMember = new HashSet<Member>(); // 제네릭 타입이 Member인 HashSet 생성

		setMember.add(new Member("토니 스타크", "ironman")); // 객체 추가
		setMember.add(new Member("피터 파커", "spierman")); // 객체 추가

		Iterator<Member> itMember = setMember.iterator(); // 반복자 생성

		while (itMember.hasNext()) {
			Member mb = itMember.next(); // set에 저장된 다음 객체의 참조값 저장

			System.out.println("아이디 : " + mb.id); // id 출력
			System.out.println("이름 : " + mb.name); // name 출력
			System.out.println("-----------------");
		}
	}
}
