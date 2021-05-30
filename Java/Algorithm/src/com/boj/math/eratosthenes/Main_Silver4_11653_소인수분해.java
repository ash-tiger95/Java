package com.boj.math.eratosthenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_Silver4_11653_소인수분해 {

	static int N;
	static boolean[] isPrime;
	static ArrayList<Integer> prime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		// 풀이1. 에라토스테네스의 체, 252ms
		eratosthenes();

		while (N != 1) {
			for (int i = 0; i < prime.size(); i++) {
				if (N % prime.get(i) == 0) {
					sb.append(prime.get(i)).append("\n");
					N /= prime.get(i);
					break;
				}
			}

		}
		System.out.println(sb);

		// 풀이2. sqrt, 132ms
		/*
		for (int i = 2; i <= Math.sqrt(N); i++) {
			while (N % i == 0) {
				sb.append(i).append("\n");
				N /= i;
			}
		}

		if (N != 1) {
			sb.append(N);
		}
		
		System.out.println(sb);
		*/
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

	}
}
