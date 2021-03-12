package com.boj.dp.memoi;

import java.util.Scanner;

public class Main_Silver2_15988_123더하기3 {
	static int T, N;
	static long[] memoi;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		memoi = new long[1000000 + 1];
		bottomUp();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 1 <= N <= 1,000,000

			System.out.println(memoi[N]);
		} // for T
	}

	private static void bottomUp() {

		for (int i = 1; i < 1000000 + 1; i++) {
			if (i == 1) {
				memoi[i] = 1;
			} else if (i == 2) {
				memoi[i] = 2;
			} else if (i == 3) {
				memoi[i] = 4;
			} else {
				memoi[i] = (memoi[i - 1] + memoi[i - 2] + memoi[i - 3]) % 1000000009;
			}
		}
	}
}
