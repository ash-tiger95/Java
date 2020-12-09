package com.theory.datastructure;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Set03_LinkedHashSetTest {
	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<String>();
		set.add("1");
		set.add("1");
		set.add("two");
		set.add("3");
		set.add("4");
		set.add("five");

		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("--------------------------");

		LinkedHashSet<Integer> lh = new LinkedHashSet<Integer>();
		lh.add(1);
		lh.add(1);
		lh.add(4);
		lh.add(2);
		lh.add(3);
		Iterator<Integer> it2 = lh.iterator();

		while (it2.hasNext()) {
			System.out.println(it2.next());
		}

	}

}
