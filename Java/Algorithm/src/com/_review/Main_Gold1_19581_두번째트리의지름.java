package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold1_19581_두번째트리의지름 {

	static int N;
	static ArrayList<Node>[] adj;
	static int[][] dist;
	
	static class Node implements Comparable<Node>{
		int dest, value;
		
		public Node(int dest, int value) {
			this.dest = dest;
			this.value	 = value;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		for(int i=0;i<N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b,c));
			adj[b].add(new Node(a,c));
		}
		
		dist = new int[N][N];
		
		for(int i=0;i<N;i++) {
			dijkstra(i);
		}
		
		int max = Integer.MIN_VALUE;
		int ans = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(max <= dist[i][j]) {
					ans = max;
					max = dist[i][j];
				}
			}
		}
		System.out.println(ans);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq =new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int[] distance = new int[N];
		
		pq.offer(new Node(start,0));
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cn = pq.poll();
			
			if(visited[cn.dest]) {
				continue;
			}
			
			for(Node next : adj[cn.dest]) {
				if(distance[next.dest] > distance[cn.dest] + next.value) {
					distance[next.dest] = distance[cn.dest] + next.value;
					pq.offer(new Node(next.dest, distance[next.dest]));
				}
			}
			
			visited[cn.dest] = true;
		}
		
		dist[start] = distance.clone();
	}
}
