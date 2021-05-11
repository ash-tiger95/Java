package com._review.previous;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 프림
public class Main_Gold4_1922_네트워크연결_prim {
	static int N, M;
	static int distance;
	static boolean[] visited;
	static ArrayList<Node>[] list;
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> {
		int start;
		int end;
		int value;

		public Node(int start, int end, int value) {
			super();
			this.start = start;
			this.end = end;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + ", value=" + value + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine()); // 컴퓨터 개수
		M = Integer.parseInt(br.readLine()); // 간선 개수

		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());

			list[start].add(new Node(start, end, value));
			list[end].add(new Node(end, start, value));
		}

		pq = new PriorityQueue<Node>();
		visited = new boolean[N];
		distance =0;
		
		for(int s=0;s<list[0].size();s++) {
			pq.offer(new Node(list[0].get(s).start,list[0].get(s).end,list[0].get(s).value));
		}
//		for(int i=0;i<size;i++) {
//			System.out.println(pq.poll().toString());
//		}

		visited[0] = true;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(!visited[cur.end]) {
				visited[cur.end] = true;
				distance += cur.value;
				for(int s=0;s<list[cur.end].size();s++) {
					pq.offer(new Node(cur.end,list[cur.end].get(s).end,list[cur.end].get(s).value));
				}
			}
			
			
			
		}
		System.out.println(distance);

	}

}
