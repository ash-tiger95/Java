package com.programmers.test.kakao;

public class Solution_Level2_신규아이디추천_효율성 {

	public static String solution(String new_id) {
		String answer;

		// 1단계
		String step1 = new_id.toLowerCase();

		// 2단계
		char[] step1_arr = step1.toCharArray();
		StringBuilder step2 = new StringBuilder();
		for (char c : step1_arr) {
			if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.') {
				step2.append(c);
			}
		}

		// 3단계 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		String step3 = step2.toString().replace("..", ".");
		while (step3.contains("..")) {
			step3 = step3.replace("..", ".");
		}

		// 4단계 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		String step4 = step3;
		if (step4.length() > 0) {
			if (step4.charAt(0) == '.') {
				step4 = step4.substring(1, step4.length());
			}
		}
		if (step4.length() > 0) {
			if (step4.charAt(step4.length() - 1) == '.') {
				step4 = step4.substring(0, step4.length() - 1);
			}
		}

		// 5단계 빈 문자열이라면, new_id에 "a"를 대입합니다.
		String step5 = step4;
		if (step5.equals("")) {
			step5 = "a";
		}

		// 6단계 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
	    // 	   만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		String step6 = step5;
		if (step6.length() >= 16) {
			step6 = step6.substring(0, 15);

			if (step6.charAt(step6.length() - 1) == '.') {
				step6 = step6.substring(0, step6.length() - 1);
			}
		}

		// 7단계 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		StringBuilder step7 = new StringBuilder(step6);
		if (step7.length() <= 2) {
			char last = step7.charAt(step7.length() - 1);

			while (step7.length() < 3) {
				step7.append(last);
			}
		}

		answer = String.valueOf(step7);
		return answer;
	}

	public static void main(String[] args) {
		String new_id = "...!@BaT#*..y.abcdefghijklm";

		System.out.println(solution(new_id));
	}
}
