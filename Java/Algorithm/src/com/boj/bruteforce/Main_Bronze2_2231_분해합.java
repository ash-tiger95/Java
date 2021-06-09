package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Bronze2_2231_분해합 {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		int ans = 0;
		for (int i = N - (int) (Math.log(N) + 1) * 9; i < N; i++) { // 최소값은 N - N의 자릿수x9
			int num = i;
			int sum = 0;

			while (num != 0) {
				sum += num % 10; // 각 자릿수 확인
				num /= 10;
			}

			if (sum + i == N) {
				ans = i;
				break;
			}
		}

		System.out.println(ans);
	}
}
