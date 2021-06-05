package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver1_7576_토마토 {

	static int R, C, max, total;
	static int[][] map;
	static Queue<int[]> q;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		total = R *C; // 최초 토마토 상태 체크하기 위함
		
		map = new int[R][C];
		q = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					q.offer(new int[] { i, j });
					total--;
				} else if(map[i][j] == -1) {
					total--;
				}
			}
		}

		max = 0;
		if(total == 0) { // 최초 토마토가 모두 익은 상태일 때
			max = 1;
		} else {
			bfs();
		}
		
		System.out.println(check() ? max - 1 : -1);
	}

	private static boolean check() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int[] cp = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (map[ny][nx] == 0) {
					q.offer(new int[] { ny, nx });
					map[ny][nx] = map[cp[0]][cp[1]] + 1;
					
					if (max < map[ny][nx]) {
						max = map[ny][nx];
					}
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
