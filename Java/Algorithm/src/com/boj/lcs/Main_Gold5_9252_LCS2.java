package com.boj.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 문제) 최장 공통 부분수열 구하기 
 * -> 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것 
 * -> 부분수열이기 때문에 문자사이를 건너뛰면서 가장 긴 부분 문자열을 만든다.
 * 
 * @author jugia
 *
 */
public class Main_Gold5_9252_LCS2 {

	static char[] a, b;
	static int[][] LCS;
	static Stack<Character> result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		a = br.readLine().toCharArray();
		b = br.readLine().toCharArray();

		LCS = new int[a.length + 1][b.length + 1];

		// 1. LCS 배열 만들기
		for (int i = 0; i < a.length + 1; i++) {
			for (int j = 0; j < b.length + 1; j++) {
				if (i == 0 || j == 0) {
					LCS[i][j] = 0;
					continue;
				}

				if (a[i - 1] == b[j - 1]) {
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
				} else {
					LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
				}
			}
		}

		result = new Stack<>();

		// 2. 거꾸로 탐색
		int idx = 0;
		int p1 = a.length, p2 = b.length;

		while (true) {
			if (p1 == 0 || p2 == 0) {
				break;
			}

			if (LCS[p1][p2] == LCS[p1 - 1][p2]) { // 위에 검사
				p1--;
				continue;
			} else if (LCS[p1][p2] == LCS[p1][p2 - 1]) { // 왼쪽 검사
				p2--;
				continue;
			}

			if (LCS[p1][p2] == LCS[p1 - 1][p2 - 1] + 1) { // 대각선 검사
				result.add(a[p1-1]);
				p1--;
				p2--;
			}
		}
		
		while(!result.isEmpty()) {
			sb.append(result.pop());
		}
		
		System.out.println(LCS[a.length][b.length]); // 최장 공통 부분수열 크기
		System.out.println(sb); // 최장 공통 부분수열
	}
}
