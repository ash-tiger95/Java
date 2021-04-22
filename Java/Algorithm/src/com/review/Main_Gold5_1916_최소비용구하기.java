package com.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold5_1916_최소비용구하기 {
	
	static int N,M,s,e;
	static List<Node>[] adj;
	
	static class Node implements Comparable<Node>{
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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N];
		for(int i=0;i<N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			adj[start].add(new Node(end, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e= Integer.parseInt(st.nextToken());
		
		dijkstra();
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int[] distance = new int[N];
		
		pq.offer(new Node(s,0));
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[s] = 0;
		
		while(!pq.isEmpty()) {
			
		}
	}
}
