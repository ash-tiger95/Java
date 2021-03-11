package com.boj.dp.memoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold3_1937_욕심쟁이판다 {
	static int N, Ans;
	static int[][] map;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static int[][] memoi;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Ans = Integer.MIN_VALUE;
		map = new int[N][N];
		memoi = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Ans = Math.max(Ans, dfs(i, j, map[i][j], memoi));
				System.out.println("---");
				print(memoi);
				System.out.println(Ans+"*************");
			}
		}
		
//		print();
		System.out.println(Ans);

	}

	private static int dfs(int sy, int sx, int tree, int[][] memoi) {
		if (memoi[sy][sx] != 0) {
			return memoi[sy][sx];
		}
		
		System.out.println("----"+sy+" "+sx);
		
		memoi[sy][sx] = 1;
		print(memoi);

		for (int d = 0; d < 4; d++) {
			int ny = sy + dirs[d][0];
			int nx = sx + dirs[d][1];

			if (!boundary(ny, nx)) {
				continue;
			}

			if (map[ny][nx] <= tree) { // 나무가 적은 경우
				continue;
			} else { // 나무가 많은 경우

				memoi[sy][sx] = Math.max(memoi[sy][sx], dfs(ny, nx, map[ny][nx], memoi)+1);

			}

		}
		return memoi[sy][sx];
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

	private static void print(int[][] memoi) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(memoi[i][j] + " ");
			}
			System.out.println();
		}
	}

}
