package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold3_11779_최소비용구하기2 {
	static class Edge implements Comparable<Edge>{
		int to, cost;

		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + ", cost=" + cost + "]";
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
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(to,cost));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1;
		end = Integer.parseInt(st.nextToken()) - 1;
		
		// Dijkstra
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N];
		Edge[] D = new Edge[N];
		int[] parents = new int[N];
		
		for(int n=0;n<N;n++) {
			if(n == start) {
				D[n] = new Edge(n,0);
				parents[n] = n;
			} else {
				D[n] = new Edge(n,Integer.MAX_VALUE);
			}
			pq.add(D[n]);
		}
		
		while(!pq.isEmpty()) {
//			System.out.println("PQ: "+pq.toString());
			Edge cur = pq.poll();
//			System.out.println("start: "+cur.toString());
			
			for(Edge next : adj[cur.to]) { // 현재좌표와 연결되어 있는 모든 간선 검사
				if(!check[next.to] && D[next.to].cost > D[cur.to].cost + next.cost) {
					D[next.to].cost = D[cur.to].cost + next.cost;
					
//					System.out.println("--------------");
//					System.out.println(pq.toString());
//					System.out.println(D[next.to].toString());
					pq.remove(D[next.to]);
//					System.out.println(pq.toString());
					pq.add(D[next.to]);
//					System.out.println(pq.toString());
					parents[next.to] = cur.to;
				}
			}
			check[cur.to] = true;
//			System.out.println("check: "+Arrays.toString(check));
			
			
		}
//		System.out.println(Arrays.toString(D));
//		System.out.println(Arrays.toString(parents));
		
		
		
		int count = 1; // 경로에 포함된 도시개수
		ArrayList<Integer> city = new ArrayList<>(); // 방문한 도시 순서대로
		int s = end;
		city.add(s);
		int index=  1;
		
		while(true) {
			city.add(parents[s]);
			if(parents[s] == start) {
				count++;
				break;
			}
			s = city.get(index);
			count++;
			index++;
			
			
		}
		System.out.println(D[end].cost);
		System.out.println(count);
		for(int i=city.size()-1;i>=0;i--) {
			System.out.print((city.get(i)+1)+" ");
		}
		
		
		
	}
}
