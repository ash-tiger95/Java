package com.boj.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_1261_알고스팟 {
	static int N, M;
	static int[][] map;
	static int[][] dist;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point implements Comparable<Point> {
		int y, x, cost;

		public Point(int y, int x, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + ", cost=" + cost + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		System.out.println(bfs(0, 0));

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static int bfs(int sy, int sx) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(sy, sx, 0));
		dist[sy][sx] = 0;

		while (!pq.isEmpty()) {
			Point cp = pq.poll();
			System.out.println(cp.toString());
			if (cp.y == N - 1 && cp.x == M - 1) {
				return cp.cost;
			}

			for (int d = 0; d < 4; d++) {
				int ny = cp.y + dirs[d][0];
				int nx = cp.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (dist[ny][nx] > dist[cp.y][cp.x] + map[ny][nx]) {
					dist[ny][nx] = dist[cp.y][cp.x] + map[ny][nx];
					pq.add(new Point(ny, nx, dist[ny][nx]));
				}
			}
		}
		return -1;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
