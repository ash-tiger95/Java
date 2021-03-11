package com.boj.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_2206_벽부수고이동하기 {
	static int N, M, Ans;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point {
		int y, x, dist;
		boolean isBroken;
		public Point(int y, int x, int dist, boolean isBroken) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
			this.isBroken = isBroken;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		visited = new boolean[N][M][2]; // 0: 벽 안 부심, 1: 벽 부심

		Ans = 0;
		bfs();
		System.out.println(Ans == 0 ? -1 : Ans);

	}

	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, 1,false)); // 시작하는 칸도 포함
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Point cp = q.poll();
			
			if(cp.y == N-1 && cp.x == M-1) {
				Ans = cp.dist;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int ny = cp.y + dirs[d][0];
				int nx = cp.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}
				
				if(cp.isBroken) { // 벽을 부순 경우
					if(map[ny][nx] == 0 && !visited[ny][nx][1]) {
						q.offer(new Point(ny,nx,cp.dist+1,true));
						visited[ny][nx][1] = true;
					}
					
					
				} else { // 벽을 부수지 않은 경우
					
					if(map[ny][nx] == 0 && !visited[ny][nx][0]) {
						q.offer(new Point(ny,nx,cp.dist+1,false));
						visited[ny][nx][0] = true;
					}
					
					if(map[ny][nx] == 1 && !visited[ny][nx][1]) {
						q.offer(new Point(ny,nx,cp.dist+1,true));
						visited[ny][nx][1] = true;
					}
					
				}
				
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
