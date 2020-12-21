package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Silver5_11650_좌표정렬하기_Comparable {
	static class Point implements Comparable<Point>{
		int x,y;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			// 이렇게 하면 안되.
//			if(this.x != o.x) {
//				return this.y - o.y;
//			} else {
//				return this.x - o.x;
//			}
			if(this.x > o.x) {
				return 1;
			} else if(this.x < o.x) {
				return -1;
			} else {
				if(this.y > o.y) {
					return 1;
				} else if(this.y < o.y) {
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
		
		for(int i=0;i<N;i++) {
			st  = new StringTokenizer(br.readLine());
			int inputX = Integer.parseInt(st.nextToken());
			int inputY = Integer.parseInt(st.nextToken());
			
			pq.add(new Point(inputX, inputY));
		}
		int size= pq.size();
		while(size-->0) {
			System.out.println(pq.poll());
		}
	}
}	
