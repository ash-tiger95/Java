package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Silver2_1713_후보추천하기 {
	
	static int N,T;
	static int[] in;
	static PriorityQueue<Node> pq;
	
	static class Node implements Comparable<Node>{
		int idx;
		int referCnt;
		int time;
		
		public Node(int idx, int referCnt, int time) {
			super();
			this.idx = idx;
			this.referCnt = referCnt;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.referCnt == o.referCnt) {
				return Integer.compare(this.time, o.time);
			} else {
				return Integer.compare(this.referCnt, o.referCnt);
			}
		}

	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 사진 틀의 개수, 1 <= N <= 20
		T = Integer.parseInt(br.readLine()); // 총 추천 횟수
		
		pq = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int count = 0;
		for(int t=0;t<T;t++) {
			int m = Integer.parseInt(st.nextToken());
			
			Node cn = pq.poll();
			
		}
	}
}
