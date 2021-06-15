package com.swea.sw역량테스트;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Test_1949_등산로조성 {

	static int T, N, K, max, Ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\1949.txt")));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // map 길이, 3 <= N <= 8
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이, 1 <= K <= 5

			map = new int[N][N];
			max = 0; // 등산로는 가장 높은 봉우리에서 시작
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]); // 가장 높은 높이 저장
				}
			}

			Ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						visited = new boolean[N][N];
						visited[i][j] = true;

						dfs(i, j, map[i][j], 1, false);
					}
				}
			}

			sb.append(Ans).append("\n");
		} // for T

		System.out.println(sb);
		br.close();
	}

	private static void dfs(int sy, int sx, int sh, int len, boolean hasCut) {

		Ans = Math.max(Ans, len);

		for (int d = 0; d < 4; d++) {
			int ny = sy + dirs[d][0];
			int nx = sx + dirs[d][1];

			if (!boundary(ny, nx)) {
				continue;
			}

			if (visited[ny][nx]) {
				continue;
			}

			if (!hasCut) { // 자른적이 없을 때
				if (map[ny][nx] < sh) {
					visited[ny][nx] = true;
					dfs(ny, nx, map[ny][nx], len + 1, false);
					visited[ny][nx] = false;

				} else {
					for (int k = 1; k <= K; k++) {
						if (map[ny][nx] - k < sh) {
							visited[ny][nx] = true;

							// ★ 중요!
//							map[ny][nx] -= k; // 방법1. map의 높이에 변화를 준다.
							dfs(ny, nx, map[ny][nx] - k, len + 1, true); // 방법2. 매개변수를 넘길 때 높이에 변화를 준다.
//							map[ny][nx] += k; // 원상복구
							
							visited[ny][nx] = false;
						}
					}
				}
			} else { // 자른적이 있을 때
				if (map[ny][nx] < sh) {
					visited[ny][nx] = true;
					dfs(ny, nx, map[ny][nx], len + 1, true);
					visited[ny][nx] = false;
				}
			}
		}

		return;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N & nx >= 0 && nx < N;
	}
}
