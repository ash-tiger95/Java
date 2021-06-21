package com.boj.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold1_1414_불우이웃돕기 { // 방향그래프 MST는 크루스칼

	static int N, min, total;
	static int[][] map;
	static int[] parents;
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> {
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

		@Override
		public String toString() {
			return "Node [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		total = 0; // 총 랜선 길이
		pq = new PriorityQueue<>(); // 크루스칼
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			char[] in = br.readLine().toCharArray();

			for (int j = 0; j < N; j++) {
				if(in[j] == '0') {
					continue;
				} else if (in[j] -'a' >= 0 && in[j]-'a' <= 26) { // a ~ z
					map[i][j] = in[j] - 'a' + 1;
				} else { // A ~ Z
					map[i][j] = in[j] - 'A' + 27;
				}

				if (i != j) {
					pq.add(new Node(i, j, map[i][j]));
				}

				total += map[i][j];
			}
		}
		
		min = 0; // MST 최소 간선 길이
		int cnt = kruskal() == N ? N : -1;
		
		if (cnt == -1) {
			sb.append(-1);
		} else {
			sb.append(total - min);
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
	
	private static int kruskal() {
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

		return visitedCnt;
	}

	private static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}

		return;
	}

	private static int find(int x) {
		if (parents[x] == x) {
			return x;
		} else {
			return parents[x] = find(parents[x]); // find(parents[x]) 주의하자!
		}
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y) {
			return;
		} else {
			if (x < y) {
				parents[y] = x;
			} else {
				parents[x] = y;
			}

			return;
		}
	}
}
