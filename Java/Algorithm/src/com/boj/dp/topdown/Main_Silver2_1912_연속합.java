package com.boj.dp.topdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver2_1912_연속합 {

	static int N, ans;
	static int[] in;
	static Integer[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		in = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		dp = new Integer[N];
		dp[0] = in[0];
		ans = in[0];

		recur(N - 1);
		System.out.println(ans);
	}

	private static int recur(int N) {
		if (dp[N] == null) {
			dp[N] = Math.max(in[N], recur(N - 1) + in[N]);

			ans = Math.max(dp[N], ans);
		}

		return dp[N];
	}
}
