package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_1915_가장큰정사각형 {

	static int N, M, Ans;
	static int[][] memoi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Ans = 0;

		memoi = new int[N + 1][M + 1];

		if (N == 1 && M == 1) {
			System.out.println(1);
		} else {
			for (int i = 1; i <= N; i++) {
				String input = br.readLine();
				for (int j = 1; j <= M; j++) {
					int temp = input.charAt(j - 1) - '0';

					if (i == 1 && j == 1) {
						memoi[i][j] = temp;
					} else {
						if (temp == 1) {
							memoi[i][j] = Math.min(Math.min(memoi[i - 1][j], memoi[i][j - 1]), memoi[i - 1][j - 1]) + 1;
							Ans = Math.max(Ans, memoi[i][j]);
						}
					}
				}
			}
		}

		System.out.println(Ans * Ans);

	}
}
