package com.test.scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 일차05_시선이동 { // 실패

	static int N, M, answer;
	static char[][] map;
	static boolean[] start;

	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 하, 우, 좌

	static class Node {
		int y, x, count; // count: 가로로 간 횟수

		public Node(int y, int x, int count) {
			super();
			this.y = y;
			this.x = x;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 가로, 3 <= M <= 20
		N = Integer.parseInt(st.nextToken()); // 세로, 5 <= N <= 1,000

		map = new char[N][M];
		start = new boolean[M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'c') {
					start[j] = true; // 시작지점 저장
				}
			}
		}

		answer = Integer.MAX_VALUE; // 좌우로 가장 적에 이동한 경로 == '기준 경로'
		for (int i = 0; i < M; i++) {
			if (!start[i]) {
				continue;
			}

			// 시작
			int temp = bfs(i);
			if (temp == Integer.MAX_VALUE) {
				answer = -1; // 도착지점에 갈 수 없는 경우 -1
				break;
			} else {
				answer = Math.min(temp, answer); // 가로로 이동한 경우 최솟값 업데이트
			}
		}
		
		System.out.println(answer);

	}

	private static int bfs(int i) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(new Node(0, i, 0));
		visited[0][i] = true;

		int temp = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			Node cn = q.poll();

			for (int d = 0; d < 3; d++) {
				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] == '.') {
					if (ny == N - 1) {
						if (d == 0) {
							temp = Math.min(temp, cn.count);
						} else {
							temp = Math.min(temp, cn.count + 1);
						}

					}
					visited[ny][nx] = true;
					q.offer(new Node(ny, nx, d != 0 ? cn.count + 1 : cn.count));
				}
			}
		}

		return temp;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
