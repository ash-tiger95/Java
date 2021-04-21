package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Silver2_1713_후보추천하기 {

	static int N, M;
	
	static class Node implements Comparable<Node>{
		int num,time, count;

		public Node(int num, int time, int count) {
			super();
			this.num = num;
			this.time = time;
			this.count = count;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.count > o.count) {
				return 1;
			} else if(this.count < o.count) {
				return -1;
			} else {
				if(this.time > o.time) {
					return -1;
				} else { // time이 같은 경우는 없다.
					return 1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Node> pq = new PriorityQueue<>();
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		N = Integer.parseInt(br.readLine()); // 사진 틀의 개수, 1 <= N <= 20
		M = Integer.parseInt(br.readLine()); // 총 추천 횟수, <= 1,000
		
		StringTokenizer st=  new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			int input = Integer.parseInt(st.nextToken()); // 학생을 나타내는 번호, 1부터 100
			
			for(int j=0;j<pq.size();j++) {
				
			}
		}
		
		
		
		
		
	}

}
