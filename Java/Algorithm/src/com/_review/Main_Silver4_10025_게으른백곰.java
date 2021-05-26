package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver4_10025_게으른백곰 {

	final static int MAX = 1000000;
	static int N, K;
	static int[] map;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		in = new int[N];
		map = new int[1000001];
		long ans = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			in[i] = x;
			if (x - K < 0 && MAX < x + K) {
				for (int j = 0; j <= MAX; j++) {
					map[j] += g;
				}
			} else if (x - K < 0 && x + K <= MAX) {
				for (int j = 0; j <= x + K; j++) {
					map[j] += g;
				}
			} else if (x - K >= 0 && x + K > MAX) {
				for (int j = x - K; j <= MAX; j++) {
					map[j] += g;
				}
			} else {
				for (int j = x - K; j <= x + K; j++) {
					map[j] += g;
				}
			}
		}
		
		Arrays.sort(map);
		System.out.println(map[1000000]);

	}
}
