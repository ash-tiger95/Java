package com.theory.linkedlist;

import java.util.LinkedList;

public class llTest {

	public static void main(String[] args) {
		LinkedList<String> ll = new LinkedList<>();
		
		ll.add("RED");
		ll.add("YELLOW");
		ll.add("GREEN");
		
		System.out.println(ll);
		
		ll.add(1, "ORANGE"); // add([index], value): 인덱스를 지정할 수 있다. (지정하지 않으면 가장 마지막에 연결)
		
		System.out.println(ll);
		
		ll.set(0, "WHITE"); // set(index, value): 원하는 인덱스의 값을 변경
		
		System.out.println(ll);
		
		String remove = ll.remove(0); // remove(index): 원하는 인덱스의 값을 삭제
		System.out.println(remove);
		System.out.println(ll);
		
		ll.remove("ORANGE"); // 값으로 삭제 가능, return boolean
		System.out.println(ll);
		
		ll.clear(); // 초기화
		System.out.println(ll);
		
		ll.add("RED");
		ll.add("ORANGE");
		ll.add("YELLOW");
		ll.add("GREEN");
		for(String s : ll) {
			System.out.println(s);
		}
		
		boolean contain = ll.contains("BLUE"); // contains(): 존재유무
		System.out.println(contain);
		
		int index = ll.indexOf("GREEN"); // indexOf(): 존재유뮤 + 위치
		System.out.println(index);
		
		int index2 = ll.indexOf("BLACK");
		System.out.println(index2); // 존재하지 않으면 return -1;
	}

}
