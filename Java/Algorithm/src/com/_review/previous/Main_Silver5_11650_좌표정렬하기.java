package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Silver5_11650_좌표정렬하기 {

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) { // this가 나중에 들어온 값
			if (this.x > o.x) {
				return 1; // 양수이면 this가 뒤로
			} else if (this.x < o.x) {
				return -1; // 음수이면 this가 앞으로
			} else {
				if (this.y > o.y) {
					return 1;
				} else if (this.y < o.y) {
					return -1;
				} else {
					return 0;
				}
			}
		}

		@Override
		public String toString() {
			return x + " " + y;
		}
	}

	static int N;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<Point>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int inputX = Integer.parseInt(st.nextToken());
			int inputY = Integer.parseInt(st.nextToken());
			pq.add(new Point(inputX, inputY));
		}
		
		int size = pq.size();
		while (size-- > 0) {
			System.out.println(pq.poll());
		}
	}
}
