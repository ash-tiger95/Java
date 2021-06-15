package com.boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver3_20152_GameAddiction {

	static int H, N;
	static long[][] map; // ★ 조심! 피보나치 9,000번 -> long
	static int[][] dirs = { { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		// H를 (0,0), N을 (N,N)으로 통일하자. -> 사실 필요없는 작업
		// 단순히 큰걸 H, 작은걸 N으로만 하면 된다.
		if (a < b) {
			if (a != 0) {
				b = b - a;
				a = 0;
			}
			H = a;
			N = b + 1;
		} else if (a > b) {
			if (b != 0) {
				a = a - b;
				b = 0;
			}
			H = b;
			N = a + 1;
		}

		if (a == b) {
			sb.append(1);
		} else {
			// 풀이1. BFS 시간초과
			map = new long[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i > j) {
						map[i][j] = -1; // y>x는 침수
					}
				}
			}

			bfs();
			sb.append(map[N - 1][N - 1]);

			// 풀이2. DP
			map = new long[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i > j) { // y > x는 침수
						continue;
					}

					if (i == 0) {
						map[i][j] = 1;
					} else {
						map[i][j] = map[i - 1][j] + map[i][j - 1];
					}
				}
			}

			sb.append(map[N - 1][N - 1]);
		}

		bw.write(sb.toString()); // 출력
		bw.flush(); // 남아있는 데이터를 모두 출력시킴

		br.close();
		bw.close();
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0 });

		while (!q.isEmpty()) {
			int[] cn = q.poll();
			map[cn[0]][cn[1]]++;

			if (cn[0] == N - 1 && cn[1] == N - 1) {
				continue;
			}

			for (int d = 0; d < 2; d++) {
				int ny = cn[0] + dirs[d][0];
				int nx = cn[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (map[ny][nx] != -1) {
					q.offer(new int[] { ny, nx });
				}
			}
		}

	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
