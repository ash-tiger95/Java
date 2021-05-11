package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_1647_도시분할계획 {
	static int N, M;
	static ArrayList<Edge>[] adj;
	static ArrayList<Integer> Ans;

	static class Edge implements Comparable<Edge> {
		int start, end, dist;

		public Edge(int start, int end, int dist) {
			super();
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());

			adj[a].add(new Edge(a, b, c));
			adj[b].add(new Edge(b, a, c));
		}

		Ans = new ArrayList<>();
		prim();
		Collections.sort(Ans);
		int a = 0;
		for (int i = 0; i < Ans.size() - 1; i++) {
			a += Ans.get(i);
		}
		System.out.println(a);

	}

	private static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];

		visited[0] = true;
		pq.addAll(adj[0]);

		while (!pq.isEmpty()) {
			Edge ce = pq.poll();

			if (visited[ce.end]) {
				continue;
			}

			visited[ce.end] = true;
			Ans.add(ce.dist);

			for (int i = 0; i < adj[ce.end].size(); i++) {
				
				pq.offer(adj[ce.end].get(i));
			}
		}
	}

}
