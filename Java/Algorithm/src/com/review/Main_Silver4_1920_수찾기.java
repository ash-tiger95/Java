package com.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver4_1920_수찾기 {

	static int N, M;
	static int[] map, input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		input = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(map); // 정렬

		// 이분탐색
		for (int m = 0; m < M; m++) {
			System.out.println(check(input[m]) == true ? 1 : 0);
		}
	}

	private static boolean check(int key) { // 이분탐색
		int left = 0;
		int right = N - 1;
		int mid;

		while (left <= right) {
			mid = (left + right) / 2;

			if (map[mid] == key) {
				return true;
			}

			if (key >= map[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return false;
	}
}
