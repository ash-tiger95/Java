package com.boj.prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이) 기본 Prim
 * 다른 풀이) Kruskal
 * 
 * @author jugia
 *
 */
public class Main_Gold4_4386_별자리만들기 {

	static int N;
	static double Ans;
	static double[][] in;
	static ArrayList<Node>[] list;

	static class Node implements Comparable<Node> {
		int to;
		double value;

		public Node(int to, double value) {
			super();
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

		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double len = Math.sqrt(Math.pow(in[i][0] - in[j][0], 2) + Math.pow(in[i][1] - in[j][1], 2));
				list[i].add(new Node(j, len));
				list[j].add(new Node(i, len));
			}
		}

		prim();
		System.out.println(String.format("%.2f", Ans));
		
		br.close();
	}

	private static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int count = 1;

		// 0번 부터 시작
		visited[0] = true;
		pq.addAll(list[0]);

		while (!pq.isEmpty()) {
			Node cn = pq.poll();

			if (visited[cn.to]) {
				continue;
			}

			visited[cn.to] = true;

			Ans += cn.value;
			pq.addAll(list[cn.to]);
			
			count++;
			if (count == N) {
				break;
			}
		}
		pq.clear();

	}
}
