package com;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_SW_1251_하나로 {
	static class Edge implements Comparable<Edge> {
		int idx;
		long cost;

		public Edge(int idx, long cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "Edge [idx=" + idx + ", cost=" + cost + "]";
		}
		
		

	}

	static int T, N, Ans;
	static double E;
	static long[][] isLands;
	static long[][] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\1251.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			Ans = 0;
			N = Integer.parseInt(br.readLine());
			StringTokenizer x = new StringTokenizer(br.readLine());
			StringTokenizer y = new StringTokenizer(br.readLine());

			isLands = new long[N][2];
			for (int i = 0; i < N; i++) {
				isLands[i][0] = Long.parseLong(x.nextToken());
				isLands[i][1] = Long.parseLong(y.nextToken());
			}
			E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 2; j++) {
					System.out.print(isLands[i][j] + " ");
				}
				System.out.println();
			}

			graph = new long[N][N];
			long[] from, to;
			for (int r = 0; r < N; r++) {
				from = isLands[r];
				System.out.println("from: " + Arrays.toString(from));
				for (int c = r + 1; c < N; c++) {
					to = isLands[c];
					System.out.println("to " + Arrays.toString(to));
					graph[r][c] = graph[c][r] = (from[0] - to[0]) * (from[0] - to[0])
							+ (from[1] - to[1]) * (from[1] - to[1]); // 해저터널 길이의 제곱
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(graph[i][j] + " ");
				}
				System.out.println();
			}

			double cost = prim(0) * E;

			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static double prim(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 모든 정점들을 다 관리하는 dynamicGraph
		Edge[] dynamicGraph = new Edge[N];

		for (int n = 0; n < dynamicGraph.length; n++) {
			dynamicGraph[n] = new Edge(n, Long.MAX_VALUE);
			System.out.println("dg: " + dynamicGraph[n]);
//					pq.add(dynamicGraph[n]);
			if (n == start) {
				dynamicGraph[n].cost = 0;
			}
			pq.add(dynamicGraph[n]);
			System.out.println(pq.toString());
		}
		System.out.println("out: " + pq.toString());
		System.out.println();
		System.out.println();
		System.out.println();

		long cost = 0;
		while (!pq.isEmpty()) {
			Edge front = pq.poll(); // MST에 포함된 녀석
			cost += front.cost;
			System.out.println("111: " + pq.toString());
			// 자식 탐색
			for (int i = 0; i < dynamicGraph.length; i++) {
				Edge child = dynamicGraph[i];
				System.out.println("222: " + child.toString());
				System.out.println("333: " + pq.toString());
				// pq: 비 MST
				if (pq.contains(child)) {

					long tempCost = graph[front.idx][child.idx];
					if (tempCost < child.cost) {
						child.cost = tempCost;
						pq.remove(child);
						pq.offer(child);
					}
				}
			}
		}

		return cost;
	}
}
