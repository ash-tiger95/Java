package com.boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver2_1260_DFS와BFS {
	static int N, M, V;
	static int[][] map;
	static boolean[] visited;
	static ArrayList<Integer> Ans;
	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점 개수
		M = Integer.parseInt(st.nextToken()); // 간선 개수
		V = Integer.parseInt(st.nextToken()) - 1; // 시작점

		map = new int[N][N];
		

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			map[a][b] = map[b][a] = 1;
		}
		Ans = new ArrayList<>();
		Ans.add(V+1);
		visited = new boolean[N];
		visited[V] = true;
		dfs(V);
		for(Integer i : Ans) {
			System.out.print(i+" ");
		}
		System.out.println();
		
		Ans.clear();
		queue = new LinkedList<>();
		queue.offer(V);
		visited = new boolean[N];
		visited[V] = true;
		bfs();
		for(Integer i : Ans) {
			System.out.print(i+" ");
		}

	}

	private static void bfs() {
		while(!queue.isEmpty()) {
			int cp = queue.poll();
			Ans.add(cp+1);
			
			for(int i=0;i<N;i++) {
				if(!visited[i] && map[cp][i] == 1) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
		
	}

	private static void dfs(int start) {
		for (int i = 0; i < N; i++) {
			if (map[start][i] == 1 && !visited[i]) {
				Ans.add(i+1);
				visited[i] = true;
				dfs(i);
			}
		}
	}
}
