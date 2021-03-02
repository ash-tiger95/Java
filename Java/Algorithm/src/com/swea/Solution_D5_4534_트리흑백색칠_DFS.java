package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_D5_4534_트리흑백색칠_DFS {
	static final int MOD = 1000000007;
	static int T, N;
	static List[] adj;
	static long[][] memo; // 색상, 정점 번호

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 개수, 2 <= N <= 100,000
			
			adj = new ArrayList[N];
			for(int i=0;i<N;i++) {
				adj[i] = new ArrayList<>();
			}
			
			memo = new long[2][N];

			for (int i = 0; i < N - 1; i++) { // 간선 개수, N-1개
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				adj[a].add(b);
				adj[b].add(a);
			} // 문제: 주어진 그래프는 트리임이 보장된다.
			
			long ans = (dfs(0,0,-1,1) + dfs(0,1,-1,1))%MOD; // 문제: 개수가 클 수 있으니 MOD와 나눈 것의 나머지 출력
			System.out.println("----------");
			for(int i=0;i<2;i++) {
				for(int j=0;j<N;j++) {
					System.out.print(memo[i][j]+" ");
				}
				System.out.println();
			}
			
			System.out.println("===========");
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	// 흰-흰/흰-검/검-흰 o, but, 흑-흑 x
	private static long dfs(int v, int color, int parent,int num) { // 정점, 색, 연결된 정점
		
		if(memo[color][v] != 0) {
			return memo[color][v];
		}
		
		long ret = 1;
		
		if(color == 0) { // 흑일 경우,
			for(int i=0 ; i<adj[v].size();i++) {
				if((int)adj[v].get(i) != parent) {
					
					ret *= dfs((int)adj[v].get(i),1,v,num+1);
					ret%=MOD;
				}
			}
		} else { // 백일 경우
			for(int i=0 ; i<adj[v].size();i++) {
				if((int)adj[v].get(i) != parent) {
					
					ret *= (dfs((int)adj[v].get(i),1,v,num+1) + dfs((int)adj[v].get(i),0,v,num+1));
					ret%=MOD;
				}
			}
		}
		
		memo[color][v] = ret;
		System.out.println(v+","+color+","+parent+","+num+","+ret);
		
		return ret;
	}
}
