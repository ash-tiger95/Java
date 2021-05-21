package com.swea.swtest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_Test_4012_요리사 {
	static int T, N, Ans;
	static int[][] map;

	static boolean[] isSelected;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\4012.txt")));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Ans = Integer.MAX_VALUE;
			isSelected = new boolean[N];
			numbers = new int[N];
			nCr(0, 0);
			System.out.println("#" + t + " " + Ans);
		} // for T
	}

	private static void nCr(int cur, int cnt) {
		if (cnt == N / 2) {

			int sum1 = 0;
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					if (i != j) {
						sum1 += map[numbers[i]][numbers[j]];
					}
				}
			}

			int[] otherNumbers = new int[N / 2];
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (!isSelected[i]) {
					otherNumbers[idx] = i;
					idx++;
				}
			}
//			System.out.println("------");
//			System.out.println(Arrays.toString(isSelected));
//			System.out.println(Arrays.toString(otherNumbers));

			int sum2 = 0;
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					if (i != j) {
						sum2 += map[otherNumbers[i]][otherNumbers[j]];

					}
				}
			}

			Ans = Math.min(Ans, Math.abs(sum1 - sum2));
			return;
		}

		for (int i = cur; i < N; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				numbers[cnt] = i;
				nCr(i + 1, cnt + 1);
				isSelected[i] = false;

			}
		}
	}

}
