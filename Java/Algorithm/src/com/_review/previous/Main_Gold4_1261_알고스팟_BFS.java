package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_1261_알고스팟_BFS {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point implements Comparable<Point> {
		int y, x, wall;

		public Point(int y, int x, int wall) {
			super();
			this.y = y;
			this.x = x;
			this.wall = wall;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.wall,o.wall);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}


		System.out.println(bfs(0, 0));

	}

	private static int bfs(int sy, int sx) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(sy, sx, 0));
		visited[sy][sx] = true;

		while (!pq.isEmpty()) {
			Point cp = pq.poll();
			if (cp.y == N - 1 && cp.x == M - 1) {
				return cp.wall;
			}

			for (int d = 0; d < 4; d++) {
				int ny = cp.y + dirs[d][0];
				int nx = cp.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] == 0) {
					visited[ny][nx] = true;
					pq.offer(new Point(ny, nx, cp.wall));
				}

				if (!visited[ny][nx] && map[ny][nx] == 1) {
					visited[ny][nx] = true;
					pq.offer(new Point(ny, nx, cp.wall + 1));
				}
			}
		}
		return -1;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
