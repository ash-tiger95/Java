package com.boj.datastructure.priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이) compareTo 조작하기
 * 힙에 대해 이해하기
 * 
 * @author jugia
 *
 */
public class Main_Silver1_11286_절댓값힙 {

	static int N;
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> {
		int value;

		public Node(int value) {
			super();
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			if (Math.abs(this.value) == Math.abs(o.value)) { // 절댓값이 같으면 작은 수를 먼저
				return Integer.compare(this.value, o.value); 
			} else { // 절댓값이 다르면 절댓값이 작은 수를 먼저
				return Integer.compare(Math.abs(this.value), Math.abs(o.value)); 
			}
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
				if (pq.isEmpty()) { // 큐가 비어있으면 0 출력
					sb.append(0).append("\n");
				} else { // 가장 작은 절댓값 출력
					sb.append(pq.poll().value).append("\n");
				}

			} else {
				pq.add(new Node(in));
			}
		}
		System.out.println(sb);
	}
}
