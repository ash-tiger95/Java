package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver1_9084_동전 {

	static int T, N, M;
	static int[] coin;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 동전 가지 수, 1 <= N <= 20
			
			coin = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				coin[i] = Integer.parseInt(st.nextToken()); // 동전의 각 금액(오름차순 입력)
			}
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 만들어야 할 금액, 1 <= M <= 10,000
			
			
		}
	}
}
