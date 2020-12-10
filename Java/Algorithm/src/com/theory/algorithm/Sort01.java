package com.theory.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort01 {
	public static void main(String[] args) {
		int[] a = {6,1,8,2,4}; // 기본형 타입이라면,
		Arrays.sort(a); // 배열의 원소를 정렬, DualPivotQuicksort: 퀵정렬과 병합정렬 합친 것
		System.out.println(Arrays.toString(a));
		
		Integer[] b = {6,1,8,2,4}; // 객체 타입이라면,
		Arrays.sort(b); // 타입고 개수에 따라 다른 정렬방법이 실행된다. ComparableTimSort: 삽입정렬과 병합정렬 합친 것
		System.out.println(Arrays.toString(b));
		
		Arrays.sort(b,Comparator.reverseOrder()); // 역순으로 정렬
		System.out.println(Arrays.toString(b));
		
		List<Integer> l = new ArrayList<Integer>();
		l.add(3);
		l.add(5);
		l.add(1);
		l.add(2);
		Collections.sort(l); // Collections는 list객체만 정렬해줌
		l.sort(null);
		System.out.println(l.toString());
		
		
	}
}
