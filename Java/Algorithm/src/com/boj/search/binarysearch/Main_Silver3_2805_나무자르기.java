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

		N = Integer.parseInt(st.nextToken()); // 나무의 수, 1 <= N <= 1,000,000
		M = Integer.parseInt(st.nextToken()); // 필요한 나무 길이, 1,000,000 <= M <= 2,000,000,000
		map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(map); // 오름차순 정렬

		int left = 0;
		int right = map[N-1]; // 가장 큰 나무 길이까지
		int mid;

		// int: -2,147,483,648 ~ 2,147,483,647
		long height = 0; // 최악: 2,000,000,000 * 1,000,000

		while (left <= right) {
			height = 0; // 계산해야할 M

			mid = (left + right) / 2;

			for (int i = 0; i < N; i++) {
				if (map[i] >= mid) {
					height += (map[i] - mid); // mid가 주어졌을 때, 구할 수 있는 나무 길이
				}
			}

			if (height >= M) { // M보다 크면 height를 높여서 잘라야 하는 나무를 최소화
				left = mid + 1;
			} else { // M보다 작으면  height를 낮춰서 요구에 충족
				right = mid - 1;
			}
		}
		System.out.println(right); // 결국, 마지막은 right (이렇게 해도 되고, 아니면  Math.min 수시로 하는게 더 편할듯)
	}

}
