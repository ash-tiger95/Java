package com.boj.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_2573_빙산 {
	static int N, M,Ans;
	static int[][] map;

	static int dirs[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Ans = 0;
		while (!check()) {
			melt();
			Ans++;
			
		}
		System.out.println(Ans);
	}

	private static void melt() {
		int[][] temp = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				int ice = 0;
				if (map[i][j] != 0) {
					for (int d = 0; d < 4; d++) {
						int ny = i + dirs[d][0];
						int nx = j + dirs[d][1];

						if (!boundary(ny, nx)) {
							continue;
						}

						if(map[ny][nx] == 0) {
							ice++;
						}
					}
					
					
					if(map[i][j] - ice < 0) {
						temp[i][j] = 0;
					} else {
						temp[i][j] = map[i][j] - ice;
					}
				} else {
					temp[i][j] = 0;
				}
				
				
				
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			map[i] = temp[i].clone();
		}
	}

	private static boolean check() {
		visited = new boolean[N][M];
		int temp = 0; // 빙산 덩어리 카운트

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					bfs(i, j);
					temp++;
				}
			}
		}
		
		if(temp == 0) { // 덩어리가 하나도 없을 경우!
			Ans = 0;
			return true;
		}

		if (temp >= 2) {
			return true;
		} else {
			return false;
		}
	}

	private static void bfs(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sy, sx });
		visited[sy][sx] = true;

		while (!q.isEmpty()) {
			int[] cp = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] != 0) {
					q.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
