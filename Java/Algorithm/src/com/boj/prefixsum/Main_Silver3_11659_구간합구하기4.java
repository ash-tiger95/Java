package com.boj.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver3_11659_구간합구하기4 {

	static int N, M;
	static int[] prefixSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		prefixSum = new int[N+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int current = Integer.parseInt(st.nextToken());
			
			prefixSum[i] = prefixSum[i-1] + current;
		}

//		System.out.println(Arrays.toString(prefixSum));

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(prefixSum[b] - prefixSum[a-1]+"\n");
		}
		
		System.out.println(sb);

	}
}
