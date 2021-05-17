package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_2412_암벽등반 {
	static int N, T;
	static boolean[] visited;
	static int[][] map;
	static Queue<int[]> que = new LinkedList<>();
	static int Ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		Ans = Integer.MAX_VALUE;
		visited = new boolean[N];
		map = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken()); // x
			map[i][1] = Integer.parseInt(st.nextToken()); // y    
		}
		dfs(0,0,0);
		if(Ans == Integer.MAX_VALUE)
			Ans = -1;
		System.out.println(Ans);
	}

	private static void dfs(int sx, int sy, int cnt) {
		if (sy == T) {
			Ans = (Ans>cnt ? cnt : Ans);
			return;
		}
		if(cnt == N) {
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i] && Math.abs(map[i][0] - sx) <= 2 && Math.abs(map[i][1] - sy) <= 2) {
				visited[i] = true;
				dfs(map[i][0], map[i][1], cnt + 1);
				visited[i] = false;
				dfs(map[i][0], map[i][1], cnt + 1);
			}

		}
	}
}
