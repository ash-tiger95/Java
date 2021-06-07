package com.boj.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_12865_평범한배낭_BottomUp {

	static int N, K;
	static int[] W, V;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물품의 수, 1 <= N <= 100
		K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게, 1 <= K <= 100,000

		W = new int[N + 1]; // 무게
		V = new int[N + 1]; // 가치

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {

				if (W[i] > j) { // i번째 무게를 더 담을 수 없는 경우
					dp[i][j] = dp[i - 1][j];
				} else { // i번째 무게를 더 담을 수 있는 경우
					dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - W[i]] + V[i]);
				}
			}
		}

		System.out.println(dp[N][K]);

		br.close();
	}
}
