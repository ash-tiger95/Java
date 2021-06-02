package com.boj.lcs;

import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 문제) 기본 LCS(Longest Common Substring) 최장 공통 문자열
 * 
 * Longest Common Subsequence와 다른점은 a[i]와 b[j]가 다를 경우 LCS[i][j] = 0;
 * 
 * @author jugia
 *
 */
public class Main_Gold5_5582_공통부분문자열 {

	static int Ans;
	static char[] a, b;
	static int[][] LCS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Ans = 0;

		a = br.readLine().toCharArray();
		b = br.readLine().toCharArray();

		LCS = new int[a.length + 1][b.length + 1];

		for (int i = 0; i < a.length + 1; i++) {
			for (int j = 0; j < b.length + 1; j++) {
				/*
				if (i == 0 || j == 0) {
					LCS[i][j] = 0;
					continue;
				}
				*/

				if (a[i - 1] == b[j - 1]) {
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
					if (LCS[i][j] > Ans) {
						Ans = LCS[i][j];
					}
				} /* else {
					LCS[i][j] = 0; // ★ Longest Common Subsequence와 다른점
				} */
				
				// default가 0이므로 사실 필요없는 작업
			}
		}

		System.out.println(Ans);
	}
}
