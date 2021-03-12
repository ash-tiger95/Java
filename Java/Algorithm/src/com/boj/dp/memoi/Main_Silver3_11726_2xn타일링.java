package com.boj.dp.memoi;

import java.util.Scanner;

public class Main_Silver3_11726_2xn타일링 {
	static int N;
	static int[] memoi;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		memoi = new int[N + 1];
		System.out.println(topDown(N));
//		memoi = new int[N];
//		bottomUp();
//		System.out.println(memoi[N-1]);
	}

	private static void bottomUp() { // 18408KB, 248ms
		memoi[0] = 1;
		if (N - 1 > 0) {
			memoi[1] = 2;

		}
		if (N >= 2) {
			for (int i = 2; i < N; i++) {
				memoi[i] = memoi[i - 1] + memoi[i - 2];
				memoi[i] %= 10007;
			}

		}
	}

	private static int topDown(int start) {
		if (start == 0 || start == 1) {
			return 1;
		}

		if (memoi[start] > 0) {
			return memoi[start];
		}

		memoi[start] = (topDown(start - 2) + topDown(start - 1)) % 10007;

		return memoi[start];
	}
}
