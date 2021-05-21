package com.boj.search.adjacency.matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이)
 * 1. 인접행렬로 데이터 저장
 * 2. 간단한 DFS
 * 3. 간단한 BFS
 * 
 * @author jugia
 *
 */
public class Main_Silver2_1260_DFS와BFS {

	static int N, M, V;
	static int[][] adj; // 인접 행렬
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점 개수, 1 <= N <= 1,000
		M = Integer.parseInt(st.nextToken()); // 간선 개수, 1 <= M <= 10,000
		V = Integer.parseInt(st.nextToken()); // 시작정점

		adj = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a][b] = adj[b][a] = 1;
		}

		visited = new boolean[N + 1];
		dfs(V);

		sb.append("\n");

		visited = new boolean[N + 1];
		bfs();

		System.out.println(sb);
	}

	private static void dfs(int start) {
		sb.append(start).append(" ");
		
		visited[start] = true;
		
		for (int i = 1; i < N + 1; i++) {
			if (adj[start][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();

		q.offer(V);
		visited[V] = true;

		while (!q.isEmpty()) {
			int cp = q.poll();
			sb.append(cp).append(" ");

			for (int i = 1; i < N + 1; i++) {
				if (adj[cp][i] == 1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}
}
