package com.boj.datastructure.priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이) compareTo 조작하기
 * Heap 이해하기
 * 
 * @author jugia
 *
 */
public class Main_Silver2_11279_최대힙 {

	static int N;
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> {
		int value;

		public Node(int value) {
			super();
			this.value = value;
		}

		@Override
		public int compareTo(Node o) { // 두 수 중 큰 수를 호출
			return (-1) * Integer.compare(this.value, o.value);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 1 <= N <= 100,000
		pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int in = Integer.parseInt(br.readLine());

			if (in == 0) {
				if (pq.isEmpty()) {
					sb.append(0).append("\n");
				} else {
					sb.append(pq.poll().value).append("\n");
				}

			} else {
				pq.add(new Node(in));
			}
		}
		System.out.println(sb);
	}
}
