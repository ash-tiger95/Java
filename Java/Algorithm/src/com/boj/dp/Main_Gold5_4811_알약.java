package com.boj.dp;

import java.util.Scanner;

public class Main_Gold5_4811_알약 {

	static int N;
	static long memoi[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			N = sc.nextInt(); // 약의 개수, N <= 30

			if (N == 0) {
				break;
			}
			memoi = new long[1000][1000];

			System.out.println(recur(0, N)); // Top-Down 방식

		}
	}

	private static long recur(int halfCnt, int oneCnt) {// 짤라먹은 횟수, 덩어리로 먹은 횟수
		if (halfCnt == 1 && oneCnt == 0) { // 기저 조건
			return 1;
		}

		if (memoi[halfCnt][oneCnt] != 0) {
			return memoi[halfCnt][oneCnt];
		}

		if (halfCnt != 0) { // 한 개 먹은 알약이 남아 있을 때
			memoi[halfCnt][oneCnt] = memoi[halfCnt][oneCnt] + recur(halfCnt - 1, oneCnt);
		}

		if (oneCnt != 0) { // 덩어리 알약이 남아 있을 때
			memoi[halfCnt][oneCnt] = memoi[halfCnt][oneCnt] + recur(halfCnt + 1, oneCnt - 1);
		}

		return memoi[halfCnt][oneCnt];
	}

}
