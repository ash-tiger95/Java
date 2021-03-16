package com.programmers.x;

public class Solution_조이스틱 {
	// 마지막 테케에서 걸린다.. 아직 뭐가 문제인지 모르겟다...
	static int len;

	public int solution(String name) {
		int answer = 0;
		len = name.length();

		answer += searchA(name);
		answer += alpha(name);
		System.out.println(answer);
		return answer;

	}

	// 마지막 A 찾기
	private static int searchA(String name) {
		int temp = 0;
		// 오른쪽 맨 끝부터 왼쪽으로 검사
		for (int i = len - 1; i >= 0; i--) {
			if (name.charAt(i) != 'A') {
				temp = i; // i번 이동
				break;
			}
		}
		// 두번째부터 오른쪽으로 검사
		for (int i = 1; i < len; i++) {
			if (name.charAt(i) != 'A') {
				temp = Math.min(temp, len - i);
				break;
			}
		}

		return temp;
	}

	private static int alpha(String name) {
		int temp = 0;
		for (int i = 0; i < len; i++) {
			if (name.charAt(i) - 'A' <= 'N' - 'A') {
				temp += name.charAt(i) - 'A';
			} else {
				temp += 'Z' - name.charAt(i) + 1;
			}
		}
		return temp;
	}
}
