package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_Gold5_2015_수들의합4 {
	
	static int N,K;
	static long Ans;
	static int[] prefixSum;
	static HashMap<Integer, Long> hm;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // map 크기, 1 <= N <= 200,000
		K = Integer.parseInt(st.nextToken()); // 구간합이 M이 되는 경우의 수 찾기, |K| <= 2,000,000,000
		
		st = new StringTokenizer(br.readLine());
		prefixSum = new int[N + 1]; // ★. e는 포함되지 않기 때문에 N까지 들어가는 경우를 생각해야 한다.
		prefixSum[0] = 0;
		for (int i = 1; i < N+1; i++) {
			prefixSum[i] = prefixSum[i-1]+Integer.parseInt(st.nextToken());
		}
		
		
		hm = new HashMap<>();
		for(int i=1;i<N+1;i++) {
			if(prefixSum[i] == K) {
				Ans++;
			}
			
			Ans+= hm.get(prefixSum[i] - K);
		}
		
	}
}
