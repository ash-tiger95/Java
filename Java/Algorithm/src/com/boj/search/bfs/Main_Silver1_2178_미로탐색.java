package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver1_2178_미로탐색 {

	static class Point {
		int y, x, cnt;

		public Point(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

	}

	static int N, M, Ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Ans = Integer.MAX_VALUE;

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		// BFS
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 1));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Point cp = queue.poll();

			if (cp.y == N - 1 && cp.x == M - 1) {
				Ans = Math.min(Ans, cp.cnt);
			}

			for (int d = 0; d < 4; d++) {
				int ny = cp.y + dirs[d][0];
				int nx = cp.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (map[ny][nx] != 0 && !visited[ny][nx]) {
					queue.offer(new Point(ny, nx, ++cp.cnt));
					visited[ny][nx] = true;
					map[ny][nx] += map[cp.y][cp.x];
				}
			}
		}
		System.out.println(map[N-1][M-1]);
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
