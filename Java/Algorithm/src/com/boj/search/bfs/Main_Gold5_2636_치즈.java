package com.boj.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_2636_치즈 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

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

		// 치즈 안의 공간을 제외한 바깥 공기를 방문처리한다.
		visited = new boolean[N][M];
		queue = new LinkedList<>();
		visited[0][0] = true;
		queue.offer(new int[] { 0, 0 });
		air();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}

		while (true) { // 다 녹을 때까지
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && isEdge(i, j)) {
						
					}
				}
			}
		}

	}

	private static boolean isEdge(int i, int j) {
		if (visited[i - 1][j] == true || visited[i + 1][j] == true || visited[i][j - 1] == true
				|| visited[i][j + 1] == true) {
			return true;
		} else {
			return false;
		}
	}

	private static void air() {
		while (!queue.isEmpty()) {
			int[] cp = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] == 0) {
					visited[ny][nx] = true;
					queue.offer(new int[] { ny, nx });
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
