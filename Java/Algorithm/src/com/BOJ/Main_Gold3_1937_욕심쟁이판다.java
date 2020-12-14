package com.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold3_1937_욕심쟁이판다 {
	static int N, Ans;
	static int[][] map;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int[][] memoi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		memoi = new int[N][N];
		Ans = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Ans = Math.max(Ans, dfs(i,j,memoi));
			}
		}
		System.out.println(Ans);
	}

	private static int dfs(int sy, int sx, int[][] memoi) {
		if(memoi[sy][sx] != 0) {
			return memoi[sy][sx];
		}
		memoi[sy][sx] = 1;
		for(int d=0;d<dirs.length;d++) {
			int ny = sy + dirs[d][0];
			int nx = sx + dirs[d][1];
			if(!boundary(ny,nx)) {
				continue;
			}
			if(map[sy][sx] < map[ny][nx]) {
				
				memoi[sy][sx] = Math.max(memoi[sy][sx], dfs(ny,nx,memoi)+1);
			}
		}
		return memoi[sy][sx];
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}	
