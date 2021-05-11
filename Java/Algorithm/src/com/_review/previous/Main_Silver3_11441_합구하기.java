package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver3_11441_합구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 단어 개수, 1 <= N <= 100,000

		int[] prefixSum = new int[N + 1]; // 구간 합 (0번째는 0으로 가지고 있어야 편하다.)
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			if (i == 1) {
				prefixSum[i] = Integer.parseInt(st.nextToken());
			} else {
				prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken()); // [0]은 0
			}
		}

		int M = Integer.parseInt(br.readLine()); // 구간 개수, 1 <= M <= 100,000
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			System.out.println(prefixSum[b] - prefixSum[a - 1]); // a부터 b까지의 합
		}

	}

}
