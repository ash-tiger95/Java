package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_1922_네트워크연결 {

	static int N, M, Ans;
	
	static class Node implements Comparable<Node>{
		int dest;
		int weight;
		
		public Node(int dest, int weight) {
			super();
			this.dest = dest;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Node [dest=" + dest + ", weight=" + weight + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 입력
		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수, 1 <= N <= 1,000
		M = Integer.parseInt(br.readLine()); // 연결하는 선의 수, 1 <= M <= 100,000
		Ans = 0;

		ArrayList<Node>[] list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) { // 0번 정점은 생략
			list[i] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()); // 정점1
			int b = Integer.parseInt(st.nextToken()); // 정점2
			int c = Integer.parseInt(st.nextToken()); // 가중치

			// 양방향 그래프 만들기
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}

		// Prim 준비, 1번 정점부터 시작
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		for (int i = 0; i < list[1].size(); i++) {
			pq.add(list[1].get(i));
		}
		visited[1] = true; // 시작 정점 방문 처리

		// Prim
		int count = 1; // 모든 정점을 확인하면 더이상 검사할 필요가 없다.
		while (!pq.isEmpty() && count < N) {
			Node c = pq.poll();

			if (visited[c.dest]) {
				continue;
			}

			visited[c.dest] = true; // 목적지 방문 처리
			Ans += c.weight;
			count++;

			for (int i = 0; i < list[c.dest].size(); i++) { // 목적지와 연결된 모든 정점 추가
				pq.offer(list[c.dest].get(i));
			}
		}

		System.out.println(Ans);
	}
}
