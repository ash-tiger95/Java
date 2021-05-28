package com.boj.search.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver1_20495_수열과헌팅 {
	
	static int N;
	static int[][] in, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		in = new int[N][2];
		ans = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			in[i][0] = Integer.parseInt(st.nextToken()); // a
			in[i][1] = Integer.parseInt(st.nextToken()); // +- b
			
			for(int j=in[i][0] ; j<=in[i][1];j++) {
			}
		}
		
		for (int i = 0; i < N; i++) {
			binarySearch(in[i][0]-in[i][1], in[i][0]+in[i][1]);
		}
		System.out.println(sb);
	}

	private static void binarySearch(int left, int right) {
		int mid;
		
		while(left <= right) {
			mid = (left+right)/ 2;
			
			
		}
	}
}
