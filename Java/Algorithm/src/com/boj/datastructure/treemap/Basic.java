package com.boj.datastructure.treemap;

import java.util.TreeMap;

public class Basic {

	public static void main(String[] args) {
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		tm.put(0, 0);
		System.out.println(tm);

		tm.put(2,2);
		System.out.println(tm);

		tm.put(1, 1);
		System.out.println(tm);

		System.out.println(tm.firstKey());
		System.out.println(tm.lastKey());
	}

}
