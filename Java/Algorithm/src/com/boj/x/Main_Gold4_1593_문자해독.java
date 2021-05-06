package com.boj.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold4_1593_문자해독 {

	static int wLen, sLen, Ans;
	static char[] W, S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Ans = 0;
		wLen = Integer.parseInt(st.nextToken()); // 단어 W의 길이, 1 <= wLen <= 3,000
		sLen = Integer.parseInt(st.nextToken()); // 벽화 S의 길이, wLen <= S <= 3,000,000

		W = new char[wLen];
		S = new char[sLen];

		W = br.readLine().toCharArray();
		S = br.readLine().toCharArray();

		int[] alpha = new int[52];
		for (int i = 0; i < wLen; i++) {
			if (W[i] >= 'A' && W[i] <= 'Z') { // 대문자인 경우
				alpha[W[i] - 'A']++;
			} else { // 소문자인 경우
				alpha[26 + W[i] - 'a']++;
			}
		}

//		System.out.println(Arrays.toString(alpha));

		for (int i = 0; i < sLen; i++) {
			if (S[i] >= 'A' && S[i] <= 'Z') { // 대문자인 경우
				if (alpha[S[i] - 'A'] > 0) {
					int[] cloneAlpha = alpha.clone();
					dfs(cloneAlpha, i, 0);
				} else {
					continue;
				}
			} else { // 소문자인 경우
				if (alpha[S[i] - 'a' + 26] > 0) {
					int[] cloneAlpha = alpha.clone();
					dfs(cloneAlpha, i, 0);

				} else {
					continue;
				}
			}
		}

		System.out.println(Ans);

	}

	private static void dfs(int[] alpha, int index, int count) {
		if(index >= sLen) {
			return;
		}
		if (count == wLen) {
//			System.out.println("in");
			Ans++;
			return;
		}

		if (S[index] >= 'A' && S[index] <= 'Z') { // 대문자인 경우
			if (alpha[S[index] - 'A'] > 0) {
				alpha[S[index] - 'A']--;
				dfs(alpha, index + 1, count + 1);
			} else {
				return;
			}

		} else { // 소문자인 경우
			if (alpha[S[index] - 'a' + 26] > 0) {
				alpha[S[index] - 'a' + 26]--;
				dfs(alpha, index + 1, count + 1);
			} else {
				return;
			}
		}

	}
}
