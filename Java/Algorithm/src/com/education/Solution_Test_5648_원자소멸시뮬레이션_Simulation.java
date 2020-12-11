package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Test_5648_원자소멸시뮬레이션_Simulation {
	static class Point implements Comparable<Point> {
		int y, x, d, k;

		public Point(int y, int x, int d, int k) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + ", d=" + d + ", k=" + k + "]";
		}

		@Override
		public int compareTo(Point o) {
			if (this.y > o.y) {
				return 1;
			} else if (this.y < o.y) {
				return -1;
			} else {
				if (this.x > o.x) {
					return 1;
				} else if (this.x < o.x) {
					return -1;
				} else {
					return 0;
				}
			}
		}

	}
	
	static class MatchPoint{
		int y,x;

		public MatchPoint(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}

	static int T, N, Ans;
	static int[][] map;
	static PriorityQueue<Point> pq;
	static Queue<Point> tempQueue;
	static Queue<MatchPoint> matchQueue;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			Ans = 0;
			pq = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				pq.add(new Point(Integer.parseInt(st.nextToken()) + 1000, Integer.parseInt(st.nextToken()) + 1000,
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			tempQueue = new LinkedList<>();
			matchQueue = new LinkedList<>();

			while (!pq.isEmpty()) {
				tempQueue.clear();
				Point cp = pq.poll();
				int size = pq.size();
				int count = 0;
				
				

				for (int s = 0; s < size ; s++) {


					Point np = pq.poll();

					if (cp.y == np.y || cp.x == np.x) {

						if (checkDirection(cp.d, np.d)) {
							Ans += cp.k + np.k;
							// 만났을 때, 반례 => 3점이나 4점이 동시에 만날 때
							
							
							break;
						} 
					} else {
						count++;
						tempQueue.add(np);
					}
						
				}
				int tSize = tempQueue.size();
				for(int i=0;i<tSize;i++) {
					pq.offer(tempQueue.poll());
				}

				if (count == size) {
					break;
				}
			}

			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean checkDirection(int cd, int nd) { // 상 하 좌 우
		if (cd == 0 && nd == 1) {
			return true;
		} else if (cd == 1 && nd == 0) {
			return true;
		} else if (cd == 2 && nd == 3) {
			return true;
		} else if (cd == 3 && nd == 2) {
			return true;
		} else {
			return false;
		}

	}
}
