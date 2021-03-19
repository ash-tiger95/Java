package com.test.scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모의01_근묵자흑 {

	static int N, K, min, minIdx, answer;
	static int[] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			if (min > map[i]) { // 1. 최솟값 기억
				min = map[i];
				minIdx = i;
			}
		}

		answer = Integer.MAX_VALUE;
		// 2. ex) K가 3일 때, _ _ _에서 최솟값을 각각 넣어봐야한다.
		for (int k = 0; k < K; k++) {
			search(minIdx - k, minIdx + K - 1 - k);
		}
		System.out.println(answer + 1);
	}

	private static void search(int start, int finish) {
		if (start < 0 || finish > N - 1) {
			return;
		}

		int sum = 0;

		int left = start / (K - 1);
		if (start % (K - 1) != 0) {
			left++;
		}

		int right = (N - 1 - finish) / (K - 1);
		if ((N - 1 - finish) % (K - 1) != 0) {
			right++;
		}

		answer = Math.min(left + right, answer);

		return;
	}

}
