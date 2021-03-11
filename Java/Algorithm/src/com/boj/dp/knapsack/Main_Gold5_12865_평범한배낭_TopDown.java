package com.boj.dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold5_12865_평범한배낭_TopDown {
	static int N, K;
	static int[][] memoi;
	static int[] W, V; // weight, value

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 물품 수, 1 <= N <= 100
		K = Integer.parseInt(st.nextToken()); // 배낭 무게, 1 <= K <= 100,000

		W = new int[N]; // 무게
		V = new int[N]; // 가치

		memoi = new int[N][K + 1];
		for(int i=0;i<N;i++) {
			Arrays.fill(memoi[i], -1);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(knapsack(N - 1, K));
	}

	static int knapsack(int i, int k) {
		for(int n=0;n<N;n++) {
			for(int j=0;j<K+1;j++) {
				System.out.print(memoi[n][j]+" ");
			}
			System.out.println();
		}
		System.out.println("----------- "+i+" "+k);
		// i가 0미만, 즉 범위 밖이 된다면
		if (i < 0)
			return 0;
		
		// 탐색하지 않은 위치라면?
		if (memoi[i][k] == -1) {
			// 현재 물건(i)을 추가로 못담는 경우 (이전 i값 탐색)
			if (W[i] > k) {
				memoi[i][k] = knapsack(i - 1, k);
			}
			// 현재 물건(i)을 담을 수 있는 경우
			else {
				// 이전 i값과 이전 i값에 대한 k-W[i]의 값 + 현재 가치(V[i])중 큰 값을 저장
				memoi[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - W[i]) + V[i]);
			}
		}
		return memoi[i][k];
	}

}
