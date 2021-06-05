package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이) 3차원 BFS
 * 
 * @author jugia
 *
 */
public class Main_Silver1_7569_토마토 {

	static int R, C, H, total, Ans;
	static int[][][] box;
	static boolean[][][] visited;
	static Queue<int[]> q;

	static int[][] dirs = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 1, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Ans = 0;
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		total = H * R * C;

		box = new int[H][R][C];
		visited = new boolean[H][R][C];
		q = new LinkedList<>();

		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					box[h][r][c] = Integer.parseInt(st.nextToken());

					if (box[h][r][c] == 1) { // 1: 익은 토마토, 0: 익지 않은 토마토
						q.add(new int[] { h, r, c });
						visited[h][r][c] = true;
						total--;
						
					} else if (box[h][r][c] == -1) { // 토마토가 없는 칸
						total--;
					}
				}
			}
		}

		if (total == 0) { // 저장될 때부터 모든 토마토가 익은 상태
			System.out.println(0);
		} else {
			while (true) {
				bfs();
				Ans++;

				if (total == 0) { // 토마토가 모두 익은 상황
					System.out.println(Ans);
					break;
				}
				
				if(total != 0 && q.isEmpty()) { // 토마토가 모두 익지 못하는 상황
					System.out.println(-1);
					break;
				}
			}

		}
	}

	private static void bfs() {
		int size = q.size();

		for (int i = 0; i < size; i++) {
			int[] cp = q.poll();

			for (int d = 0; d < 6; d++) {
				int nz = cp[0] + dirs[d][0];
				int ny = cp[1] + dirs[d][1];
				int nx = cp[2] + dirs[d][2];

				if (!boundary(nz, ny, nx)) {
					continue;
				}

				if (!visited[nz][ny][nx] && box[nz][ny][nx] == 0) {
					q.add(new int[] { nz, ny, nx });
					visited[nz][ny][nx] = true;
					total--;
				}
			}
		}
	}

	private static boolean boundary(int nz, int ny, int nx) {
		return nz >= 0 && nz < H && ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
