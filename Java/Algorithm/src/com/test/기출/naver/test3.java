package com.test.기출.naver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class test3 {

	static int N, R;
	static int[] output;
	static int[][] adj;

	private static int[] solution(int n, int[][] edges) {
		int[] answer = new int[2];
		N = n;
		R = n / 3 - 1;
		output = new int[R];

		adj = new int[N][N];
		for (int i = 0; i < edges.length; i++) {
			adj[edges[i][0]][edges[i][1]] = 1;
			adj[edges[i][1]][edges[i][0]] = 1;
		}

//		nCr(0, 0);

		return answer;
	}

	public static void nCr(int cnt, int cur, int[][] edges) {
		if (cnt == R) {
			System.out.println(Arrays.toString(output));
			
			for(int i=0;i<R;i++) {
				adj[edges[output[i]][0]][edges[output[i]][1]] = 0;
				adj[edges[output[i]][1]][edges[output[i]][0]] = 0;
			}
			
			bfs();

			return;
		}

		for (int i = cur; i < N - 1; i++) {
			output[cnt] = i;
			nCr(cnt + 1, i + 1, edges);
		}
	}

	private static void bfs() {
		boolean[] visited = new boolean[N];
		
		for(int t = 0; t<N/3;t++) { // 잘린 개수만큼 돌리기
			Queue<Integer> q = new LinkedList<>();
			for(int i=0;i<N;i++) {
				if(!visited[i]) {
					q.add(i);
					break;
				}
			}
			
			while(!q.isEmpty()) {
				int cn = q.poll();
				
				
			}
			
		}
	}

	public static void main(String[] args) {
		int n = 9;
		int[][] edges = { { 0, 2 }, { 2, 1 }, { 2, 4 }, { 3, 5 }, { 5, 4 }, { 5, 7 }, { 7, 6 }, { 6, 8 } }; // edges의
																											// 길이는 n-1:
																											// 무조건 다 연결

		System.out.println(solution(n, edges));
	}

}
