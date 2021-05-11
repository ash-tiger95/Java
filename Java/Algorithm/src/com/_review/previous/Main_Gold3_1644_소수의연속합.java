package com._review.previous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main_Gold3_1644_소수의연속합 {

	public static boolean[] primeNumcheck;
	private static ArrayList<Integer> primes;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수 구하기

		// 1. N까지의 소수만의 배열을 만든다.
		
		// 1-1. 재귀로 소수 만들기: 22168ms
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 2; i <= N; i++) {
			if (isPrime(i)) {
				list.add(i);
			}
		}
		list.add(0);

		// 1-2. 그냥 소수 만들기: 344ms
		primeNumcheck = new boolean[N + 1];
		primes = new ArrayList<Integer>();

		primeNumcheck[0] = primeNumcheck[1] = true;
		for (int i = 2; i * i <= N; i++) {
			if (!primeNumcheck[i]) {
				for (int j = i * i; j <= N; j += i) {
					primeNumcheck[j] = true;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (!primeNumcheck[i]) {
				primes.add(i);
			}
		}
		primes.add(0);

		// 2. 투 포인터
		int s = 0, e = 0, sum = 0;
		int Ans = 0;
		while (e <= list.size() - 1) {
			if (sum >= N) {
				sum -= list.get(s++);
			} else {
				sum += list.get(e++);
			}

			if (sum == N) {
				Ans++;
			}
		}
		System.out.println(Ans);
	}

	public static boolean isPrime(int n) { // 재귀로 소수 구하기: 2168ms
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
