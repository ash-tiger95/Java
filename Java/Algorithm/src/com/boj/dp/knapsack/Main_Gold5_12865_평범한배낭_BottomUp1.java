package com.boj.dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold5_12865_평범한배낭_BottomUp1 {
	static int N, K;
	static int[][] memoi;
	static int[] W, V; // weight, value

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 물품 수, 1 <= N <= 100
		K = Integer.parseInt(st.nextToken()); // 배낭 무게, 1 <= K <= 100,000

		W = new int[N + 1]; // 무게
		V = new int[N + 1]; // 가치

		memoi = new int[N + 1][K + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < K + 1; j++) {
				// i번째 무게를 더 담을 수 없는 경우
				if (W[i] > j) {
					memoi[i][j] = memoi[i - 1][j];
				}
				// i번째 무게를 더 담을 수 있는 경우
				else {
					memoi[i][j] = Math.max(memoi[i - 1][j], memoi[i - 1][j - W[i]] + V[i]);
				}
			}
		}
		System.out.println(memoi[N][K]);

	}

}
