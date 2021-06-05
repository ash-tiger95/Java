package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_9663_NQueen {

	static int N, Ans;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][N];
		
		Ans = 0;
	}
	
	private static void dfs(int cnt,int y, int x) {
		if(cnt == N) {
			Ans++;
			return;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					// 가로 검사
					for(int k=0;k<N;k++) {
						if(visited[i][k]) {
							return;
						}
					}
					
					// 세로 검사
					
					// 대각선 검사
					
				}
			}
		}
	}
}
