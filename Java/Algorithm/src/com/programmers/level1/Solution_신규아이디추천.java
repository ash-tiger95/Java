package com.programmers.level1;

public class Solution_신규아이디추천 {
	public static void main(String[] args) {

		String input = "...!@BaT#*..y.abcdefghijklm";

		System.out.println(solution(input));
	}

	public static String solution(String new_id) {
		String answer = "";

		// 1. 소문자 -> 대문자
		new_id = new_id.toLowerCase();
		System.out.println(new_id);

		// 2. 소문자, 숫자, 빼기(-), 밑줄(_), 맞침표(.)를 제외한 문자 제거 (65 <= 대문자 <= 90, 97 <= 소문자 <=
		// 122)
		String id = "";
		for (int i = 0; i < new_id.length(); i++) {
			char ch = new_id.charAt(i);

			if (ch >= 'a' && ch <= 'z') {
				id += String.valueOf(ch); // String.valueOf(): Object 형태의 값을 String 형태로 변환
			} else if (ch >= '0' && ch <= '9') {
				id += String.valueOf(ch);
			} else if (ch == '.' || ch == '-' || ch == '_') {
				id += String.valueOf(ch);
			}
		}

		// 3. . 하나만
		for (int i = 0; i < id.length(); i++) {
			if (id.charAt(i) == '.') {
				int j = i + 1;
				String dot = ".";

				while (j != id.length() && id.charAt(j) == '.') {
					dot += ".";
					j++;
				}

				if (dot.length() > 1)
					id = id.replace(dot, ".");
			}
		}

		return answer;
	}
}
