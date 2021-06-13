package com.boj.kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이) 기본 Kruskal 
 * 다른 풀이) Prim
 * 
 * @author jugia
 *
 */
public class Main_Gold4_4386_별자리만들기 {

	static int N;
	static double Ans;
	static double[][] in;
	static PriorityQueue<Node> pq;
	static int[] parents;

	static class Node implements Comparable<Node> {
		int from, to;
		double value;

		public Node(int from, int to, double value) {
			super();
			this.from = from;
			this.to = to;
			this.value = value;
		}

		public int compareTo(Node o) {
			return Double.compare(this.value, o.value);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		in = new double[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			in[i][0] = Double.parseDouble(st.nextToken());
			in[i][1] = Double.parseDouble(st.nextToken());
		}

		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double len = Math.sqrt(Math.pow(in[i][0] - in[j][0], 2) + Math.pow(in[i][1] - in[j][1], 2));
				pq.offer(new Node(i, j, len));
				pq.offer(new Node(j, i, len));
			}
		}

		kruskal();
		System.out.println(String.format("%.2f", Ans));
		
		br.close();
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
			return find(parents[x]);
		}
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return;
		} else {
			if (a < b) {
				parents[b] = a;
			} else {
				parents[a] = b;
			}

			return;
		}
	}

	private static void kruskal() {
		makeSet();

		int count = 1;

		for (int i = 0; i < N * (N - 1) / 2; i++) { // 간선 수만큼 반복
			Node cn = pq.poll();

			// a와 b의 부모 노드 find
			int a = find(cn.from);
			int b = find(cn.to);

			if (a == b) { // 부모가 같으면 싸이클이 생기므로 패스
				continue;
			}

			union(a, b); // 다르면 부모 통합
			Ans += cn.value;

			count++;
			if (count == N) {
				break;
			}
		}

		pq.clear();
	}
}
