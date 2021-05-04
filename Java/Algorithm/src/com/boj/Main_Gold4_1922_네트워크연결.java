package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_1922_네트워크연결 {

	static int N, M, Ans, Ans2;
	
	// Prim
	static ArrayList<Node>[] list;
	
	// Kruskal
	static int[] parent;
	static ArrayList<Node2> list2;
	
	// Prim
	static class Node implements Comparable<Node>{
		int end;
		int weight;
		
		public Node(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	// Kruskal
	static class Node2 implements Comparable<Node2>{
		int start;
		int end;
		int weight;
		
		public Node2(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node2 o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Node2 [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 입력
		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수, 1 <= N <= 1,000
		M = Integer.parseInt(br.readLine()); // 연결하는 선의 수, 1 <= M <= 100,000
		Ans = 0;
		Ans2 = 0;

		// Prim
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) { // 0번 정점은 생략
			list[i] = new ArrayList<>();
		}
		
		// Kruskal
		list2 = new ArrayList<>();

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()); // 정점1
			int b = Integer.parseInt(st.nextToken()); // 정점2
			int c = Integer.parseInt(st.nextToken()); // 가중치

			// Prim, 양방향 그래프 만들기
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
			
			//Kruskal
			list2.add(new Node2(a,b,c));
		}
		
		// Kruskal
		makeSet();
		kruskal();
		// 58512KB, 664ms (count 계산 x)

		// Prim
//		prim();
		// 52872KB, 472ms (count 계산 o)

		System.out.println(Ans2);
	}
	
	private static void kruskal() {
		Collections.sort(list2); // list 정렬하기
		
		for(int i=0;i<list2.size();i++) {
			Node2 cn = list2.get(i);
			
			// 싸이클 발생 시 제외
			if(find(cn.start) != find(cn.end)) {
				Ans2 += cn.weight;
				union(cn.start, cn.end);
			}
		}
	}
	
	private static void makeSet() {
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int x) {
		if(parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			if(a < b) {
				parent[b] = a;
			} else {
				parent[a] =b;
			}
		}
	}
	
	private static void prim() {
		// Prim 준비
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		
		// 1번 정점부터 시작
		for (int i = 0; i < list[1].size(); i++) {
			pq.add(list[1].get(i));
		}
		visited[1] = true;

		// Prim
		int count = 1; // 모든 정점을 확인하면 더 이상 검사할 필요가 없다.
		
		while (!pq.isEmpty() && count < N) {
			Node c = pq.poll();

			if (visited[c.end]) {
				continue;
			}

			visited[c.end] = true; // 목적지 방문 처리
			Ans += c.weight;
			count++;

			for (int i = 0; i < list[c.end].size(); i++) { // 목적지와 연결된 모든 정점 추가
				pq.offer(list[c.end].get(i));
			}
		}
		
		return;
	}
}
