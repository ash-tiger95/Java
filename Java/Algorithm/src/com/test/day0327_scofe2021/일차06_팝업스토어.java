package com.test.day0327_scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 일차06_팝업스토어 {

	static int N, M, answer;
	static int[][] map;
	static int[][] dirs = { { 1, 0 }, { 0, 1 } };

	static class Node {
		int y, x, colCnt, rowCnt, value;

		public Node(int y, int x, int colCnt, int rowCnt, int value) {
			super();
			this.y = y;
			this.x = x;
			this.colCnt = colCnt;
			this.rowCnt = rowCnt;
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		bfs(0, 0);
		System.out.println(answer);
	}

	private static void bfs(int i, int j) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 0, 0, map[0][0]));

		while (!q.isEmpty()) {
			Node cn = q.poll();

			if (cn.y == N - 1 && cn.x == M - 1) {
				answer = Math.max(answer, cn.value);
				continue;
			}

			for (int d = 0; d < 2; d++) {

				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (d == 0 && cn.colCnt < N) { // 세로로 갈 수 있는 경우의 수
					q.offer(new Node(ny, nx, cn.colCnt + 1, cn.rowCnt, cn.value + map[ny][nx]));
				}

				if (d == 1 && cn.rowCnt < M) { // 가로로 갈 수 있는 경우의 수
					q.offer(new Node(ny, nx, cn.colCnt, cn.rowCnt + 1, cn.value + map[ny][nx]));
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
