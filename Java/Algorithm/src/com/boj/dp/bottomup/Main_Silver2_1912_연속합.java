package com.boj.dp.bottomup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Silver2_1912_연속합 {

	static int N, ans;
	static int[] in;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		in = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = in[i];
			if (i == 0) {
				continue;
			}
			if (dp[i] < dp[i - 1] + in[i]) {
				dp[i] = dp[i - 1] + in[i];
			}

		}

		int ans = dp[0];
		for (int i = 0; i < N; i++) {
			if (ans < dp[i]) {
				ans = dp[i];
			}
		}
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
