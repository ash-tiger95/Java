package com.test.scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 일차06_팝업스토어2 { // 정답

	static int N, M, answer;
	static int[][] map;
	static int[][] dirs = { { 1, 0 }, { 0, 1 } };
	
	static int[][] memoi;

	static class Node {
		int y, x, colCnt, rowCnt, value;

		public Node(int y, int x, int colCnt, int rowCnt, int value) {
			super();
			this.y = y;
			this.x = x;
			this.colCnt = colCnt;
			this.rowCnt = rowCnt;
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		memoi = new int[N][M];
		memoi[0][0] = map[0][0];
		for(int i=1;i<N;i++) { // 세로 memoi 최초작업
			memoi[i][0] += memoi[i-1][0]+map[i][0];
		}
		for(int i=1;i<M;i++) { // 세로 memoi 최초작업
			memoi[0][i] += memoi[0][i-1] + map[0][i];
		}
		
		
		for(int i=1;i<N;i++) {
			for(int j=1;j<M;j++) {
				int temp = Math.max(memoi[i-1][j],memoi[i][j-1]);
				
				memoi[i][j] = map[i][j]+ temp;
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(memoi[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println(memoi[N-1][M-1]);
		
//		answer = 0;
//		bfs(0, 0);
//		System.out.println(answer);
	}


}
