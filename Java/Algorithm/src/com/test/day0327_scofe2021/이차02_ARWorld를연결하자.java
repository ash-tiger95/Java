package com.test.day0327_scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이차02_ARWorld를연결하자 {

	static int N, Ans;
	static HashMap<String, Integer> hm; // ex) seoul 1번, beijing 2번, ...
	static ArrayList<Node>[] adj;

	static class Node implements Comparable<Node> {
		int dest;
		int cost;

		public Node(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost); // 비용 오름차순
		}

		@Override
		public String toString() {
			return "[dest=" + dest + ", cost=" + cost + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		hm = new HashMap<>();
		StringTokenizer st = null;
		int idx = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String start = st.nextToken();
			String end = st.nextToken();
			int c = Integer.parseInt(st.nextToken()); // 비용

			int startId;
			int endId;
			if (!hm.containsKey(start)) {
				hm.put(start, idx++);
				startId = idx - 1;
			} else {
				startId = hm.get(start);
			}

			if (!hm.containsKey(end)) {
				hm.put(end, idx++);
				endId = idx - 1;
			} else {
				endId = hm.get(end);
			}

			adj[startId].add(new Node(endId, c));
			adj[endId].add(new Node(startId, c));

		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(adj[i]);
//		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		visited[0] = true;
		pq.addAll(adj[0]);
		Ans = 0;
		
		while (!pq.isEmpty()) {
			Node  cn = pq.poll();
			
			if(visited[cn.dest]) {
				continue;
			}
			
			visited[cn.dest] = true;
			Ans += cn.cost;
			
			pq.addAll(adj[cn.dest]);
		}
		
		System.out.println(Ans);

	}

}
