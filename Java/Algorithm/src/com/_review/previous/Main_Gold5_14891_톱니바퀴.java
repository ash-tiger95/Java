package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_14891_톱니바퀴 {

	static int[][] gear;
	static int T;
	static int[][] rotate;
	static int[] isValid;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		gear = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = input.charAt(j) - '0';
			}
		}

		T = Integer.parseInt(br.readLine());
		rotate = new int[T][2]; // 톱니바퀴 번호(0번~3번), 회전방향(1: 시계, -1: 반시계)
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int wheelNum = Integer.parseInt(st.nextToken()) - 1; // 톱니바퀴 번호
			int dir = Integer.parseInt(st.nextToken()); // 회전방향

			isValid = new int[4];
			check(wheelNum, dir);
			rotate(isValid);

		}

		System.out.println(calc());

	}

	static void check(int wheelNum, int dir) {
		isValid[wheelNum] = dir; // 톱니 회전 방향 저장

		int prev = wheelNum - 1;
		int next = wheelNum + 1;

		if (prev >= 0 && isValid[prev] == 0) { // 왼쪽 바퀴 검사
			if (gear[prev][2] != gear[wheelNum][6]) {
				check(prev, dir * -1);
			}
		}

		if (next <= 3 && isValid[next] == 0) { // 오른쪽 바퀴 검사
			if (gear[next][6] != gear[wheelNum][2]) {
				check(next, dir * -1);
			}
		}
	}

	static void rotate(int[] isValid) {
		for (int i = 0; i < 4; i++) {
			if (isValid[i] != 0) { // 회전해야 하는 톱니일 경우
				int[] temp = new int[8];

				int idx;
				for (int j = 0; j < 8; j++) {
					idx = j + isValid[i];

					if (idx == -1) {
						idx = 7;
					} else if (idx == 8) {
						idx = 0;
					}

					temp[idx] = gear[i][j];
				}

				gear[i] = temp;
			}
		}
	}

	static int calc() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			int num = gear[i][0];

			if (num == 1) {
				sum += Math.pow(2, i);
			}
		}
		return sum;
	}
}
