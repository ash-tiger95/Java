package com.boj.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver2_7562_나이트의이동 {

	static int T, N, sy, sx, ey, ex;
	static boolean[][] visited;
	static Queue<int[]> q;
	static int[][] dirs = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());

			System.out.println(bfs());
		}

		bw.write(sb.toString()); // 출력
		bw.flush(); // 남아있는 데이터를 모두 출력시킴

		br.close();
		bw.close();
	}

	private static int bfs() {
		q = new LinkedList<>();
		visited = new boolean[N][N];

		q.offer(new int[] { sy, sx, 0 });
		visited[sy][sx] = true;

		while (!q.isEmpty()) {
			int[] cn = q.poll();

			if (cn[0] == ey && cn[1] == ex) {
				return cn[2];
			}

			for (int d = 0; d < 8; d++) {
				int ny = cn[0] + dirs[d][0];
				int nx = cn[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx]) {
					visited[ny][nx] = true;
					q.offer(new int[] { ny, nx, cn[2] + 1 });
				}
			}
		}

		return -1;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
