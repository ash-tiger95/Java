package com.boj.datastructure.priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver3_1966_프린터큐 {
	static int T, N, M, answer;
	static Queue<Node> q;

	static class Node {
		int idx, weight;
		boolean check;

		public Node(int idx, int weight, boolean check) {
			super();
			this.idx = idx;
			this.weight = weight;
			this.check = check;
		}
		

		@Override
		public String toString() {
			return idx + ", " + weight + ", " + check + " | ";
		}


	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 문서 개수
			M = Integer.parseInt(st.nextToken()); // 궁금한 문서 번호, 시작은 0번 부터
			q = new LinkedList<>();
			
			int[] important = new int[9];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int a = Integer.parseInt(st.nextToken());
				important[a]++;
				
				q.offer(new Node(i,a, i == M ? true : false));
			}
			
			int max = 0;
			for(int i=0;i<9;i++) {
				max = max < important[i] ? important[i] : max;
			}
			System.out.println(max);

			System.out.println(q);
			
			answer = 0;
			while(!q.isEmpty()) {
				if(important[max--] == 0) {
					continue;
				}
				
				Node cn  = q.poll();
				answer++;
				
				if(cn.weight == max && cn.check) {
					break;
				}
				
				if(cn.weight == max) {
					important[cn.idx]--;
					if(important[cn.idx] == 0) {
						max--;
					}
				}
				
				q.add(cn);
			}
			
			System.out.println(answer);
		}

	}
}
