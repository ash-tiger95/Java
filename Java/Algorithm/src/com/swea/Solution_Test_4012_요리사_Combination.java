package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Test_4012_요리사_Combination {
	static int T, N, Ans;
	static int[][] map;
	static int[] output;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			Ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			output = new int[N / 2];
			visited = new boolean[N];
			Combination(0, 0);
			sb.append(Ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void Combination(int cnt, int start) {
		if (cnt == N / 2) {
			int[] otherOutput = new int[N / 2];
			int index = 0;
			for (int i = 0; i < N; i++) {
				if(!visited[i]) {
					otherOutput[index] = i;
					index++;
				}
			}

			int sum1 = 0;
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					sum1 += map[output[i]][output[j]];
				}
			}
			int sum2 = 0;
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					sum2 += map[otherOutput[i]][otherOutput[j]];
				}
			}
			Ans = Math.min(Ans, Math.abs(sum1 - sum2));

			return;
		}
		for (int i = start; i < N; i++) {
			visited[i] = true;
			output[cnt] = i;
			Combination(cnt + 1, i + 1);
			visited[i] = false;
		}
	}
}
