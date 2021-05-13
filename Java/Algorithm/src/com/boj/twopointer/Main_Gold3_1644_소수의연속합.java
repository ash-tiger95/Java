package com.boj.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 1. Eratosthenes로 소수 구하기
 * 2. Two Pointer
 * 
 * @author jugia
 *
 */
public class Main_Gold3_1644_소수의연속합 {

	static int N, Ans;
	static boolean[] isPrime;
	static ArrayList<Integer> prime;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Ans = 0;

		N = Integer.parseInt(br.readLine()); // 소수의 연속합으로 N 만들기

		eratosthenes(); // make Prime
		
		System.out.println(prime);

		int start = 0;
		int end = 0;
		long sum = 0;

		while (end <= prime.size()-1) {
			if (sum >= N) {
				sum -= prime.get(start++);
			} else {
				sum += prime.get(end++);
			}

			if (sum == N) {
				Ans++;
			}
		}

		System.out.println(Ans);

	}

	private static void eratosthenes() {
		isPrime = new boolean[N + 1];

		// prime[0]과 prime[1]은 소수가 아니다.
		isPrime[0] = isPrime[1] = true;

		for (int i = 2; i * i <= N; i++) { // 2부터 자기 자신을 제외한 배수를 제외시킨다.
			if (!isPrime[i]) {
				for (int j = i * i; j <= N; j += i) { // 2의 배수, 3의 배수, ...를 지운다.
					isPrime[j] = true;
				}
			}
		}

		// prime[]이 false이면 소수
		prime = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			if (!isPrime[i]) {
				prime.add(i);
			}
		}

		prime.add(0);
	}
}
