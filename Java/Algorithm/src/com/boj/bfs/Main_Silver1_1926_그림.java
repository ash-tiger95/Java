package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이) 간단한 BFS
 * 
 * @author jugia
 *
 */
public class Main_Silver1_1926_그림 {

	static int N, M, count, max;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		count = max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}

		System.out.println(count);
		System.out.println(max);
		
		br.close();
	}

	private static void bfs(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sy, sx });
		visited[sy][sx] = true;
		int area = 1; // 각 그림의 넓이

		while (!q.isEmpty()) {
			int[] cp = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] == 1) {
					q.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
					area++;
				}
			}
		}

		count++; // 그림 총 개수
		max = area > max ? area : max; // 가장 큰 넓이로 업데이트
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
