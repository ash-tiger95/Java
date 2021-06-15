package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 간단한 BFS
 * @author jugia
 *
 */
public class Main_Silver2_3187_양치기꿍 {

	static int R, C, wolf, sheep;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String in = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = in.charAt(j);
			}
		}

		visited = new boolean[R][C];
		wolf = sheep = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != '#' && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}

		System.out.println(sheep + " " + wolf);
		
		br.close();
	}

	private static void bfs(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sy, sx });
		visited[sy][sx] = true;

		int sheepCnt = 0;
		int wolfCnt = 0;

		if (map[sy][sx] == 'v') {
			wolfCnt++;
		} else if (map[sy][sx] == 'k') {
			sheepCnt++;
		}

		while (!q.isEmpty()) {
			int[] cp = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (map[ny][nx] != '#' && !visited[ny][nx]) {
					q.offer(new int[] { ny, nx });
					visited[ny][nx] = true;

					if (map[ny][nx] == 'v') {
						wolfCnt++;
					} else if (map[ny][nx] == 'k') {
						sheepCnt++;
					}
				}
			}
		}

		if (wolfCnt >= sheepCnt) {
			wolf += wolfCnt;
		} else {
			sheep += sheepCnt;
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
