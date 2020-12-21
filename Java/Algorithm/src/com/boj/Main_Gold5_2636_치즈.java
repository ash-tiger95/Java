package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_2636_치즈 {
	static int N, M, time, pre;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Queue<int[]> melt = new LinkedList<>();

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
		time = 0;
		pre = 0;

		while (true) {
			visited = new boolean[N][M];

			air();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && isEdge(i, j)) {
						bfs(i, j);
					}
				}
			}

			if (melt.isEmpty())
				break;

			pre = melt.size();

			while (!melt.isEmpty()) {
				int[] current = melt.poll();
				map[current[0]][current[1]] = -1;
			}
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(map[i][j] +" ");
//				}
//				System.out.println();
//			}

			time++;
		}

		System.out.println(time);
		System.out.println(pre);
	}

	private static void bfs(int y, int x) {
		Queue<int[]> que = new LinkedList<>();

		que.offer(new int[] { y, x });
		visited[y][x] = true;

		while (!que.isEmpty()) {
			int[] current = que.poll();
			int cy = current[0];
			int cx = current[1];

			if (isEdge(cy, cx)) {
				map[cy][cx] = 2;
				melt.offer(new int[] { cy, cx });
			}

			for (int i = 0; i < 4; i++) {
				int ny = cy + dirs[i][0];
				int nx = cx + dirs[i][1];
				
				if(!boundary(cy,cx)) {
					continue;
				}

				if (map[ny][nx] == 1 && !visited[ny][nx]) {
					que.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
				}
			}
		}
	}

	private static boolean isEdge(int y, int x) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dirs[d][0];
			int nx = x + dirs[d][1];

			if (map[ny][nx] == -1) {
				return true;
			}
		}
		return false;
	}

	private static void air() {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] air = new boolean[N][M];

		que.add(new int[] { 0, 0 });
		map[0][0] = -1;
		air[0][0] = true;

		while (!que.isEmpty()) {
			int[] current = que.poll();

			for (int d = 0; d < 4; d++) {
				int ny = current[0] + dirs[d][0];
				int nx = current[1] + dirs[d][1];

				if(!boundary(ny,nx)) {
					continue;
				}
				
				if (!air[ny][nx] && map[ny][nx] <= 0) {
					map[ny][nx] = -1;
					air[ny][nx] = true;
					que.offer(new int[] { ny, nx });
				}
			}
		}
	}

	private static boolean boundary(int y, int x) {
		return y >= 0 && y < N && x >= 0 && x < M;
	}
}
