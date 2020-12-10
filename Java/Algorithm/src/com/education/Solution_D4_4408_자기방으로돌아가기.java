package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_4408_자기방으로돌아가기 {
	static class Room {
		int s;
		int e;
		int d;

		// 1->3 이나 3->1이나 같다.
		public Room(int s, int e) {
			super();
			this.s = s;
			this.e = e;

			// swap
			if (s > e) {
				int t = this.s;
				this.s = this.e;
				this.e = t;
			}
			d = Math.abs(this.e - this.s);
		}

		@Override
		public String toString() {
			return "[s=" + s + ", e=" + e + ", d=" + d + "]";
		}
	}

	static class RCT implements Comparator<Room> {

		// 그리디 대부분 90%는 크다 작다로 정렬하고 풀어
		// d는 오른차순으로 되어있어
		@Override
		public int compare(Room o1, Room o2) { // 거리가지고 정렬, o1이 늦게 들어온거 o2가 먼저들어온거
			System.out.println("o1: " + o1.toString());
			System.out.println("o2: " + o2.toString());

			if (o1.d > o2.d) { // 거리를 불러와. 앞에가 크면 그대로
				return -1; // 앞이 크면 마이너스 그대로 놨둬
			} else if (o1.d < o2.d) {
				return 1; // 1이면 뒤집어
			} else { // 거리가 같으면 작은 방을 앞으로
				// return 0; // return 0하면 거리 정렬밖에 안해줘
				if (o1.s > o2.s) { // 작은게 앞으로 가야되니까 뒤집어
					return 1;
				} else if (o1.s < o2.s) {
					return -1;
				}
				return 0;
			}
		}

	}

	static int T, N, Ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			
			//new RCT를 하면 데이터가 들어가는 즉시 순서를 정해줘 
			//다 받고 정렬하는 것보다 속도훨씬빠름
			PriorityQueue<Room> rs = new PriorityQueue<>(new RCT()); 
			
			for(int i=0 ;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				rs.add(new Room(s,e)); // 거리까지 구해져
				System.out.println("add: " + rs.toString());
			}
			
			int size = rs.size();
//			for(int i=0;i<size;i++) {
//				System.out.println("rs print: " + rs.toString());
//			}
			
			int[] room = new int[201];
			// 디센딩 -> 래퍼
			Integer[] 복도 = new Integer[201];
			Arrays.fill(복도, 0); // null이 생길 수 있어서 해줘야됨. // reverseOrder쓰려면 해야되
			while(!rs.isEmpty()) {
				Room r= rs.poll();
				for(int i = r.s;i<=r.e;i++)
					복도[i]++;
			}
			Arrays.sort(복도,Comparator.reverseOrder());//정렬
			System.out.println("#"+t+" "+복도[0]);
			

		}
	}
}
