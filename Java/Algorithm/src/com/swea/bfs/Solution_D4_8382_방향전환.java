package com.swea.bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_8382_방향전환 {
	
	static class Point {
		int row, col, depth;
		boolean dir;

		public Point(int row, int col, int depth, boolean dir) {
			super();
			this.row = row;
			this.col = col;
			this.depth = depth;
			this.dir = dir;
		}
	}

	static int T;
	static int sy, sx, ey, ex;
	static boolean[][][] visited;
	static Queue<Point> queue = new LinkedList<>();
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\com\\swea\\bfs\\swea8382.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken()) + 100;
			sx = Integer.parseInt(st.nextToken()) + 100;
			ey = Integer.parseInt(st.nextToken()) + 100;
			ex = Integer.parseInt(st.nextToken()) + 100;

			visited = new boolean[201][201][2]; // 가로인 경우, 세로인 경우
			queue.clear();
			queue.offer(new Point(sy, sx, 0, true)); // 다음 이동상태 == 가로
			queue.offer(new Point(sy, sx, 0, false)); // 다음 이동상태 == 세로
			visited[sy][sx][0] = true;
			visited[sy][sx][1] = true;

			while (!queue.isEmpty()) {
				Point cp = queue.poll();
				if (cp.row == ey && cp.col == ex) {
					sb.append(cp.depth).append('\n');
					break;
				}

				if (cp.dir) { // 가로일 때
					for (int d = 0; d < 2; d++) {
						// 세로로 이동
						int ny = cp.row + dirs[d][0];
						int nx = cp.col + dirs[d][1];

						if (boundary(ny, nx) && !visited[ny][nx][0]) { // 가로이동해서 온 적이 없을 경우
							queue.offer(new Point(ny, nx, cp.depth + 1, !cp.dir));
							visited[ny][nx][0] = true;
						}
					}
				} else { // 세로일 때
					for (int d = 2; d < 4; d++) {
						// 가로로 이동
						int ny = cp.row + dirs[d][0];
						int nx = cp.col + dirs[d][1];

						if (boundary(ny, nx) && !visited[ny][nx][1]) { // 세로이동해서 온 적이 없을 경우
							queue.offer(new Point(ny, nx, cp.depth + 1, !cp.dir));
							visited[ny][nx][1] = true;
						}
					}
				}

			} // for bfs
		} // for T
		System.out.println(sb);
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < 201 && nx >= 0 && nx < 201;
	}
}
