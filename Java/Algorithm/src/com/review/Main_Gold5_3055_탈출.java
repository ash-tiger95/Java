package com.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_3055_탈출 {

	static int R, C, sy, sx, ey, ex;
	static char[][] map;
	static Queue<Node> q;
	static boolean[][] visited;
	static boolean[][] go;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Node {
		int y, x, count;
		boolean isWater;

		public Node(int y, int x, boolean isWater, int count) {
			super();
			this.y = y;
			this.x = x;
			this.isWater = isWater;
			this.count = count;
		};
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		q = new LinkedList<>();

		map = new char[R][C];
		visited = new boolean[R][C];
		go = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);

				if (map[i][j] == 'D') {
					ey = i;
					ex = j;
				} else if (map[i][j] == 'S') {
					sy = i;
					sx = j;
					go[i][j] = true;
				} else if (map[i][j] == '*') {
					q.offer(new Node(i, j, true, 0));
					visited[i][j] = true;
				}
			}
		}

		q.offer(new Node(sy, sx, false, 0));

		int ans = bfs();
		System.out.println(ans == -1 ? "KAKTUS" : ans);

	}

	private static int bfs() {
		visited = new boolean[R][C];

		while (!q.isEmpty()) {
			Node cn = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (cn.isWater) {

					if (!visited[ny][nx] && map[ny][nx] != 'D' && map[ny][nx] != 'X') {
						map[ny][nx] = '*';
						visited[ny][nx] = true;
						q.offer(new Node(ny, nx, true, 0));
					}
				} else {

					if (map[ny][nx] == 'D') {
						return cn.count + 1;
					}

					if (!go[ny][nx] && map[ny][nx] == '.') {
						go[ny][nx] = true;
						q.offer(new Node(ny, nx, false, cn.count + 1));
					}
				}
			}

		}

		return -1;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
