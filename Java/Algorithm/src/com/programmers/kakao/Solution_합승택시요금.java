package com.programmers.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_합승택시요금 { // 효율성 2개 시간초과....

	/*
	static int Ans;
	static ArrayList<Node>[] adj;
	static int[] distance;
	static int[] distance2;

	static class Node implements Comparable<Node> {

		int v, weight;

		public Node(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		// n: 지점 갯수, s: 출발지점

		adj = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < fares.length; i++) {
			adj[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
			adj[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
		}
//		parents = new int[n + 1];

		Ans = Integer.MAX_VALUE;
		dijkstra(n, s, a, b);
		for (int i = 1; i < n + 1; i++) {
			dijkstra2(n, i, a, b);
//			System.out.println(Arrays.toString(distance2));
			int temp = distance[i] + distance2[a] + distance2[b];
			Ans = Math.min(Ans, temp);

		}

		System.out.println(Ans);
		return Ans;
	}

	private static void dijkstra(int N, int S, int A, int B) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		distance = new int[N + 1];

		pq.offer(new Node(S, 0));
		Arrays.fill(distance, Integer.MAX_VALUE);
//		Arrays.fill(parents, -1);
		distance[S] = 0;
//		parents[S] = 0;

		while (!pq.isEmpty()) {
			Node ce = pq.poll();

			if (visited[ce.v]) {
				continue;
			}

			for (Node next : adj[ce.v]) {
				if (distance[next.v] > distance[ce.v] + next.weight) {
					distance[next.v] = distance[ce.v] + next.weight;
//					parents[next.v] = ce.v;
					pq.offer(new Node(next.v, distance[next.v]));
				}
			}

			visited[ce.v] = true;

		}
	}

	private static void dijkstra2(int N, int S, int A, int B) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		distance2 = new int[N + 1];

		pq.offer(new Node(S, 0));
		Arrays.fill(distance2, Integer.MAX_VALUE);
		distance2[S] = 0;

		while (!pq.isEmpty()) {
			Node ce = pq.poll();

			if (visited[ce.v]) {
				continue;
			}

			for (Node next : adj[ce.v]) {
				if (distance2[next.v] > distance2[ce.v] + next.weight) {
					distance2[next.v] = distance2[ce.v] + next.weight;
					pq.offer(new Node(next.v, distance2[next.v]));
				}
			}

			visited[ce.v] = true;

		}
	}
	*/
	
	static int Ans;
	final static int INF = Integer.MAX_VALUE;
	
	public static int solution(int n, int s, int a, int b, int[][] fares) {
		
		int[][] adj = new int[n+1][n+1];
		for(int i=0;i<n+1;i++) {
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}
		
		
		for(int i=0;i<fares.length;i++) {
			adj[fares[i][0]][fares[i][1]] = fares[i][2];
			adj[fares[i][1]][fares[i][0]] = fares[i][2];
		}
		
		for(int k=1;k<n+1;k++) {
			for(int i=1;i<n+1;i++) {
				if(k == i) {
					continue;
				}
				for(int j=1;j<n+1;j++) {
					if(k == i || k == j) {
						continue;
					}
					
					if(adj[i][k] != INF && adj[k][j] != INF && adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
//		for(int i=1;i<n+1;i++) {
//			for(int j=1;j<n+1;j++) {
//				System.out.print(adj[i][j]+" ");
//			}
//			System.out.println();
//		}
		Ans = adj[s][a] + adj[s][b];
		for (int i = 1; i <= n; i++) 
            Ans = Math.min(Ans, adj[s][i] + adj[i][a] + adj[i][b]);
		return Ans;
	}
	
	
	public static void main(String[] args) {
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
				{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };
		System.out.println(solution(n, s, a, b, fares));
	}

}
