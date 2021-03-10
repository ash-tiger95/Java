package com.programmers.level2;

import java.util.HashMap;

public class Solution_위장 {
	public static void main(String[] args) {
		String[][] clothes = { { "yellowhat", "headgear" }, { "bluesunglasses", "eyewear" },
				{ "green_turban", "headgear" } };

		System.out.println(solution(clothes));
	}

	public static int solution(String[][] clothes) {
		int answer = 1;

		HashMap<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < clothes.length; i++) {
			hm.put(clothes[i][1], hm.getOrDefault(clothes[i][1], 0) + 1);
		}

//		System.out.println(hm);

		for (String key : hm.keySet()) {
			answer *= (hm.get(key) + 1);
		}

		return answer - 1;
	}
}
