package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_2814_최장경로_DFS {

	static int T, N, M, Ans;
	static boolean[][] adj;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 개수
			M = Integer.parseInt(st.nextToken()); // 간선 개수
			Ans = 0;

			adj = new boolean[N][N]; // 인접 행렬
			visited = new boolean[N];

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				adj[a][b] = adj[b][a] = true;
			}

			for (int i = 0; i < N; i++) { // 모든 정점에 대해 DFS
				visited[i] = true;
				dfs(i, 1);
				visited[i] = false;

			}
			System.out.println("#" + t + " " + Ans);

		} // for T
	}

	private static void dfs(int start, int cnt) {
		Ans = Math.max(Ans, cnt);

		for (int i = 0; i < N; i++) {
			if (adj[start][i] && !visited[i]) {
				visited[i] = true;
				dfs(i, cnt + 1);
				visited[i] = false;
			}
		}

	}

}
