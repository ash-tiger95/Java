package com.boj.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_Silver4_10989_수정렬하기3 {
	static int N;
	static PriorityQueue<Point> pq;

	static class Point implements Comparable<Point> {
		int x;

		public Point(int x) {
			super();
			this.x = x;
		}

		@Override
		public int compareTo(Point o) {
			// x 검사
			if (this.x > o.x) { // 지금 가지고 있는게 더 크면 지금 들어온것이 앞으로 간다.
				return 1; // 양수면 바꾸기
			} else {
				return -1; // 음수면 그대로
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<Point>();
		
		// 입력
		for (int n = 0; n < N; n++) {
			int x = Integer.parseInt(br.readLine());
			pq.add(new Point(x));
		}
		
		// 출력
		while (pq.isEmpty()) {
			Point points = pq.poll();
			System.out.println(points.x);
		}
	}
}
