package com._review.day210511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver4_1920_수찾기 {

	static int N, M;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 <= N <= 100,000

		map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(map); // 이분 탐색을 위한 정렬

		// 입력
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int t = 0; t < M; t++) {
			int m = Integer.parseInt(st.nextToken());

			int left = 0;
			int right = N - 1;
			int mid;

			boolean flag = false;

			while (left <= right) { // 이분 탐색
				mid = (left + right) / 2;

				if (map[mid] == m) {
					sb.append(1).append("\n");
					flag = true;
					break;
				}

				if (map[mid] > m) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}

			if (!flag) {
				sb.append(0).append("\n");
			}

		} // for M

		System.out.println(sb);
	}

}
