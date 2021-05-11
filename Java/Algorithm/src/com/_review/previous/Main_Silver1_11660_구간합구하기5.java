package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver1_11660_구간합구하기5 {

	static int N, M;
	static int[][] prefixSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		prefixSum = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1]
						+ Integer.parseInt(st.nextToken());
			}
		}

		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());

			sb.append(prefixSum[ey][ex] - prefixSum[ey][sx - 1] - prefixSum[sy - 1][ex] + prefixSum[sy - 1][sx - 1])
					.append("\n");
		}
		System.out.println(sb);
	}
}
