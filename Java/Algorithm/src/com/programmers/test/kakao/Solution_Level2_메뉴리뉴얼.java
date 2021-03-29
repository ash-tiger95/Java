package com.programmers.test.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Solution_Level2_메뉴리뉴얼 {

	static char[] output;
	static int max;
	static HashMap<String, Integer> hm;

	public static String[] solution(String[] orders, int[] course) {

		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < orders.length; i++) { // 주문목록 오름차순으로 정렬
			char[] arr = orders[i].toCharArray();
			Arrays.sort(arr);
			orders[i] = String.valueOf(arr);
		}

		for (int i = 0; i < course.length; i++) {
			hm = new HashMap<>();
			max = 0; // 메뉴 조합이 손님 최소 2명이상 이용해야 해서 이를 확인하기 위함

			for (int j = 0; j < orders.length; j++) {
				output = new char[course[i]];
				nCr(course[i], orders[j], 0, 0);
			}

			for (String key : hm.keySet()) {
				if (max == 1) { // 메뉴 조합이 한 손님밖에 없으면 불가능
					break;
				}
				if (max == hm.get(key)) { // max 개수로 이루어진 조합만 가져옴
					list.add(key);
				}
			}
		}

		Collections.sort(list); // 정답을 위해 정렬
		String[] answer = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
//        System.out.println(Arrays.toString(answer));

		return answer;
	}

	private static void nCr(int course, String order, int cnt, int cur) {
		if (cnt == course) {
			String s = "";
			for (int i = 0; i < output.length; i++) {
				s += output[i];
			}

			hm.put(s, hm.getOrDefault(s, 0) + 1); // 값이 없으면 1개
			max = Math.max(max, hm.get(s));

			return;
		}

		for (int i = cur; i < order.length(); i++) {

			output[cnt] = order.charAt(i);
			nCr(course, order, cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) {

		String[] orders = { "XYZ", "XWY", "WXA" };
		int[] course = { 2, 3, 4 };

		System.out.println(solution(orders, course));

	}

}
