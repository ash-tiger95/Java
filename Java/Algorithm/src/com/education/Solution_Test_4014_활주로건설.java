package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_Test_4014_활주로건설 {
	static int T,N,X,Ans;
	static int[][] map;
	static int[][] dirs = {{0,1},{1,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int tc =1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map= new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// x축 검사
			/*
			for(int i=0;i<N;i++) {
				int[] row = new int[N];
				boolean[] visited = new boolean[N];
				row = map[i].clone();
				int maxHigh=0;
				for(int r=0;r<row.length;r++) {
					maxHigh = Math.max(row[r], maxHigh);
				}
				for(int r=0;r<row.length;r++) {
					if(row[r] == maxHigh) {
						visited[r] = true;
					}
				}
				
				int sameHigh = 0;
				for(int r=0;r<N;r++) {
					if(!visited[r]) {
						
					}
				}
				
				
				System.out.println(Arrays.toString(row));
			}
			*/
			
			for(int i=0;i<N;i++) {
				dfs(i,0,0,1,false);
			}
			// y축 검사
		}
	}

	private static void dfs(int y, int x, int idx, int cnt, boolean flag) {
		int ny = y + dirs[idx][0];
		int nx = x + dirs[idx][1];
		
		if(ny == N || nx == N) {
			
			return;
		}
		
		if(map[y][x] == map[ny][nx]) {
			dfs(ny,nx,idx,cnt+1,flag);
		} else {
			if(Math.abs(map[y][x] - map[ny][nx]) >= 2) {
				return;
			} 
			
			if(map[y][x] < map[ny][nx] && !flag && cnt>=X) {
				dfs(ny,nx,idx,1,false);
			} 
		}
	}
	
}
