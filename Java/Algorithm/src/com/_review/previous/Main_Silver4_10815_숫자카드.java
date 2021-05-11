package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver4_10815_숫자카드 {
	static int N,M;
	static int[] card, check;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		card = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		check = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			check[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(card);
		for(int i=0;i<M;i++) {
			binarySearch(check[i]);
		}
		
		System.out.println(sb);
	}
	private static void binarySearch(int num) {
		int left = 0;
		int right = card.length - 1;
		int mid;
		while(left <= right) {
			mid = (left+right)/2;
			
			if(num == card[mid]) {
				sb.append(1).append(" ");
				return;
			}
			
			if(num < card[mid]) {
				right = mid-1;
			} else {
				left = mid + 1;
			}
		}
		
		sb.append(0).append(" ");
		return;
	}

}
