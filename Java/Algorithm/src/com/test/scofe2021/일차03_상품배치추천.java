package com.test.scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 일차03_상품배치추천 {

	static int N, temp, total;
	static int[][] map;
	static boolean[][] possible;
	static int[] answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine()); // 방 크기 N*N, 3 <= N <= 50

		map = new int[N][N]; // 1: 가구를 놓을 수 있는 공간, 0: 불가능
		possible = new boolean[N][N];
		int size1 = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';

				if (map[i][j] == 1) { // size1짜리 먼저 구하기
					size1++;
					possible[i][j] = true;
				}
			}
		}

		// 1*1, 2*2, ... 등 정사각형 가구 배치 경우의 수 구하기

		answer = new int[N + 1];
		answer[1] = size1;
		total = size1;

		for (int n = 2; n <= N; n++) { // size2부터 구하자
			temp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) { // possible: 2가 불가능 한데 3이 가능하겠어? 효율성 높이자.
						check(i, j, n);
					}
				}
			}

			answer[n] = temp;
			total += temp;

		}

		sb.append("total: ").append(total).append("\n");

		for (int i = 1; i <= N; i++) {
			if (answer[i] != 0) {
				sb.append("size[").append(i).append("]: ").append(answer[i]).append("\n");
			}
		}

		System.out.println(sb);

	}

	private static void check(int sy, int sx, int size) { // 몇 개의 가구를 놓을 수 있는지 검사

		boolean flag = false;
		if (sy + size - 1 >= N || sx + size - 1 >= N) {
			
			return;
		}

		for (int i = sy; i < sy + size ; i++) {
			for (int j = sx; j < sx + size ; j++) {
				if (map[i][j]==0) {
					flag = true;
//					possible[sy][sx] = false;
					return;
				}
			}
		}

		if (!flag) {
			temp++;
		}

	}
}
