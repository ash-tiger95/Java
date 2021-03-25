package com.swea.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Test_1949_등산로조성 {

	static int T, N, K, Ans, high;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Node> qHigh;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static StringBuilder sb = new StringBuilder();

	static class Node {
		int y, x, depth, height;
		boolean isCut;

		public Node(int y, int x, int depth, int height, boolean isCut) {
			super();
			this.y = y;
			this.x = x;
			this.depth = depth;
			this.height = height;
			this.isCut = isCut;
		}

		@Override
		public String toString() {
			return "[" + y + ", " + x + ", " + depth + ", " + height + ", " + isCut + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\1949.txt")));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			Ans = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			qHigh = new LinkedList<>();
			high = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (high < map[i][j]) { // 새로운 high가 정해지면 q 초기화
						qHigh.clear();
						high = map[i][j];
						qHigh.offer(new Node(i, j, 1, map[i][j], false));
					} else if (high == map[i][j]) {
						qHigh.offer(new Node(i, j, 1, map[i][j], false));
					}
				}
			}


			visited = new boolean[N][N];
			
			while (!qHigh.isEmpty()) {
				Node cn = qHigh.poll();
				visited[cn.y][cn.x] = true;
				dfs(cn.y, cn.x, cn.depth, cn.height, cn.isCut);
				visited[cn.y][cn.x] = false;
			}

			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int sy, int sx, int depth, int height, boolean isCut) {
		Ans = Math.max(Ans, depth);

		for (int d = 0; d < 4; d++) {
			int ny = sy + dirs[d][0];
			int nx = sx + dirs[d][1];

			if (!boundary(ny, nx)) {
				continue;
			}
			
			if(visited[ny][nx]) {
				continue;
			}

			if (isCut) { // 짜른 적이 있으면
				if (map[ny][nx] < height) {
					visited[ny][nx] = true;
					dfs(ny, nx, depth + 1, map[ny][nx], isCut);
					visited[ny][nx] = false;
				}
			} else {

				if (map[ny][nx] < height) {
					visited[ny][nx] = true;
					dfs(ny, nx, depth + 1, map[ny][nx], isCut);
					visited[ny][nx] = false;
				}

				for (int k = 1; k <= K; k++) {
					if (map[ny][nx] - k < height){
						map[ny][nx] -= k; // 얘가 포인트!!!!!!!
						visited[ny][nx] = true;
						dfs(ny, nx, depth + 1, map[ny][nx], true);
						map[ny][nx] += k; // 원상복구
						visited[ny][nx] = false;
					}
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
