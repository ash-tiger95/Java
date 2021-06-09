package com.boj.graph;

import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Main_Gold5_14699_관악산등산 {

	static int N, M;
	static int[] h;
	static int[][] adj;
	static PriorityQueue<Node> pq;
	static int[] ans;

	static class Node implements Comparable<Node> {
		int num, height;

		public Node(int num, int height) {
			super();
			this.num = num;
			this.height = height;
		}

		@Override
		public int compareTo(Node o) {
			return (-1) * Integer.compare(this.height, o.height);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 쉼터 개수, 2 <= N <= 5,000
		M = Integer.parseInt(st.nextToken()); // 쉼터 연결 개수, 1 <= M <= 100,000

		st = new StringTokenizer(br.readLine());
		h = new int[N]; // 쉼터 높이, 1 <= h <= 1,000,000
		pq = new PriorityQueue<>(); // 가장 높은 쉼터 뽑기 위해
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			h[i] = height;
			pq.offer(new Node(i, height));
		}

		adj = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			// 화살표 방향: 낮은 쉼터 -> 높은 쉼터
			if (h[a] > h[b]) {
				adj[b][a] = 1; // 낮은 쉼터에 표시
			} else {
				adj[a][b] = 1;
			}
		}

		ans = new int[N];
		while (!pq.isEmpty()) {
			Node cn = pq.poll(); // 가장 높은 쉼터부터 시작

			for(int i=0;i<N;i++) {
				if(adj[cn.num][i] == 1) { // 자신보다 높은 쉼터 탐색해서 큰 수로 업데이트
					if(ans[cn.num] < ans[i]) { 
						ans[cn.num]= ans[i]; 
					}
				}
			}
			
			ans[cn.num]++; // 자기 자신 쉼터 +1
		}
		
		for(int i=0;i<N;i++) {
			sb.append(ans[i]).append("\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
