package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Test_1949_등산로조성_DFS {
	static int T, N, K, Ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 한 변의 길이, 3 <= N <= 8
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이, 1 <= K <=5
			Ans = 0;

			map = new int[N][N];

			int maxValue = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (maxValue < map[i][j]) {
						maxValue = map[i][j]; // 가장 큰 봉우리 찾기
					}
				}
			}

			visited = new boolean[N][N]; // 방문 처리
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxValue) { // 가장 큰 봉우리에서 시작
						visited[i][j] = true;
						dfs(i, j, map[i][j], 1, false);
						visited[i][j] = false;
					}
				}
			}

			System.out.println("#" + t + " " + Ans);
		}

	}

	private static void dfs(int i, int j, int pow, int cnt, boolean cut) {
		Ans = Math.max(Ans, cnt); // 가장 큰 값 찾기

		for (int d = 0; d < 4; d++) {
			int ny = i + dirs[d][0];
			int nx = j + dirs[d][1];
			if (!boundary(ny, nx)) {
				continue;
			}

			if (!visited[ny][nx] && map[ny][nx] < pow) {
				visited[ny][nx] = true;
				dfs(ny, nx, map[ny][nx], cnt + 1, cut);
				visited[ny][nx] = false;
			}
			if (!cut) { // 깎은 적 없을 때,
				if (!visited[ny][nx] && map[ny][nx] - K < pow) {
					for (int k = 1; k <= K; k++) { // 1부터 K까지 다 깎아본다.
						if (map[ny][nx] - k < pow) {
							map[ny][nx] -= k; // 깎고
							visited[ny][nx] = true;
							dfs(ny, nx, map[ny][nx], cnt + 1, true);
							map[ny][nx] += k; // 원상복귀
							visited[ny][nx] = false;
						}
					}
				}
			}

		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < N && nx < N;
	}
}
