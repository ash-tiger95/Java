package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_10421_수식완성하기 {

	static int N, K;
	static int[] star, use;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		star = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			star[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());

		use = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			use[i] = Integer.parseInt(st.nextToken());
		}
		
	}
	
	private static void nPir(int cnt) {
	}
}
