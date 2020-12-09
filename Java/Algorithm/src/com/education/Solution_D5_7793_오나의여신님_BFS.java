package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_7793_오나의여신님_BFS {
	static class Point {
		int y, x, time;
		boolean isDevil;

		public Point(int y, int x, int time, boolean isDevil) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
			this.isDevil = isDevil;
		}

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + ", time=" + time + ", isDevil=" + isDevil + "]";
		}
	}

	static int T, N, M, Ans;
	static char[][] map;
	static Queue<Point> queue;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Ans = Integer.MAX_VALUE;

			map = new char[N][M];
			queue = new LinkedList<Point>();
			Point subin = null;
			
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == '*') {
						queue.offer(new Point(i, j, 0, true));
					} else if (map[i][j] == 'S') {
						subin = new Point(i, j, 0, false);
					}
				}
			}
			queue.offer(subin); // 악마를 먼저 넣고 수빈을 마지막에 넣는다.


			outer: while (!queue.isEmpty()) {
				Point cp = queue.poll();
				
				if (cp.isDevil) { // 악마일 때,
					for (int d = 0; d < 4; d++) {
						int ny = cp.y + dirs[d][0];
						int nx = cp.x + dirs[d][1];

						if (!boundary(ny, nx)) {
							continue;
						}

						if (map[ny][nx] == '.' || map[ny][nx] == 'S') {
							map[ny][nx] = '*';
							queue.offer(new Point(ny, nx, cp.time + 1, true));
						}

					}
				} else { // 수빈일 때,
					for (int d = 0; d < 4; d++) {
						int ny = cp.y + dirs[d][0];
						int nx = cp.x + dirs[d][1];

						if (!boundary(ny, nx)) {
							continue;
						}

						if (map[ny][nx] == 'D') {
							Ans = cp.time+1;
							break outer;
						}else if (map[ny][nx] == '.') {
							map[ny][nx] = 'S';
							queue.offer(new Point(ny, nx, cp.time + 1, false));
						}

					}
				}
			}
			System.out.println("#"+t+" "+ (Ans == Integer.MAX_VALUE ? "GAME OVER" : Ans));
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < N && nx < M;
	}

}
