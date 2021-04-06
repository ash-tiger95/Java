package com.test.day0403_kakaocommerce;

import java.util.TreeMap;

public class Solution01 {
	public static int solution(int[] gift_cards, int[] wants) {
		int answer = 0; // 최소

		int N = wants.length;
		TreeMap<Integer, Integer> gc = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			gc.put(gift_cards[i], gc.getOrDefault(gift_cards[i], 0) + 1);
		}

		for (int i : wants) {
			if (gc.containsKey(i) && gc.get(i) != 0) {
				gc.put(i, gc.get(i) - 1);
				answer++;
			}
		}

		return N - answer;
	}

	public static void main(String[] args) {
		int[] gift_cards = { 1, 4, 5, 4, 5 };
		int[] wants = { 4, 5, 4, 5, 4 };

//		int[] gift_cards = { 4,5,3,2,1 };
//		int[] wants = { 2,4,4,5,1 };

//		int[] gift_cards = { 5,4,5,4,5};
//		int[] wants = {1,2,3,5,4};

		System.out.println(solution(gift_cards, wants));
	}
}
