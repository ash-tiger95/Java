package com.boj.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold5_3020_개똥벌레 {
	
	static int N,H, count, min;
	static boolean[][] map;
	static int[][] prefixSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new boolean[H][N]; 
		
		for(int i=0;i<N;i++) {
			int in = Integer.parseInt(br.readLine());
			if(i % 2 == 0) { // 석순
				for(int d=H-1, count = 0 ; count < in ; d--,count++) {
					map[d][i] = true;
				}
				
			} else { // 종유석
				for(int d=0;d<in;d++) {
					map[d][i] = true;
				}
				
			}
		}
		
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		
		prefixSum = new int[H][N];
		
		// 1. 가장 왼쪽 지점 최기화
		for(int i=0;i<H;i++) {
			if(map[i][0]) {
				prefixSum[i][0] = 1;
			}
		}
		
		for(int i=0;i<H;i++) {
			for(int j=1;j<N;j++) {
				if(map[i][j]) {
					prefixSum[i][j]= prefixSum[i][j-1] + 1;
				} else {
					prefixSum[i][j]= prefixSum[i][j-1];
				}
			}
		}
		
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(prefixSum[i][j]+" ");
			}
			System.out.println();
		}
		
		count = 0;
		min = Integer.MAX_VALUE;
		for(int i=0;i<H;i++) {
			if(prefixSum[i][N-1] == min) {
				count++;
			}
			if(prefixSum[i][N-1] < min) {
				min = prefixSum[i][N-1];
				count = 1;
			}
			
		}
		
		System.out.println(min+" "+count);
	}
}
