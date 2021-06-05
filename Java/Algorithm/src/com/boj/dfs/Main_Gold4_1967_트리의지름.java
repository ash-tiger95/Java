package com.boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이) 아이디어
 * 1. 루트가 항상 0이라고 주어졌다.
 * 2. 그러므로 루트~가장 멀리 있는 정점을 찾고
 * 3. 그 정점을 기준으로 가장 멀리 있는 정점을 찾는다.
 * 
 * @author jugia
 *
 */
public class Main_Gold4_1967_트리의지름 {

	static int N, R, max, maxIdx;
	static ArrayList<Node>[] adj;
	static boolean[] visited;

	static class Node {
		int dest, value;

		public Node(int dest, int value) {
			super();
			this.dest = dest;
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		// 문제: 무방향 그래프
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());

			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}

		max = maxIdx = 0;

		visited = new boolean[N];
		visited[0] = true;
		dfs(0, 0); // 첫번째 DFS: 루트 노드(0)에 가장 멀리있는 점을 찾는다.

		visited = new boolean[N];
		visited[maxIdx] = true;
		dfs(maxIdx, 0); // 두번째 DFS: 가장 멀리있는 점을 기준으로 가장 멀리있는 점을 찾는다.

		System.out.println(max);
	}

	private static void dfs(int v, int dist) { // (정점, 누적 가중치)
		if (max < dist) {
			max = dist;
			maxIdx = v;
		}

		for (Node node : adj[v]) {

			if (!visited[node.dest]) { // 트리에서는 한번만 지나게 된다.
				visited[node.dest] = true;
				dfs(node.dest, dist + node.value);
			}

		}
	}
}
