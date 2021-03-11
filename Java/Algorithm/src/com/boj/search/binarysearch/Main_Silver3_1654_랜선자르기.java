package com.boj.search.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver3_1654_랜선자르기 {
	static int N, K;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[K];
		for (int i = 0; i < K; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(map);

		long left = 1;
		long right = map[K - 1];
		long mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			long temp = 0;

			for (int i = 0; i < K; i++) {
				temp += map[i] / mid;
			}
			if (temp >= N) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}
		System.out.println(right);
	}
}
