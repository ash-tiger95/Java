package com.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class 문법 {

	public static void main(String[] args) {
		char a = 'A';
		System.out.println((int) 'A');
		
		String str = "HELLO WORLD";
		System.out.println(str.length()); // 문자 길이
		
		System.out.println(str.startsWith("HEL"));
		System.out.println(str.endsWith("LD"));
		
		System.out.println(str.substring(0,5));
		System.out.println(str.contains("L"));
		System.out.println(str.concat("aa"));
		System.out.println(str.indexOf("H"));
		System.out.println("split: "+str.split(" ")[0]);
		
		String[] arr1 = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(arr1.length); // 문자열 배열 길이
		
		int[] arr2 = {1,2,3,4,5,6,7,8,9};
		System.out.println(arr2.length); // int형 배열 길이
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		System.out.println(list.size());
		
		System.out.println("----------------");
		int[] test = {7,2,8,3,5,8,2,7};
		Arrays.sort(test);
		System.out.println(Arrays.toString(test));
		
		Integer[] test2 = {7,2,8,3,5,8,2,7};
		Arrays.sort(test2, Collections.reverseOrder());
		System.out.println(Arrays.toString(test2));
		
		int[] input = new int[test2.length];
		int idx = 0;
		for(Integer i : test2) {
			input[idx++] = i;
		}
		System.out.println(Arrays.toString(input));
		
		
		System.out.println("---------------");
		TreeMap<Integer, Integer> tm = new TreeMap<>(); // 자동 정렬
		tm.put(3, 1);
		tm.put(1, 1);
		tm.put(2, 1);
		System.out.println(tm);
		tm.put(15, tm.getOrDefault(1,0)+1);
		System.out.println(tm);
		tm.put(4, tm.getOrDefault(4,0)+1);
		System.out.println(tm);
		System.out.println(tm.firstKey());
		System.out.println(tm.lastKey());
		System.out.println(tm.size());
		
		TreeMap<String, String> tm1 = new TreeMap<>(); // 자동 정렬
		tm1.put("AA", "C");
		tm1.put("C", "BB");
		tm1.put("1", "AAA");
		System.out.println(tm1);
		
		
		System.out.println("---------------");
		TreeSet<Integer> ts = new TreeSet<>();
		ts.add(6);
		ts.add(2);
		ts.add(7);
		System.out.println(ts);
		
		TreeSet<String> ts1 = new TreeSet<>();
		ts1.add("CCC");
		ts1.add("BBC");
		ts1.add("AAA");
		System.out.println(ts1);
	}

}
