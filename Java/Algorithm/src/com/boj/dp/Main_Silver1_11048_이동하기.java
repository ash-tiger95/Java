package com.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver1_11048_이동하기 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				map[i][j] = Math.max(map[i-1][j], Math.max(map[i][j-1], map[i-1][j-1])) + map[i][j];
			}
		}

		System.out.println(map[N][M]);
		
		// 내 풀이: 메모리초과
		memoi = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(memoi[i], -1);
		}
		dp();

	}
	
	// 메모리 초과
	static int[][] memoi;
	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
	
	static class Node{
		int y,x,value;
		public Node(int y,int x,int value) {
			super();
			this.y=y;
			this.x=x;
			this.value=value;
		}
	}
	
	private static void dp() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0,0,map[0][0]));
		memoi[0][0] = map[0][0];
		while (!q.isEmpty()) {
			Node cn = q.poll();

			for (int d = 0; d < 3; d++) {
				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}
				
				if(memoi[ny][nx] < cn.value + map[ny][nx]) {
					memoi[ny][nx] = cn.value + map[ny][nx];
					q.offer(new Node(ny,nx,cn.value+map[ny][nx]));
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
