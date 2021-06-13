package com.boj.prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_6497_전력난 { // 기본적인 prim

	static int N, M, total, min;
	static ArrayList<Node>[] adj;

	static class Node implements Comparable<Node> {
		int to, value;

		public Node(int to, int value) {
			super();
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) {
				break;
			}

			adj = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				adj[i] = new ArrayList<>();
			}

			total = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				adj[a].add(new Node(b, c));
				adj[b].add(new Node(a, c));
				total += c;
			}
			
			min = 0;
			prim();

			sb.append(total - min).append("\n");
		}

		bw.write(sb.toString()); // 출력
		bw.flush(); // 남아있는 데이터를 모두 출력시킴

		br.close();
		bw.close();
	}

	private static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int cnt = 1;

		visited[0] = true;
		pq.addAll(adj[0]);

		while (!pq.isEmpty()) {
			Node cn = pq.poll();

			if (visited[cn.to]) {
				continue;
			}

			visited[cn.to] = true;

			min += cn.value;
			pq.addAll(adj[cn.to]);

			cnt++;
			if (cnt == N) {
				break;
			}
		}

		pq.clear();

		return;
	}
}
