package com.programmers.binarysearch;

import java.util.Arrays;

public class Main_Level3_입국심사 {

	private static long solution(int n, int[] times) {
		long Ans = Long.MAX_VALUE;

		Arrays.sort(times);

		long start = 0;
		long end = (long) times[times.length - 1] * n; // 최악의 경우
		long mid;

		long sum = 0;

		while (start <= end) {
			mid = (start + end) / 2;
			sum = 0;

			for (int i = 0; i < times.length; i++) {
				sum += mid / times[i];

				if (sum >= n) {
					break;
				}
			}

			if (n > sum) {
				start = mid + 1;
			} else {
				end = mid - 1;
				Ans = Math.min(Ans, mid);
			}
		}

		return Ans;
	}

	public static void main(String[] args) {
		int n = 6;
		int[] times = { 7, 10 };
		System.out.println(solution(n, times));
	}

}
