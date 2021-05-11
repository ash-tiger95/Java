package com._review.previous;

import java.util.Scanner;

public class Main_Silver3_9095_123더하기 {
	static int T, N;
	static int[] memoi;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		memoi = new int[12];
		bottomUp();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 1 <= N <= 11

			System.out.println(memoi[N]);
		} // for T
	}

	private static void bottomUp() {

		for (int i = 1; i < 12; i++) {
			if (i == 1) {
				memoi[i] = 1;
			} else if (i == 2) {
				memoi[i] = 2;
			} else if (i == 3) {
				memoi[i] = 4;
			} else {
				memoi[i] = memoi[i - 1] + memoi[i - 2] + memoi[i - 3];
			}
		}
	}

}
