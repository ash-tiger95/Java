package com._review;

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
public class Main_Silver2_3184_양 {

	static int R, C, sheep, wolf, sheepAns, wolfAns;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sheepAns = wolfAns = 0; // 다음날 살아있을 양과 늑대 마리 수

		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {

				if (map[i][j] != '#' && !visited[i][j]) {
					sheep = wolf = 0; // 한 구역에 대한 양과 늑대 임시 카운트
					visited[i][j] = true;
					if (map[i][j] == 'o') {
						sheep++;
					} else if (map[i][j] == 'v') {
						wolf++;
					}

					bfs(i, j);
				}

			}
		}

		System.out.println(sheepAns + " " + wolfAns);
	}

	private static void bfs(int sy, int sx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sy, sx });

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

					if (map[ny][nx] == 'o') { // 마리 수 증가
						sheep++;
					} else if (map[ny][nx] == 'v') {
						wolf++;
					}
				}
			}
		}

		if (sheep > wolf) {
			sheepAns += sheep;
		} else {
			wolfAns += wolf;
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
