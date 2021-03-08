package com.swea.dfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_D5_4534_트리흑백색칠 {
	static final int MOD = 1000000007;
	static int T, N;
	static long Ans;
	static List[] adj;
	static long[][] memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\dfs\\4534.txt")));
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			Ans = 0;
			N = Integer.parseInt(br.readLine());
			
			adj = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				adj[i] = new ArrayList<>();
			}

			memo = new long[2][N]; // 0: 흑, 1: 백

			for (int i = 0; i < N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;

				adj[a].add(b);
				adj[b].add(a);
			}
			
			Ans = (dfs(0,0,-1) + dfs(0,1,-1))%MOD;

			sb.append(Ans).append("\n");
		}
		System.out.println(sb);

	}

	private static long dfs(int v, int color, int parent) {
		if (memo[color][v] != 0) {
			return memo[color][v];
		}

		long ret = 1;
		if (color == 0) { // color가 흑(0)인 경우
			for (int i = 0; i < adj[v].size(); i++) {
				if ((int) adj[v].get(i) != parent) {
					ret *= dfs((int) adj[v].get(i), 1, v); // 흑-흑 제외
					ret %= MOD;
				}
			}
		} else {
			for (int i = 0; i < adj[v].size(); i++) {
				if ((int) adj[v].get(i) != parent) {
					ret *= (dfs((int) adj[v].get(i), 1, v) + dfs((int) adj[v].get(i), 0, v));
					ret %= MOD;
				}
			}
		}

		memo[color][v] = ret;
		return ret;
	}
}
