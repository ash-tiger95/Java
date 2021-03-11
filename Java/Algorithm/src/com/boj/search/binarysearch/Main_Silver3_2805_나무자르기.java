package com.boj.search.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver3_2805_나무자르기 {
	static int N, M;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(map);

		int left = 0;
		int right = map[N-1];
		int mid;

		long height = 0;

		while (left <= right) {
			height = 0;

			mid = (left + right) / 2;

			for (int i = 0; i < N; i++) {
				if (map[i] >= mid) {
					height += (map[i] - mid);
				}
			}

			if (height >= M) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(right);
	}

}
