package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_1504_특정한최단경로 {

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
			return "Edge [v=" + v + ", weight=" + weight + "]";
		}

	}

	static int N, E;
	static int R1, R2; // 반드시 지나야하는 두 정점
	static ArrayList<Edge>[] list;
	static final int INF = 200_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Edge(b, c));
			list[b].add(new Edge(a, c));
		}
		st = new StringTokenizer(br.readLine());
		R1 = Integer.parseInt(st.nextToken()) - 1;
		R2 = Integer.parseInt(st.nextToken()) - 1;

		// 0 -> R1 -> R2 -> N-1
		// 0 -> R2 -> R1 -> N-1
		// 두 가지 경우 비교
		int case1 = 0, case2 = 0;

		case1 += dijkstra(0, R1);
		case1 += dijkstra(R1, R2);
		case1 += dijkstra(R2, N - 1);

		case2 += dijkstra(0, R2);
		case2 += dijkstra(R2, R1);
		case2 += dijkstra(R1, N - 1);

		if (case1 >= INF || case2 >= INF)
			System.out.println(-1);
		else
			System.out.println(Math.min(case1, case2));

	}

	private static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		boolean[] visited = new boolean[N];
		int[] distance = new int[N];

		pq.add(new Edge(start, 0));
		Arrays.fill(distance, INF);
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Edge ce = pq.poll();

			if (visited[ce.v])
				continue;

			for (Edge next : list[ce.v]) {
				if (distance[next.v] > distance[ce.v] + next.weight) {
					distance[next.v] = distance[ce.v] + next.weight;
					pq.offer(new Edge(next.v, distance[next.v]));
				}
			}

			visited[ce.v] = true;
		}
		return distance[end];
	}

}
