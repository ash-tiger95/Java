package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_5427_불 {

	static class Point {
		int y, x, dist;
		boolean isFire;

		public Point(int y, int x, boolean isFire, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.isFire = isFire;
			this.dist = dist;
		}

	}

	static int T, N, M, Ans;
	static char[][] map;
	static Queue<Point> queue;
	static boolean[][] visited;
	static boolean[][] sanggeun;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 가로
			N = Integer.parseInt(st.nextToken()); // 세로

			map = new char[N][M];
			queue = new LinkedList<Point>();
			visited = new boolean[N][M];
			sanggeun = new boolean[N][M];
			Ans = 0;
			int startX = 0, startY = 0;

			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == '@') { // 상근이는 불을 퍼뜨리고 이동시켜야 하기 때문에
						startY = i;
						startX = j;
					} else if (map[i][j] == '*') { // 불을 모두 입력받고
						queue.offer(new Point(i, j, true, 0));
						visited[i][j] = true;
					} else if (map[i][j] == '#') {
						visited[i][j] = true;
					}
				}
			}
			
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(visited[i][j]+" ");
//				}
//				System.out.println();
//			}
			// 마지막에 상근이 위치를 입력한다.
			queue.offer(new Point(startY, startX, false, 1));
			sanggeun[startY][startX] = true;

			bfs();

			if (Ans == 0) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(Ans);
			}
		}

	}

	private static void bfs() {

		while (!queue.isEmpty()) {
			
			Point cp = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp.y + dirs[d][0];
				int nx = cp.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}
				if(visited[ny][nx]) {
					continue;
				}

				if (cp.isFire) {
					if ( (map[ny][nx] == '.' || map[ny][nx] == '@')) {
						queue.offer(new Point(ny, nx, true, 0));
						visited[ny][nx] = true;
					}
				} else {
					if (!sanggeun[ny][nx]&& (map[ny][nx] == '.')) {
						queue.offer(new Point(ny, nx, false, cp.dist + 1));
						sanggeun[ny][nx] = true;
						
						if (ny == 0 || nx == 0 || ny == N - 1 || nx == M - 1) {
							Ans = cp.dist + 1;
							return;
						}

					}

					
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < N && nx < M;
	}

}
