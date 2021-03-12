package com.boj.dp.memoi;

import java.util.Scanner;

public class Main_Silver3_11727_2xn타일링2 {
	static int N;
	static int[] memoi;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 1 <= N <= 1,000
		memoi = new int[N + 1];

//		bottomUp();
//		System.out.println(memoi[N]);

		System.out.println(topDown(N));
	}

	private static void bottomUp() { // 18388KB, 232ms

		for (int i = 0; i < N + 1; i++) {
			if (i == 0) {
				memoi[i] = 1;
			} else if (i == 1) {
				memoi[i] = 1;
			} else if (i == 2) {
				memoi[i] = 3;
			} else {
				memoi[i] = (memoi[i - 1] + memoi[i - 2] * 2) % 10007;
			}
		}

	}

	private static int topDown(int n) { // 18496KB, 260ms
		if (n == 0 || n == 1) {
			return 1;
		}
		if (n == 2) {
			return 3;
		}

		if (memoi[n] != 0) {
			return memoi[n];
		}

		memoi[n] = (topDown(n - 1) + 2 * topDown(n - 2)) % 10007;

		return memoi[n];
	}

}
