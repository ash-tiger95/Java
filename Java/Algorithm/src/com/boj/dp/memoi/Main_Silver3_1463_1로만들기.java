package com.boj.dp.memoi;

import java.util.Scanner;

public class Main_Silver3_1463_1로만들기 {
	static int N;
	static int[] memoi;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		memoi = new int[N + 1];

//		System.out.println(topDown(N));
		bottomUp();
		System.out.println(memoi[N]);
	}

	// Top-Down: 61140KB, 388ms
	// 재귀함수 사용, 작은 부분의 답을 저장해 이미 계산을 진행한 작은문제는 저장된 값을 이용하는 방법
	private static int topDown(int start) {
		if (start <= 1) {
			return 0;
		}

		if (memoi[start] > 0) {
			return memoi[start];
		}

		// 가장 오래 걸리는 순서
		memoi[start] = topDown(start - 1) + 1;

		// 업데이트
		if (start % 2 == 0) {
			int tmp = topDown(start / 2) + 1;
			if (memoi[start] > tmp) {
				memoi[start] = tmp;
			}
		}

		// 업데이트
		if (start % 3 == 0) {
			int tmp = topDown(start / 3) + 1;
			if (memoi[start] > tmp) {
				memoi[start] = tmp;
			}
		}

		return memoi[start];
	}

	// BottomUp: 22260KB, 248ms
	// 0일 때부터 시작해서 입력한 값의 답을 찾을때까지 무한루프를 도는 방식
	private static void bottomUp() {
		memoi[0] = memoi[1] = 0;
		for (int i = 2; i < N + 1; i++) {
			memoi[i] = memoi[i - 1] + 1;

			if (i % 2 == 0) {
				memoi[i] = Math.min(memoi[i], memoi[i / 2] + 1);
			}
			if (i % 3 == 0) {
				memoi[i] = Math.min(memoi[i], memoi[i / 3] + 1);
			}
		}
	}
}
