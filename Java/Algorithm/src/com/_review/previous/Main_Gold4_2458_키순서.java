package com._review.previous;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author jugia
 *
 */

public class Main_Gold4_2458_키순서 {

	static int N, M, Ans;
	static final int INF = 501;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학생 수, 2 <= N <= 500
		M = Integer.parseInt(st.nextToken()); // 비교 횟수, 0 <= M <= N(N-1)/2

		int max = Integer.MAX_VALUE;

		int[][] student = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(student[i], INF);
		}

		for (int i = 0; i < N; i++) {
			student[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			student[a][b] = Math.min(student[a][b], 1);
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (student[i][j] > student[i][k] + student[k][j]) {
						student[i][j] = student[i][k] + student[k][j];
					}
				}
			}
		}

		Ans = 0;
		int[] path = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				if (student[i][j] < INF) {
					path[i] += 1;
					path[j] += 1;
					if (path[i] == N - 1) {
						Ans += 1;
					}
					if (path[j] == N - 1) {
						Ans += 1;
					}
				}
			}
		}
		System.out.println(Ans);

	}
}
