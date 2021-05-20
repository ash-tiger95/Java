package com.boj.datastructure.priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_Gold2_1655_가운데를말해요 {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 1 <= N <= 100,000

		// 람다식
		PriorityQueue<Integer> front = new PriorityQueue<>((o1, o2) -> (o2 - o1)); // 큰 수 찾기
		PriorityQueue<Integer> rear = new PriorityQueue<>((o1, o2) -> (o1 - o2)); // 작은 수 찾기

		for (int i = 0; i < N; i++) {
			int in = Integer.parseInt(br.readLine());

			if (front.size() == rear.size()) {
				front.offer(in);
			} else {
				rear.offer(in);
			}

			if (!front.isEmpty() && !rear.isEmpty()) {
				if (front.peek() > rear.peek()) {
					int temp = rear.poll();
					rear.offer(front.poll());
					front.offer(temp);
				}
			}

			sb.append(front.peek()).append("\n");
		}
		System.out.println(sb);
	}
}
