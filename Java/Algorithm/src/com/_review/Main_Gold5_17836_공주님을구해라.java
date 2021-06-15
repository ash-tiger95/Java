package com._review;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_17836_공주님을구해라 {

	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Node {
		int y, x, time;
		boolean hasSword;

		public Node(int y, int x, boolean hasSword, int time) {
			super();
			this.y = y;
			this.x = x;
			this.hasSword = hasSword;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken()); // 제한 시간, 1 ≤ T ≤ 10,000

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M][2];
		int min = bfs();
		
		System.out.println(min == -1 ? "Fail" : min);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		if (map[0][0] == 0) {
			q.offer(new Node(0, 0, false, 0));
		} else if (map[0][0] == 2) {
			q.offer(new Node(0, 0, true, 0));
		}

		while (!q.isEmpty()) {
			Node cn = q.poll();

			if (cn.y == N - 1 && cn.x == M - 1) {
				return cn.time;
			}
			
			if(cn.time >= T) { // T보다 크면 더이상 탐색할 필요 없다.
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (cn.hasSword) {
					if(!visited[ny][nx][1]) {
						q.offer(new Node(ny, nx, true, cn.time + 1));
						visited[ny][nx][1] = true;
					}
				} else {
					if(!visited[ny][nx][0]) {
						if (map[ny][nx] == 2) {
							q.offer(new Node(ny, nx, true, cn.time + 1));
							visited[ny][nx][0] = true;
						} else if (map[ny][nx] == 0) {
							q.offer(new Node(ny, nx, false, cn.time + 1));
							visited[ny][nx][0] = true;
						}
					}
				}
			}
		}

		return -1;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 & nx < M;
	}
}
