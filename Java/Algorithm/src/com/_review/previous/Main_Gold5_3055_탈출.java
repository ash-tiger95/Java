package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_3055_탈출 {
	static int N, M, dy, dx;
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] goseumdochi; // 고슴도치
	static Queue<Point> q;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point {
		int y, x, depth;
		boolean isWater;

		public Point(int y, int x, int depth, boolean isWater) {
			super();
			this.y = y;
			this.x = x;
			this.depth = depth;
			this.isWater = isWater;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		goseumdochi = new boolean[N][M];
		q = new LinkedList<>();
		int sy = 0, sx = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'D') { // 굴(목적지)
					dy = i;
					dx = j;
				} else if (map[i][j] == '*') { // 물
					q.offer(new Point(i, j, 0, true));
					visited[i][j] = true;
				} else if (map[i][j] == 'S') { // 고슴도치
					sy = i;
					sx = j;
					goseumdochi[i][j] = true;
				}
			}
		}
		q.offer(new Point(sy, sx, 0, false));

		int Ans = bfs();
		System.out.println(Ans == -1 ? "KAKTUS" : Ans);
	}

	private static int bfs() {
		while (!q.isEmpty()) {
			Point cp = q.poll();
			if (!cp.isWater) {
				if (cp.y == dy && cp.x == dx) {
					return cp.depth;
				}
			}

			for (int d = 0; d < 4; d++) {
				int ny = cp.y + dirs[d][0];
				int nx = cp.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (cp.isWater) {
					if (!visited[ny][nx] && map[ny][nx] != 'X' && map[ny][nx] != 'D') {
						q.offer(new Point(ny, nx, cp.depth + 1, cp.isWater));
						map[ny][nx] = '*';
						visited[ny][nx] = true;
					}
				} else {
					if (!goseumdochi[ny][nx] && map[ny][nx] != 'X' && map[ny][nx] != '*') {
						q.offer(new Point(ny, nx, cp.depth + 1, cp.isWater));
						goseumdochi[ny][nx] = true;
					}
				}

			}
		}
		return -1;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
