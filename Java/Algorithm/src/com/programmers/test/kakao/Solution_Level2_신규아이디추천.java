package com.programmers.test.kakao;

public class Solution_Level2_신규아이디추천 {

	public static String solution(String new_id) {
		String answer = "";

		// 1단계. 모든 대문자를 소문자로 치환
		new_id = new_id.toLowerCase();

		// 2단계. 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		String temp = "";
		for (int i = 0; i < new_id.length(); i++) {
			if ((new_id.charAt(i) - '0' >= 0 && new_id.charAt(i) - '0' <= 9)
					|| (new_id.charAt(i) - 'a' >= 0 && new_id.charAt(i) - 'a' <= 25) || new_id.charAt(i) == '-'
					|| new_id.charAt(i) == '_' || new_id.charAt(i) == '.') {
				temp += new_id.charAt(i);
			}
		}
		new_id = temp;

		// 3단계. 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		temp = "";
		temp += new_id.charAt(0);
		for (int i = 1; i < new_id.length(); i++) {
			if (new_id.charAt(i - 1) == '.' && new_id.charAt(i) == '.') {
				continue;
			} else {
				temp += new_id.charAt(i);
			}
		}
		System.out.println("#3 " + temp);

		// 4단계. 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		if (temp.length() >= 1) {
			if (temp.charAt(0) == '.') {
				temp = temp.substring(1, temp.length());
			}
		}

		if (temp.length() >= 1) {
			if (temp.charAt(temp.length() - 1) == '.') {
				temp = temp.substring(0, temp.length() - 1);
			}
		}

		// 5단계. 빈 문자열이라면, new_id에 "a"를 대입합니다.
		if (temp.length() == 0) {
			temp = "a";
		}

		// 6단계. 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
		// 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.

		if (temp.length() >= 16) {
			temp = temp.substring(0, 15);
		}

		if (temp.charAt(temp.length() - 1) == '.') {
			temp = temp.substring(0, temp.length() - 1);
		}

		// 7단계. 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		while (temp.length() <= 2) {
			temp += temp.charAt(temp.length() - 1);
		}

		answer = temp;

		return answer;
	}

	public static void main(String[] args) {
		String new_id = "...!@BaT#*..y.abcdefghijklm";

		System.out.println(solution(new_id));
	}
}
