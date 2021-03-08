package com.swea.bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_1907_모래성쌓기 {
	static int T, N, M, Ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\1907.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로
			M = Integer.parseInt(st.nextToken()); // 가로
			Ans = 0;

			map = new int[N][M];
			visited = new boolean[N][M];
			Queue<int[]> sea = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					if (input.charAt(j) == '.') {
						map[i][j] = 0;
						sea.offer(new int[] { i, j });
					} else {
						map[i][j] = input.charAt(j) - '0';
					}
				}
			}

			// 모래성 중심이 아니라 바다 중심으로 모래성을 깎는다.
			while (!sea.isEmpty()) {
				int cnt = sea.size();
				while (cnt > 0) {
					int[] cp = sea.poll();

					for (int d = 0; d < 8; d++) {
						int ny = cp[0] + dirs[d][0];
						int nx = cp[1] + dirs[d][1];

						if (boundary(ny, nx)) {
							map[ny][nx]--;
							if (map[ny][nx] == 0) {
								sea.offer(new int[] { ny, nx });
							}
						}
					}

					cnt--;
				}
				Ans++;
			}

			System.out.println("#" + t + " " + (Ans - 1));

		} // for T
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
