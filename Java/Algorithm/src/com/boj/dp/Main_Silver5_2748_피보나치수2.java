package com.boj.dp;

import java.util.Scanner;

public class Main_Silver5_2748_피보나치수2 {

	static long[] memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		memo = new long[n+1]; // 0부터 n까지이므로 배열의 총 길이는 n+1

		System.out.println(fibo(n));
	}

	private static long fibo(int i) {
		if (i <= 1) { // 0번 째: 0, 1번 째: 1
			return i;
		}
		else {
			if (memo[i] > 0) { // 값이 저장되어 있으면 값 출력
				return memo[i];
			} else { // 값이 없으면 값을 구한다.
				memo[i] = fibo(i - 2) + fibo(i - 1);
				return memo[i];
			}
		}
	}

}
