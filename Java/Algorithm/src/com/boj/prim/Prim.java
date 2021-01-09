package com.boj.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prim {
	static class Edge implements Comparable<Edge>{
		int start;
		int dest; 
		int cost;
		Edge(int s, int d, int c){
			start = s;
			dest = d;
			cost = c;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost); // 오름차순
		}
		@Override
		public String toString() {
			return "Edge [start=" + start + ", dest=" + dest + ", cost=" + cost + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		//각 정점별로 인접리스트 참조변수
		ArrayList<Edge>[] adj = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		//입력되어지는 간선 배열을 인접리스트에 저장
		for(int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			adj[a].add(new Edge(a,b,c));
			adj[b].add(new Edge(b,a,c));
		}
		
		for(int i=0;i<V;i++) {
			System.out.print(i+"번쨰: ");
			for(int j=0;j<adj[i].size();j++) {
				System.out.println(adj[i].get(j).toString());
			}
		}
		System.out.println("==============");
		
		boolean[] visited = new boolean[V];
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		int[] p = new int[V];
		p[0] = -1;
		int[] dist = new int[V];
		//0번을 방문하거로 체크하고, 0번에서 이어지는 모든 간선을 queue에 삽입.
		visited[0] = true;
		queue.addAll(adj[0]);
		//확보한 정점의 개수
		int cnt = 1;
		int result = 0;
		//모든 정점을 확보할때까지
		while( cnt != V ) {
			Edge edge = queue.poll();
			System.out.println(edge.toString());
			//이미 확보된 정점이 나오면 다시.
			if( visited[edge.dest] )
				continue;
			p[edge.dest] = edge.start;
			//확보되지 않는 놈이 나오면. 방문체크하고, 그놈에서부터 나가는 간선을 큐에 추가
			result += edge.cost;
			queue.addAll(adj[edge.dest]);
			visited[edge.dest] = true;
			cnt++;
			System.out.println(Arrays.toString(visited));
		}
		System.out.println(result);
		System.out.println(Arrays.toString(p));
	}
}
/*
7 11
0 1 2
0 2 2
0 5 8
1 2 1
1 3 19
2 5 5
3 4 7
3 5 11
3 6 15
4 5 9
4 6 3
*/
