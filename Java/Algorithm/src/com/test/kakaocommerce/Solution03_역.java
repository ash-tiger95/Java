package com.test.kakaocommerce;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Solution03_역 {

	static int N,idx,max;
	static int[][] adj;
	static int[] distance;
	static boolean[] visited;
	
	static class Node{
		int v, weight;

		public Node(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "[v=" + v + ", weight=" + weight + "]";
		};
	}

	public static int[] solution(int n, int[] passenger, int[][] train) {
		int[] answer = new int[2];
		N = n;
		max = Integer.MIN_VALUE;

		adj = new int[n][n];
		for (int i = 0; i < train.length; i++) { // adj[i][j]는 i->j로 가면 adj[i][j] 승객을 태울 수 있다.
			adj[train[i][0] - 1][train[i][1] - 1] = passenger[train[i][1] - 1];
			adj[train[i][1] - 1][train[i][0] - 1] = passenger[train[i][0] - 1];
		}

		distance = new int[N];
		visited = new boolean[N];
		bfs(0,passenger);
		
		answer[0] = idx+1;
		answer[1] = max;
		
		System.out.println(Arrays.toString(answer));
		return answer;
	}

	private static void bfs(int start,int[] passenger) {

		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(start,passenger[start]));
		visited[start] = true;

		while (!q.isEmpty()) {
			Node cp = q.poll();
			
			if(cp.weight >= max) { // max, idx 업데이트
				if(cp.weight == max) { // max가 같으면 idx가 높은 걸로
					idx = Math.max(cp.v, idx);
				}else {
					idx = cp.v;
					max = cp.weight;
				}
			}
			

			for (int i = 0; i <N; i++) { // 역 탐색
				if (!visited[i] && adj[cp.v][i] != 0) { // 방문한 적 없고 연결되어 있을 때
					visited[i] = true;
					q.offer(new Node(i,cp.weight+passenger[i]));
					
				}
			}
		}

	}
	

	public static void main(String[] args) {
		// testcase 1.
		int n = 6;
		int[] passenger = { 1, 1, 1, 1, 1, 1 };
		int[][] train = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 3, 5 }, { 3, 6 } };
		
		// testcase 2.
//		int n = 4;
//		int[] passenger = { 2,1,2,2 };
//		int[][] train = { { 1, 2 }, { 1, 3 }, { 2, 4 } };
		
		// testcase 3.
//		int n = 5;
//		int[] passenger = { 1,1,2,3,4 };
//		int[][] train = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 } };
		System.out.println(solution(n, passenger, train));
	}
}
