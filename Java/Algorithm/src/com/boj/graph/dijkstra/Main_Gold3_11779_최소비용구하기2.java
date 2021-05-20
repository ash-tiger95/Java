package com.boj.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_Gold3_11779_최소비용구하기2 {

	static int N, M, start, end, cityCnt;
	static ArrayList<Node>[] adj;
	static int[] distance, parents;

	static class Node implements Comparable<Node> {
		int to, value;

		public Node(int to, int value) {
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());

			adj[a].add(new Node(b, c));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1;
		end = Integer.parseInt(st.nextToken()) - 1;

		dijkstra();

		cityCnt = 0;
		Stack<Integer> stack = searchPath();

		sb.append(distance[end]).append("\n");
		sb.append(cityCnt).append("\n");

		while (!stack.isEmpty()) {
			sb.append(stack.pop() + 1).append(" ");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		distance = new int[N];
		parents = new int[N];

		// 초기 설정
		pq.offer(new Node(start, 0));
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Node cn = pq.poll();

			if (visited[cn.to]) {
				continue;
			}

			for (Node next : adj[cn.to]) {
				if (distance[next.to] > distance[cn.to] + next.value) {
					distance[next.to] = distance[cn.to] + next.value;
					pq.offer(new Node(next.to, distance[next.to]));
					parents[next.to] = cn.to;
				}
			}

			visited[cn.to] = true;
		}
	}

	private static Stack<Integer> searchPath() {
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
