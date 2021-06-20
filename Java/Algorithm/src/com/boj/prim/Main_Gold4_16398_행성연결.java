package com.boj.prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_16398_행성연결 {

	static int N;
	static long min; // 자료형 조심하자!
	static ArrayList<Node>[] adj;
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> {
		int to, cost;

		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행성의 수, 1 ≤ N ≤ 1000

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken()); // 비용, 1 ≤ cost ≤ 100,000,000

				if (i == j) {
					continue;
				}

				adj[i].add(new Node(j, cost));
			}
		}

		min = 0;
		prim();

		sb.append(min);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];

		pq.addAll(adj[0]);
		visited[0] = true;

		int visitedCnt = 1;

		while (!pq.isEmpty()) {
			Node cn = pq.poll();

			if (visited[cn.to]) {
				continue;
			}

			min += cn.cost;
			pq.addAll(adj[cn.to]);

			visited[cn.to] = true;
			visitedCnt++;

			if (visitedCnt == N) {
				pq.clear();
				break;
			}
		}
	}
}
