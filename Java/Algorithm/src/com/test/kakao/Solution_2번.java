package com.test.kakao;

import java.util.Arrays;

public class Solution_2번 {

	static int M, N, answer;
	static int[] output;

	public static int solution(int[][] needs, int r) {
		answer = Integer.MIN_VALUE;
		N = needs[0].length;
		M = r; // r개 선택
		output = new int[M];

		nCr(0, 0, needs);

		return answer;
	}

	private static void nCr(int cnt, int cur, int[][] needs) {
		if (cnt == M) {
			int count1 = 0;
			int count = 0;
			int temp = 0;
			for (int i = 0; i < needs.length; i++) {
				count = 0;
				count1 = 0;
				for (int j = 0; j < needs[i].length; j++) {
					if (needs[i][j] == 1) {
						count1++;
					}

				}
				for (int j = 0; j < M; j++) {
					if (needs[i][output[j]] == 1) {
						count++;
					}
				}

				if (count == count1) {
					temp++;
				}
			}

			answer = Math.max(answer, temp);

			// 카운트 개수와 needs[i]의 1 개수랑 같으면 answer 추가

			return;
		}

		for (int i = cur; i < N; i++) {

			output[cnt] = i;
			nCr(cnt + 1, i + 1, needs);
		}

	}

	public static void main(String[] args) {
		int[][] needs = { { 1, 0, 0 }, { 1, 1, 0 }, { 1, 1, 0 }, { 1, 0, 1 }, { 1, 1, 0 }, { 0, 1, 1 } }; // 세로(완제품 개수),
																											// 1 <= i <=
																											// 1,000
		int r = 2;
		System.out.println(solution(needs, r));
	}
}
