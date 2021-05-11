package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold4_1806_부분합 {

	static int N, S, Ans;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // N개의 수열, 10 <= N <= 100,000
		S = Integer.parseInt(st.nextToken()); // 합이 S 이상이 되는 구간합 구하기, 0 <= S <= 100,000,000

		st = new StringTokenizer(br.readLine());
		map = new int[N + 1];
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		Ans = Integer.MAX_VALUE; // 가장 짧은 길이 구하기
		int s = 0, e = 0;
		long sum = 0;

		while (e <= N) {
			if (sum >= S) { // S보다 크면 s 늘리기
				Ans = Math.min(Ans, e - s); // 최솟값 검사
				sum -= map[s++];
			} else {
				sum += map[e++]; // S보다 작으면 e 늘리기
			}

		}

		System.out.println(Ans == Integer.MAX_VALUE ? 0 : Ans);

	}

}
