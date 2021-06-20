package com.boj.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_16398_행성연결 {

	static int N;
	static long min; // 자료형 조심하자!
	static int[] parents;
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> { // union을 하기 위해 from, to 모두 필요
		int from, to, cost;

		public Node(int from, int to, int cost) {
			super();
			this.from = from;
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
		N = Integer.parseInt(st.nextToken()); // 행성의 수, 1 ≤ N ≤ 1,000

		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken()); // 비용, 1 ≤ cost ≤ 100,000,000

				if (i < j) {
					pq.offer(new Node(i, j, cost)); // prim과 차이점2. union을 하기 때문에 i->j, j->i 두 가지 경우가 필요없다.
					// 두 가지 경우 모두 pq에 넣으면 1112ms, 하나만 넣으면 916ms
				} else {
					continue;
				}

			}
		}

		min = 0;
		kruskal();
		
		sb.append(min);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}

		return;
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			if (x < y) {
				parents[y] = x;
			} else {
				parents[x] = y;
			}
		}

		return;
	}

	private static int find(int x) {
		if (parents[x] == x) {
			return x;
		} else {
			return parents[x] = find(parents[x]);
		}
	}

	private static void kruskal() {
		makeSet();

		int visitedCnt = 1;

		while (!pq.isEmpty()) {
			Node cn = pq.poll();

			int x = find(cn.from);
			int y = find(cn.to);

			if (x == y) {
				continue;
			} else {
				union(x, y);
				min += cn.cost;

				visitedCnt++;
			}

			if (visitedCnt == N) {
				pq.clear();
				break;
			}
		}

		return;
	}
}
