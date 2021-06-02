package com.boj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver3_1676_팩토리얼0의개수 {

	static long N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 0 <= N <= 500

		int count = 0;

		while (N >= 5) { // N! 뒤에서부터 0이 아닌 숫자가 나올 때까지의 0의 개수
			count += N / 5;
			N /= 5; // 5가 여러번 쓰인 모양일 때를 위해
		}

		System.out.println(count);
	}
}
