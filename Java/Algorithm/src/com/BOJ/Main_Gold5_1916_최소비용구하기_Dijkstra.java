package com.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold5_1916_최소비용구하기_Dijkstra {
	static class Edge implements Comparable<Edge>{
		int dest, cost;

		public Edge(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [dest=" + dest + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	static int N,M,start,end;
	static ArrayList<Edge>[] adj;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 도시 개수, 1 <= N <= 1,000
		M = Integer.parseInt(br.readLine()); // 버스 개수, 1 <= M <= 100,000
		
		adj = new ArrayList[N];
		for(int n=0;n<N;n++) {
			adj[n] = new ArrayList<>();
		}
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int dest = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(dest,cost));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1;
		end = Integer.parseInt(st.nextToken()) - 1;
		
		// Dijkstra
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N];
		Edge[] cost = new Edge[N];
		
		for(int n=0;n<N;n++) {
			if(n == start) {
				cost[n] = new Edge(n,0);
			} else {
				cost[n] = new Edge(n,Integer.MAX_VALUE);
			}
			pq.add(cost[n]);
		}
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			for(Edge next : adj[cur.dest]) {
				if(!check[next.dest] && cost[next.dest].cost > cost[cur.dest].cost + next.cost) {
					cost[next.dest].cost = cost[cur.dest].cost + next.cost;
					
					pq.remove(cost[next.dest]);
					pq.add(cost[next.dest]);
				}
			}
			check[cur.dest] = true;
			
			
		}
		
		System.out.println(cost[end].cost);
		
		
		
		
	}
}
