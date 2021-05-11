package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver2_1890_점프 {
	static int N, Ans;
	static int[][] map;
	static long[][] memoi;
	static int[][] dirs = { { 0, 1 }, { 1, 0 } }; // 오른쪽 || 아래쪽

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Ans = 0;

		map = new int[N][N];
		memoi = new long[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memoi[i][j] = -1;
			}
		}

		System.out.println(dfs(0, 0));

	}

	private static long dfs(int sy, int sx) {
		if (sy == N - 1 && sx == N - 1) {
			return 1;
		}

		if (memoi[sy][sx] != -1) {
			return memoi[sy][sx];
		}

		memoi[sy][sx] = 0;

		int ny = sy + map[sy][sx];
		int nx = sx + map[sy][sx];

		if (ny >= N && nx >= N) {
			return 0;
		}

		if (ny < N) {
			memoi[sy][sx] = Math.max(memoi[sy][sx], memoi[sy][sx] + dfs(ny, sx));
		}
		if (nx < N) {
			memoi[sy][sx] = Math.max(memoi[sy][sx], memoi[sy][sx] + dfs(sy, nx));
		}

		return memoi[sy][sx];

	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

	private static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
