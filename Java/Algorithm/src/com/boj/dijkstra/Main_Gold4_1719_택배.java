package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		
		
		
	}
}
