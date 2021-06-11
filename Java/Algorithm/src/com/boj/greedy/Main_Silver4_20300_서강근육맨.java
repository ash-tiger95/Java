package com.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver4_20300_서강근육맨 {

	static int N;
	static long[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		in = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(in);

		long M = in[N - 1];
		if (N % 2 == 0) { // 운동기구가 짝수개 일 때
			int left = 0;
			int right = N - 1;

			while (left < right) {
				M = Math.max(M, in[left++] + in[right--]);
			}
		} else { // 운동기구가 홀수개 일 때
			if (N == 1) {
				M = in[0];
			} else {
				int left = 0;
				int right = N - 2;

				while (left < right) {
					M = Math.max(M, in[left++] + in[right--]);
				}
			}
		}

		System.out.println(M);

		br.close();
	}
}
