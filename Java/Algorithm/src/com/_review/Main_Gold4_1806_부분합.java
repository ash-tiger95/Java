package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold4_1806_부분합 { // Two Pointer

	static int N, S, Ans;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Ans = Integer.MAX_VALUE;

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		in = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 0;
		long sum = 0;

		while (end <= N) {
			if (sum >= S) {
				Ans = Math.min(Ans, end - start); // S를 넘을 때마다 최소 길이 검사
				sum -= in[start++];
			} else {
				sum += in[end++];
			}

		}

		System.out.println(Ans == Integer.MAX_VALUE ? 0 : Ans);
	}
}
