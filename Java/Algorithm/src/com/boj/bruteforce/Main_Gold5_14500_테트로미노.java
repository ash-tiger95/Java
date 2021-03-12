package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_14500_테트로미노 {
	static int N, M, Ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M];
		Ans = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, map[i][j]);
				visited[i][j] = false;
				exception(i, j, map[i][j]);
			}
		}
		System.out.println(Ans);

	}

	private static void exception(int y, int x, int value) {
		int wing = 4;
		int min = Integer.MAX_VALUE;
		int sum = value;

		for (int d = 0; d < 4; d++) {
			int ny = y + dirs[d][0];
			int nx = x + dirs[d][1];

			if (!boundary(ny, nx)) {
				wing--;
				continue;
			}

			if (wing < 3) {
				return;
			}

			min = Math.min(min, map[ny][nx]);
			sum += map[ny][nx];

		}
		if (wing == 4) {
			sum = sum - min;
		}

		Ans = Math.max(Ans, sum);
	}

	private static void dfs(int y, int x, int cnt, int value) {
		if (cnt == 3) {

			Ans = Math.max(Ans, value);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int ny = y + dirs[d][0];
			int nx = x + dirs[d][1];

			if (!boundary(ny, nx)) {
				continue;
			}

			if (visited[ny][nx]) {
				continue;
			}

			visited[ny][nx] = true;
			dfs(ny, nx, cnt + 1, value + map[ny][nx]);
			visited[ny][nx] = false;

		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
