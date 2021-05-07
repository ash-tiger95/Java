package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver3_2003_수들의합2 {

	static int N, M, Ans;
	static int[] in;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Ans = 0;

		N = Integer.parseInt(st.nextToken()); // 수열의 크기, 1 <= N <= 10,000
		M = Integer.parseInt(st.nextToken()); // 수열의 연속합으로 M 만들기

		in = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 0;
		long sum = 0;

		while (end <= N) { // end는 가리키는 index의 값을 포함하지 않는다.
			if (sum >= M) {
				sum -= in[start++];
			} else {
				sum += in[end++];
			}

			if (sum == M) {
				Ans++;
			}
		}

		System.out.println(Ans);

	}
}
