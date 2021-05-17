package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold5_3020_개똥벌레2 {
	
	static int N,H, count, min;
	static int[][] prefixSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		prefixSum = new int[H][1];
		
		
		for(int i=0;i<N;i++) {
			int in = Integer.parseInt(br.readLine());
			if(i % 2 == 0) { // 석순
				for(int d=H-1, count = 0 ; count < in ; d--,count++) {
					prefixSum[d][0]++;
				}
				
			} else { // 종유석
				for(int d=0;d<in;d++) {
					prefixSum[d][0]++;
				}
				
			}
		}
		
		
		
		// 1. 가장 왼쪽 지점 최기화zz
		
		
		count = 0;
		min = Integer.MAX_VALUE;
		for(int i=0;i<H;i++) {
			if(prefixSum[i][0] == min) {
				count++;
			}
			if(prefixSum[i][0] < min) {
				min = prefixSum[i][0];
				count = 1;
			}
			
		}
		
		System.out.println(min+" "+count);
	}
}
