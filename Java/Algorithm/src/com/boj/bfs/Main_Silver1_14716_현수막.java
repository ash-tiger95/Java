package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이) BFS 기본 문제
 * 
 * @author jugia
 *
 */
public class Main_Silver1_14716_현수막 {

	static int R, C, Ans;
	static int[][] map;
	static boolean[][] visited;
	static int dirs[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Ans = 0;
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 1 && !visited[i][j]) { // 방문하지 않고 1인 점 BFS
					visited[i][j] = true;
					bfs(i, j);
					Ans++;
				}
			}
		}

		System.out.println(Ans);
		
		br.close();
	}

	private static void bfs(int cy, int cx) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { cy, cx });

		while (!q.isEmpty()) {
			int[] cp = q.poll();

			for (int d = 0; d < 8; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] == 1) {
					visited[ny][nx] = true;
					q.add(new int[] { ny, nx });
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
