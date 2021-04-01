package com.programmers.test.kakao;

public class Solution_Level2_신규아이디추천_정규표현식 {

	public static String solution(String new_id) {
		String answer = new_id.toLowerCase(); // 1단계

		answer = answer.replaceAll("[^-_.a-z0-9]", ""); // 2단계
		answer = answer.replaceAll("[.]{2,}", "."); // 3단계
		answer = answer.replaceAll("^[.]|[.]$", ""); // 4단계

		if (answer.equals("")) { // 5단계
			answer += "a";
		}

		if (answer.length() >= 16) { // 6단계
			answer = answer.substring(0, 15);
			answer = answer.replaceAll("[.]$", "");
		}

		if (answer.length() <= 2) { // 7단계
			while (answer.length() < 3) {
				answer += answer.charAt(answer.length() - 1);
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		String new_id = "...!@BaT#*..y.abcdefghijklm";

		System.out.println(solution(new_id));
	}
}
