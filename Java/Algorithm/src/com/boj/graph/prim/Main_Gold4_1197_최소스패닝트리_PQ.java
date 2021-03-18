package com.boj.graph.prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_1197_최소스패닝트리_PQ {
	static int V, E, Ans;
	static ArrayList<Edge>[] adj;

	static class Edge implements Comparable<Edge> {
		int start, dest, cost;

		public Edge(int start, int dest, int cost) {
			super();
			this.start = start;
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost); // 오름차순
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", dest=" + dest + ", cost=" + cost + "]";
		}
		
		

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // 1 <= V <= 10,000
		E = Integer.parseInt(st.nextToken()); // 1 <= E <= 100,000

		adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(a, b, c));
			adj[b].add(new Edge(b, a, c));
		}

		Ans = 0;
		prim();
		System.out.println(Ans);
	}

	private static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V];
//		int[] parents = new int[V];

//		parents[0] = -1;
		visited[0] = true;
		pq.addAll(adj[0]);

		while (!pq.isEmpty()) {
			Edge ce = pq.poll();

			if (visited[ce.dest]) {
				continue;
			}
			visited[ce.dest] = true;
//			parents[ce.dest] = ce.start;

			Ans += ce.cost;
//			pq.addAll(adj[ce.dest]);
			for (int s = 0; s < adj[ce.dest].size(); s++) {
				pq.offer(new Edge(adj[ce.dest].get(s).start, adj[ce.dest].get(s).dest, adj[ce.dest].get(s).cost));
			}
		}
	}
}