package com.theory.datastructure;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Set02_TreeSetTest {
	public static void main(String[] args) {
		Set<Integer> set = new TreeSet<Integer>();

		set.add(4); // 데이터 추가
		set.add(2);
		set.add(1);
		set.add(3);
		set.add(1);
		set.add(3);

		Iterator<Integer> it = set.iterator(); // 반복자 생성

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("----------");

		Set<String> ts = new TreeSet<String>();
		ts.add("a");
		ts.add("c");
		ts.add("d");
		ts.add("b");

		Iterator<String> itr = ts.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
