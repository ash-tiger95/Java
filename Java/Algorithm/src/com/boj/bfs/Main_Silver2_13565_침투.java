package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver2_13565_침투 {

	static int R, C;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String in = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = in.charAt(j) - '0'; // 0: 전류 가능, 1: 차단
			}
		}

		visited = new boolean[R][C];
		for (int i = 0; i < C; i++) {
			if (map[0][i] == 0 && !visited[0][i]) {
				if (bfs(0, i)) {
					System.out.println("YES"); // 더이상 탐색할 필요가 없다.
					return;
				}
			}
		}

		System.out.println("NO");
		
		br.close();
	}

	private static boolean bfs(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sy, sx });
		visited[sy][sx] = true;

		while (!q.isEmpty()) {
			int[] cp = q.poll();

			if (cp[0] == R - 1) { // inner side에 접근 가능하면 더이상 탐색할 필요 없다.

				return true;
			}

			for (int d = 0; d < 4; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (map[ny][nx] == 0 && !visited[ny][nx]) {
					q.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
				}
			}
		}

		return false;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
