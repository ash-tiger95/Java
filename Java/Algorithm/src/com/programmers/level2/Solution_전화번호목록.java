package com.programmers.level2;

import java.util.HashMap;

public class Solution_전화번호목록 {
	public static void main(String[] args) {
		String[] phone_book = { "119", "97674223", "1195524421" };
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		

		// 풀이 1 -> 최적화 x
		/*
		
		boolean answer = true;
		
		for (int i = 0; i < phone_book.length; i++) {
			for (int j = 0; j < phone_book.length; j++) {
				if (i == j)
					continue;
				if (phone_book[j].indexOf(phone_book[i]) == 0) { // 접두어라면
					return false;
				}
			}
		}
		return answer;
		*/
		
		
		// 풀이 2 -> 최적화 x
		/*
		for (int i = 0; i < phone_book.length - 1; i++) {
			for (int j = i + 1; j < phone_book.length; j++) {
				if (phone_book[i].startsWith(phone_book[j])) {
					return false;
				}
				if (phone_book[j].startsWith(phone_book[i])) {
					return false;
				}
			}
		}
		return true;
		*/
		
		// 풀이 3 -> 최적화 o
		boolean answer = true;
		HashMap<String, String> hm = new HashMap<>();

		// 중복을 제거해서 해시 맵에 모두 저장
		for (String input : phone_book) {
			hm.put(input, input);
		}

		// 다른 관점이다. : input 데이터를 분해해서 input의 prefix를 다른 string에서 찾는 것이다.
		for (String target : phone_book) {
			// target을 분해하며 다른 곳에서 prefix 있는지 찾기
			for (int i = 0; i < target.length(); i++) {
				// 이거랑
				if (hm.containsKey(target.substring(0, i))) {
					return false;
				}
				// 이거랑 같은 말이야.
				else if (hm.get(target.substring(0, i)) != null) {
					return false;
				}
			}

		}
		return answer;
	
		
		
	}
}
