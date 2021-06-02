package com.boj.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이) 기본 LCS(Longest Common Subsequence) 최장 공통 부분수열 크기 구하기
 * 
 * 최장 공통 부분수열이란?
 * -> 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것 
 * -> 부분수열이기 때문에 문자사이를 건너뛰면서 가장 긴 부분 문자열을 만든다.
 * 
 * 처음에는 a,b를 String으로 입력받아 charAt을 사용했는데 164ms 
 * a,b를 char[]로 입력받으면 144ms
 * 
 * @author jugia
 *
 */
public class Main_Gold5_9251_LCS {

	static char[] a, b;
	static int[][] LCS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		a = br.readLine().toCharArray();
		b = br.readLine().toCharArray();

		LCS = new int[a.length + 1][b.length + 1]; // 테두리를 설정하기 위해 +1

		for (int i = 0; i < a.length + 1; i++) {
			for (int j = 0; j < b.length + 1; j++) {
				if (i == 0 || j == 0) { // 테두리 설정
					LCS[i][j] = 0;
					continue;
				}

				if (a[i - 1] == b[j - 1]) { // 공통부분이 연속되는 경우
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
				} else { // 현재 문자가 다를 때, 지금까지 진행된 과정 중 가장 큰 공통길이를 가지고 있는다.(DP)
					LCS[i][j] = Math.max(LCS[i][j - 1], LCS[i - 1][j]);
				}
			}
		}

		System.out.println(LCS[a.length][b.length]); // 가장 끝 점이 LCS(최장 공통 문자열)
	}
}
