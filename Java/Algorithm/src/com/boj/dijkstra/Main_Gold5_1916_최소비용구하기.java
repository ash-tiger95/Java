package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold5_1916_최소비용구하기 {
	
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

	static int N, M, start, end;
	static ArrayList<Edge>[] adj;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 도시 개수, 1 <= N <= 1,000
		M = Integer.parseInt(br.readLine()); // 버스 개수, 1 <= M <= 100,000

		adj = new ArrayList[N];
		for (int n = 0; n < N; n++) {
			adj[n] = new ArrayList<>();
		}

		// 방향 그래프
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int dest = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(dest, cost));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1; // 시작 도시
		end = Integer.parseInt(st.nextToken()) - 1; // 도착 도시

		// 시작 도시에서 도착 도시까지의 최소 비용 구하기
		dijkstra();

	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int[] distance = new int[N];

		pq.offer(new Edge(start, 0)); // 시작 도시로 시작
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Edge ce = pq.poll();

			if (visited[ce.v]) {
				continue;
			}

			for (Edge next : adj[ce.v]) { // 연결된 도시 불러오기
				if (distance[next.v] > distance[ce.v] + next.weight) { // 거리 계산
					distance[next.v] = distance[ce.v] + next.weight; // 최소 거리로 업데이트
					pq.offer(new Edge(next.v, distance[next.v]));
				}
			}

			visited[ce.v] = true; // 한 번 방문한 도시는 방문처리
		}

		System.out.println(distance[end]); // 도착 도시까지의 최소 비용 출력
	}
}
