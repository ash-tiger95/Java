package com.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver2_11047_동전0 {
	static int N, K, Ans;
	static int[] values;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Ans = 0;
		values = new int[N];
		for (int i = 0; i < N; i++) {
			values[i] = Integer.parseInt(br.readLine());
		}

		for (int i = N - 1; i >= 0; i--) {
			int tmp = K % values[i];
			Ans += (K / values[i]);
			K = tmp;
		}
		System.out.println(Ans);
	}
}
