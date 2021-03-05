package com.programmers;

public class Solution_level1_신규아이디추천 {
	public static void main(String[] args) {
		
		String input = "...!@BaT#*..y.abcdefghijklm";

		System.out.println(solution(input));
	}

	public static String solution(String new_id) {
		String answer = "";
		
		// 1. 소문자 -> 대문자
//		new_id = new_id.toLowerCase();
//		System.out.println(new_id);
		
		// 2. 소문자, 숫자, 빼기(-), 밑줄(_), 맞침표(.)를 제외한 문자 제거
		// 65 <= 대문자 <= 90 
		// 97 <= 소문자 <= 122
		String tmp = "";
		
		int count = 0;
		for(int i=0;i<new_id.length();i++) {
			if(new_id.charAt(i) == '.') {
				
			}
		}
		
		
		
		for(int i=0;i<new_id.length();i++) {
			if(i == 0 && new_id.charAt(i) == '.') {
				continue;
			} else if(i == new_id.length()-1 && new_id.charAt(i) == '.') {
				continue;
			} else if(new_id.charAt(i) >= 65 && new_id.charAt(i) <= 90) {
				count = 0;
				tmp += (char)(new_id.charAt(i) + 32);
			} else if(new_id.charAt(i) >= 97 && new_id.charAt(i) <= 122) {
				count = 0;
				tmp += new_id.charAt(i);
			} else if(new_id.charAt(i) == '-' || new_id.charAt(i) == '_') {
				count = 0;
				tmp += new_id.charAt(i);
			}else if(new_id.charAt(i) == '.' && count == 0) {
				count++;
				tmp += new_id.charAt(i);
			}
		}
		System.out.println(tmp);
		
		

		return answer;
	}
}
