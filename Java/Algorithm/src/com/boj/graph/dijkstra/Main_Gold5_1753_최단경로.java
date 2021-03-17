package com.boj.graph.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold5_1753_최단경로 {

	static class Edge implements Comparable<Edge> {
		int v;
		int weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			return "Point [v=" + v + ", weight=" + weight + "]";
		}
	}

	static int V, E, K;
	static ArrayList<Edge>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수, 1<= V <= 20,000
		E = Integer.parseInt(st.nextToken()); // 간선 개수, 1 <= E <= 300,000

		K = Integer.parseInt(br.readLine()) - 1; // 시작점

		adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Edge>();
		}

		// 방향 그래프
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(end, val));
		}

		// 시작 지점으로부터 최단 거리 구하기
		int[] distance = new int[V];
		distance = dijkstra();

		// distance는 0번부터 i번째까지의 최단경로 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < V; i++) {
			if (distance[i] != Integer.MAX_VALUE) {
				sb.append(distance[i]).append('\n');
			} else {
				sb.append("INF").append('\n');
			}
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static int[] dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V];
		int[] distance = new int[V];

		pq.offer(new Edge(K, 0)); // 시작점
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[K] = 0;

		while (!pq.isEmpty()) {
			Edge ce = pq.poll();

			if (visited[ce.v]) {
				continue;
			}

			for (Edge next : adj[ce.v]) {
				if (distance[next.v] > distance[ce.v] + next.weight) {
					distance[next.v] = distance[ce.v] + next.weight;
					pq.offer(new Edge(next.v, distance[next.v]));
				}
			}

			visited[ce.v] = true;
		}

		return distance;
	}
}
