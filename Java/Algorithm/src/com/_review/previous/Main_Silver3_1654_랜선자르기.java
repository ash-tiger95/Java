package com._review.previous;

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

		K = Integer.parseInt(st.nextToken()); // 서로 다른 K개의 랜선, 1 <= K <= 10,000
		N = Integer.parseInt(st.nextToken()); // N개의 같은 길이 랜선, 1 <= N <= 1,000,000, K <= N
		// 이때, 만들 수 있는 최대 랜선 길이 구하기
		
		map = new int[K];
		for (int i = 0; i < K; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(map);

		long left = 1;
		long right = map[K - 1];
		long mid = 0;
		
		while (left <= right) {
			long temp = 0;
			mid = (left + right) / 2;

			for (int i = 0; i < K; i++) {
				temp += map[i] / mid; // mid로 잘랐을 때 생기는 랜선 더하기
			}
			
			if (temp >= N) { // 목표한 N개보다 크면 자르는 길이를 증가시켜 최대 길이를 구한다.
				left = mid + 1;
			} else { // 목표한 N개에 못 미치면 길이를 줄인다.
				right = mid - 1;
			}

		}
		System.out.println(right);
	}
}
