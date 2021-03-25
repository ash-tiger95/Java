package com.boj.dp.memoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver1_1149_RGB거리 {

	static int N, Ans;
	static int[][] map;
	static int[][] memoi;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 집 개수, 2 <= N <= 1,000

		map = new int[N][3]; // [집][RGB]
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Ans = Integer.MAX_VALUE; // 최소비용 구하기

		memoi = new int[N][3];
		for (int i = 0; i < N; i++) {
			Arrays.fill(memoi[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < 3; i++) {
			memoi[0][i] = map[0][i];
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) { // 0: R, 1: G, 2: B
				if (memoi[i][j] > memoi[i - 1][(j + 1) % 3] + map[i][j]) {
					memoi[i][j] = memoi[i - 1][(j + 1) % 3] + map[i][j];
				}

				if (memoi[i][j] > memoi[i - 1][(j + 2) % 3] + map[i][j]) {
					memoi[i][j] = memoi[i - 1][(j + 2) % 3] + map[i][j];
				}
			}
		}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<3;j++) {
//				System.out.print(memoi[i][j]+" ");
//			}
//			System.out.println();
//		}

		for (int i = 0; i < 3; i++) {
			Ans = Math.min(Ans, memoi[N - 1][i]);
		}
		System.out.println(Ans);
	}

}
