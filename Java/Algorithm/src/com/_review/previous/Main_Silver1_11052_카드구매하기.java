package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver1_11052_카드구매하기 {
	static int N;
	static int[] p;
	static int[] memoi;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 민규가 구매하려는 카드 개수
		p = new int[N + 1]; // 카드 가격
		memoi = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				memoi[i] = Math.max(memoi[i], memoi[i - j] + p[j]);
			}
		}
//		System.out.println(Arrays.toString(memoi));
		System.out.println(memoi[N]);
	}
}
