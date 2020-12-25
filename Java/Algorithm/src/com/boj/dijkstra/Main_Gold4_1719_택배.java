package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_1719_택배 {

	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [v=" + v + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int N, M;
	static ArrayList<Edge>[] adj;
	static int[] parents;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 집화장 개수, 200이하의 자연수
		M = Integer.parseInt(st.nextToken()); // 경로 개수, 10,000이하의 자연수

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<Edge>();
		}

		// 무방향 그래프
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(end, weight));
			adj[end].add(new Edge(start, weight));
		}

		sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			dijkstra(i); // 모든 정점의 최단 경로 구하기
		}

		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int[] distance = new int[N]; // 최단 길이
		parents = new int[N]; // 최단 길이의 부모 노드

		pq.offer(new Edge(start, 0)); // 최초 시작점
		Arrays.fill(distance, Integer.MAX_VALUE); // 시작점을 제외한 모든 길이는 무한대
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Edge ce = pq.poll();

			if (visited[ce.v]) {
				continue;
			}

			for (Edge next : adj[ce.v]) {
				if (distance[next.v] > distance[ce.v] + next.weight) {
					distance[next.v] = distance[ce.v] + next.weight;
					parents[next.v] = ce.v;
					pq.offer(new Edge(next.v, distance[next.v]));
				}
			}

			visited[ce.v] = true;
		}
		
//		System.out.println("parents: "+Arrays.toString(parents));

		for (int i = 0; i < N; i++) {
			if (i == start) {
				sb.append("- ");
			} else {
				int ans = searchPath(i, start);
				sb.append((ans+1)+" ");
			}
		}
		sb.append("\n");
	}

	private static int searchPath(int i, int start) { // 역추적
//		System.out.println(i +" "+parents[i]+" "+start);
		if (parents[i] == start)
			return i;
		
		return searchPath(parents[i], start);
	}

}
