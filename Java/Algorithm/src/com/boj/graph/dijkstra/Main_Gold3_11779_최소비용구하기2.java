package com.boj.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_Gold3_11779_최소비용구하기2 {

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
	static int[] distance, parents;
	static int cityCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 도시 개수, 1 <= N <= 1,000
		M = Integer.parseInt(br.readLine()); // 버스 개수, 1 <= M <= 100,000

		adj = new ArrayList[N];
		for (int n = 0; n < N; n++) {
			adj[n] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(to, cost));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1;
		end = Integer.parseInt(st.nextToken()) - 1;

		dijkstra();
		cityCnt = 0;
		Stack<Integer> stack = searchPath();

		System.out.println(distance[end]);
		System.out.println(cityCnt);
		while (!stack.isEmpty()) {
			int city = stack.pop();
			System.out.print((city + 1) + " ");
		}

	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		distance = new int[N];
		parents = new int[N];

		pq.offer(new Edge(start, 0));
		Arrays.fill(distance, Integer.MAX_VALUE);
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
	}

	public static Stack<Integer> searchPath() { // 역추적
		Stack<Integer> stack = new Stack<>();
		int cur = end;

		while (cur != start) {
			stack.push(cur);
			cityCnt++;

			cur = parents[cur];
		}
		stack.push(cur);
		cityCnt++;

		return stack;
	}
}
