package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Bronze2_1668_트로피진열 {

	static int N;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		in = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			in[i] = Integer.parseInt(st.nextToken());
		}

		// 1. 왼쪽 탐색
		int cnt = 0;
		int temp = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if (temp < in[i]) {
				temp = in[i];
				cnt++;
			} else {
				continue;
			}
		}

		System.out.println(cnt);

		// 2. 오른쪽 탐색
		cnt = 0;
		temp = Integer.MIN_VALUE;
		for (int i = N - 1; i >= 0; i--) {
			if (temp < in[i]) {
				temp = in[i];
				cnt++;
			} else {
				continue;
			}
		}

		System.out.println(cnt);
	}
}
