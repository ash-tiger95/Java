package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_1719_택배 {
	static class Edge implements Comparable<Edge>{
		int v,weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [v=" + v + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	static int N,M;
	static ArrayList<Edge>[] adj;
	static char[][] Ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 집화장 개수, 200이하의 자연수
		M = Integer.parseInt(st.nextToken()); // 경로 개수, 10,000이하의 자연수
		
		adj = new ArrayList[N];
		for(int i=0;i<N;i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(end,weight));
			adj[end].add(new Edge(start,weight));
		}
		
		for(int i=0;i<N;i++) {
			System.out.println(adj[i].toString());
		}
		
		Ans = new char[N][N];
		for(int i=0;i<N;i++) {
			dijkstra(i); // 모든 정점 검사
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(Ans[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int[] distance = new int[N]; // 최단 길이 저장
		int[] parents = new int[N]; // 최단 길이의 부모 노드 저장
		
		pq.offer(new Edge(start,0)); // 최초 시작점
		visited[start] = true;
		Arrays.fill(distance, Integer.MAX_VALUE); // 시작점을 제외한 모든 길이는 무한대.
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			System.out.println("in?");
			Edge ce = pq.poll();
			
			if(!visited[ce.v]) {
				continue;
			}
			System.out.println("in??");
			for(Edge next : adj[ce.v]) {
				System.out.println("in???");
				if(distance[next.v] > distance[ce.v] + next.weight) {
					System.out.println("in????");
					distance[next.v] = distance[ce.v] + next.weight;
					parents[next.v] = ce.v;
				}
			}
			visited[ce.v] = true;
		}
		
		System.out.println("visited: "+Arrays.toString(visited));
		System.out.println("distance: " + Arrays.toString(distance));
		
		for(int i=0;i<N;i++) {
			if(i==start) {
				Ans[start][i] = '-'; // test
			}
			else {
				
				Ans[start][i] = (char)(parents[i]+'0');
			}
		}
	}
}
