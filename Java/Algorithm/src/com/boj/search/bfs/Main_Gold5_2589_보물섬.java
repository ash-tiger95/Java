package com.boj.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_2589_보물섬 {
	static int N, M, Ans;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Queue<Point> q = new LinkedList<>();

	static class Point {
		int y, x, depth;

		public Point(int y, int x, int depth) {
			super();
			this.y = y;
			this.x = x;
			this.depth = depth;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Ans = Integer.MIN_VALUE;
		map = new char[N][M];

		for (int n = 0; n < N; n++) {
			map[n] = br.readLine().toCharArray();
		}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					q.offer(new Point(i, j, 0));
					visited = new boolean[N][M];
					visited[i][j] = true;
					bfs();
				}
			}
		}
		System.out.println(Ans);

	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Point cp = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp.y + dirs[d][0];
				int nx = cp.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] == 'L') {
					q.offer(new Point(ny, nx, cp.depth + 1));
					visited[ny][nx] = true;
				}
			}

			if (q.isEmpty()) {
				Ans = Ans > cp.depth ? Ans : cp.depth;
			}

		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
