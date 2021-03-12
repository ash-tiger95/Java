package com.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Silver2_1931_회의실배정 {
	static int N;
	static PriorityQueue<Room> pq;

	static class Room implements Comparable<Room> {
		int start, end, time;

		public Room(int start, int end, int time) {
			super();
			this.start = start;
			this.end = end;
			this.time = time;
		}

		@Override
		public int compareTo(Room o) {
			if(this.end == o.end) {
				return Integer.compare(this.start, o.start);
			} else {
				return Integer.compare(this.end, o.end);
			}
		}

		@Override
		public String toString() {
			return "Room [start=" + start + ", end=" + end + ", time=" + time + "]";
		}
		
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pq.add(new Room(a, b, b - a));
			max = max > b ? max : b;
		}

		
		int standard = pq.poll().end;
		int Ans = 1;
		for (int i = 1; i < N; i++) {
			Room cr = pq.poll();
			if (standard <= cr.start) {
//				System.out.println(cr.toString());
				Ans++;
				standard = cr.end;
			}
		}
		System.out.println(Ans);

	}
}
