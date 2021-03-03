package com.swea.bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_7793_오나의여신님 {

	static class Point {
		int row, col, depth;
		boolean isDevil;

		public Point(int row, int col, int depth, boolean isDevil) {
			super();
			this.row = row;
			this.col = col;
			this.depth = depth;
			this.isDevil = isDevil;
		}
	}

	static int T, N, M, Ans;
	static char[][] map;
	static Queue<Point> queue = new LinkedList<>();
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\bfs\\7793.txt")));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Ans = 0;

			map = new char[N][M];
			Point player = null;
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '*') {
						queue.offer(new Point(i, j, 0, true));
					} else if (map[i][j] == 'S') {
						player = new Point(i, j, 0, false); // 악마를 먼저 큐에 넣고 수연이를 넣는다.
					}
				}
			}
			queue.offer(player);

			bfs();

			if (Ans == 0) {
				sb.append("GAME OVER").append('\n');
			} else {
				sb.append(Ans).append('\n');
			}
		}
		System.out.println(sb);
	}

	private static void bfs() {
		while (!queue.isEmpty()) {
			Point cp = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp.row + dirs[d][0];
				int nx = cp.col + dirs[d][1];

				if (boundary(ny, nx)) { // 경계선 검사
					if (cp.isDevil) { // 악마일때
						if (map[ny][nx] == '.' || map[ny][nx] == 'S') {
							map[ny][nx] = '*';
							queue.offer(new Point(ny, nx, cp.depth + 1, true));
						}
					} else { // 수연일때
						if (map[ny][nx] == '.') {
							map[ny][nx] = 'S';
							queue.offer(new Point(ny, nx, cp.depth + 1, false));
						} else if (map[ny][nx] == 'D') {
							Ans = cp.depth + 1;
							queue.clear();
							break;
						}
					}

				}
			}
		} // for while
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
