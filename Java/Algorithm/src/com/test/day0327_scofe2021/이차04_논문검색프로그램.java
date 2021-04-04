package com.test.day0327_scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이차04_논문검색프로그램 {

	static int N, Q;
	static String[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 색인어 리스트, 1 <= N <= 1,000
		StringBuilder sb = new StringBuilder();
		list = new String[N];
		for (int i = 0; i < N; i++) {
			list[i] = br.readLine();
		}

		Q = Integer.parseInt(br.readLine()); // 검색어 리스트, 1 <= Q <= 100
		for (int i = 0; i < Q; i++) {
			String input = br.readLine();
			int ans = 0;
			for (int j = 0; j < N; j++) {
				if (KMP( list[j],input)) {
					ans++;
				}
				
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	
	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			// 맞는 경우
			if (pattern.charAt(i) == pattern.charAt(j)) {
				// i길이 문자열의 공통길이는 j의 위치 + 1
				pi[i] = ++j;
			}
			// 안 맞는 경우
			else {
				if (j == 0) {
					continue;
				}
				// 맞는 날이 올때까지 하나 전칸에서의 공통부분 위치로 이동
				while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
					j = pi[j - 1];
				}
			}
		}
		return pi;
	}

	static boolean KMP(String origin, String pattern) {
		int[] pi = getPi(pattern);
		int j = 0;
		for (int i = 0; i < origin.length(); i++) {
			while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			// 맞는 경우
			if (origin.charAt(i) == pattern.charAt(j)) {
				if (j == pattern.length() - 1) {
					j = pi[j]; // 마지막 공통 부분 문자열로 이동
					return true;
				} else {// 맞았지만 패턴이 끝나지 않았다면
					j++; // j를 하나 증가
				}
			}
		}
		return false;
	}

}
