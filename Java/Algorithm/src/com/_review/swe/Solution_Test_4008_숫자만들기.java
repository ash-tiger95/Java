package com._review.swe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_Test_4008_숫자만들기 {
	static int T, N, Ans,Max,Min;
	static int[] number;
	static int[] operation;
	static boolean[] isSelected;
	static int[] output;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			Ans = 0;

			N = Integer.parseInt(br.readLine());

			operation = new int[N - 1];
			int index = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) { // 0 1 2 3
				int operCnt = Integer.parseInt(st.nextToken()); // + - * / 개수
				for (int c = 0; c < operCnt; c++) {
					operation[index] = i;
					index++;
				}
			}
//			System.out.println("operation: " + Arrays.toString(operation));

			number = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
//			System.out.println("number: " + Arrays.toString(number));

			isSelected = new boolean[N - 1];
			output = new int[N - 1];
			
			Max = Integer.MIN_VALUE;
			Min = Integer.MAX_VALUE;
			permutation(0);
			Ans = Max - Min;

			sb.append(Ans).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void permutation(int cnt) { // cnt: 순열을 뽑는 자리(index)
		if (cnt == N - 1) { // R번째이면 끝
//			System.out.println(Arrays.toString(output));
			int result = number[0];

			for (int i = 0; i < N - 1; i++) {
				if (operation[output[i]] == 0) {
					result += number[i + 1];
				} else if (operation[output[i]] == 1) {
					result -= number[i + 1];
				} else if (operation[output[i]] == 2) {
					result *= number[i + 1];
				} else if (operation[output[i]] == 3) {
					result /= number[i + 1];
				}
			}

//			System.out.println("result: " + result);

			Max = Math.max(Max, result);
			Min = Math.min(Min, result);
		}

		// 해당 자리에 뽑을 가능한 모든 수에 대해 시도(앞자리까지 선택된 수 배제)
		for (int i = 0; i < N - 1; i++) {
			if (isSelected[i]) {
				continue;
			}
			output[cnt] = i;
			isSelected[i] = true;
			permutation(cnt + 1); // 다음자리의 순열 뽑기
			isSelected[i] = false;
		}

	}
}
