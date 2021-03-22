package com.programmers.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_합승택시요금 {
	
	static ArrayList<Node>[] adj;
	
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
	

	public static void main(String[] args) {
		
	}
	
	public int solution(int n, int s, int a, int b, int[][] fares) {
		// n: 지점 갯수, s: 출발지점
        int answer = 0;
        
        adj = new ArrayList[n+1];
        for(int i=0;i<fares.length;i++) {
        	adj[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
        	adj[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
        }
        
        dijkstra(n,s,a,b);
        
        
        return answer;
    }

	private void dijkstra(int N, int S, int A, int B) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int[] distance = new int[N];
		
		pq.offer(new Node(S,0));
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[S] = 0;
		
		while(!pq.isEmpty()) {
			Node ce = pq.poll();
			
			if(visited[ce.v]) {
				continue;
			}
			
			for(Node next : adj[ce.v]) {
				if(distance[next.v] > distance[ce.v] + next.weight) {
					distance[next.v] = distance[ce.v] + next.weight;
					pq.offer(new Node(next.v, distance[next.v]));
				}
			}
			
			visited[ce.v] = true;
			
		}
	}

}
