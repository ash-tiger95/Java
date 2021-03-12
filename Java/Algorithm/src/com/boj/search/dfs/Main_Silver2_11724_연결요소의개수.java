package com.boj.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver2_11724_연결요소의개수 {
	static int N, M;
	static int[][] adj;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점 개수
		M = Integer.parseInt(st.nextToken()); // 간선 개수
		adj = new int[N][N];
		visited = new boolean[N];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken())-1;
			int b= Integer.parseInt(st.nextToken())-1;
			
			adj[a][b] = adj[b][a] = 1;
		}
		
		int Ans = 0;
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				Ans ++;
				visited[i] = true;
				dfs(i);
			}
		}
		System.out.println(Ans);
	}
	private static void dfs(int n) {
		for(int i=0;i<N;i++) {
			if(adj[n][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
	}
}
