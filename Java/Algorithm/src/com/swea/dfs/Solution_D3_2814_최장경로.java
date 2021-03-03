package com.swea.dfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_2814_최장경로 {
	static int T, N, M, Ans;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\dfs\\2814.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			Ans = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 개수 (1번 ~ N번)
			M = Integer.parseInt(st.nextToken()); // 간선 개수

			map = new int[N][N]; // 0번 ~ N-1번
			visited = new boolean[N];

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				map[i][j] = map[j][i] = 1;
			}

			for (int i = 0; i < N; i++) {
				visited[i] = true;
				dfs(i, 1);
				visited[i] = false;
			}

			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int i, int cnt) {
		Ans = Math.max(Ans, cnt);

		for (int j = 0; j < N; j++) {
			if (!visited[j] && map[i][j] == 1) {
				visited[j] = true;
				dfs(j, cnt + 1);
				visited[j] = false;
			}
		}
	}
}
