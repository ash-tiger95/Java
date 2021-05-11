package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_Silver3_2606_바이러스 {
	static int V, E, Ans;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		visited = new boolean[V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a].add(b);
			adj[b].add(a);
		}

		Ans = 0;
		visited[0] = true;
		dfs(0);
		System.out.println(Ans);
	}

	private static void dfs(int start) {

		for (int i = 0; i < adj[start].size(); i++) {
			if (!visited[adj[start].get(i)]) {
				visited[adj[start].get(i)] = true;
				Ans++;
				dfs(adj[start].get(i));
			}
		}

		return;
	}

}
