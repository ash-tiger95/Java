package com._review.previous;

import java.util.Scanner;

public class Main_Gold5_9663_NQueen {

	static int N, count;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		count = 0;
		arr = new int[N];

		nQueen(0);
		System.out.println(count); // 경우의 수

	}

	private static void nQueen(int depth) {
		if (depth == N) {
			count++;
			return;
		}

		for (int i = 0; i < N; i++) {
			arr[depth] = i;
			if (isPossible(depth)) {
				nQueen(depth + 1);
			}
		}
	}

	private static boolean isPossible(int col) {

		for (int i = 0; i < col; i++) {
			
			if (arr[col] == arr[i]) { // 해당 열의 행과 i열의 행이 일치할 경우(같은 행에 존재할 경우)
				return false;
			} else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) { // 대각선상에 놓여있는 경우 (열의 차와 행의 차가 같을 경우)
				return false;
			}

		}
		return true;
	}

}
