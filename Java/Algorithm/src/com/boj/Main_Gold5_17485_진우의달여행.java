package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_17485_진우의달여행 {

	static int N, M;
	static int[][] map;
	static int[][][] memoi;
	static int[][] dirs = { { 1, 0 }, { 1, 1 }, { 1, -1 } };
	
	static class Node{
		int y,x,d;
		
		public Node(int y, int x,int d) {
			super();
			this.y =y;
			this.x = x;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		memoi = new int[N][M][3]; // 아래, 우대각선, 좌대각선으로 올 경우
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				Arrays.fill(memoi[i][j], Integer.MAX_VALUE);
			}
		}
		
		for(int i=0;i<M;i++) {
			bfs(i);
		}
	}
	
	private static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0,start,-1));
		
		while(!q.isEmpty()) {
			Node cn = q.poll();
			
			for(int d=0;d<3;d++) {
				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];
				
				if(!boundary(ny,nx)) {
					continue;
				}
				
				if(cn.d == 0) {
					memoi[ny][nx][1] = Math.max(memoi[ny][nx][1], memoi[cn.y][cn.x][1]);
				}
				
				
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny>=0 && ny<N && nx>=0 && nx<M;
	}

}
