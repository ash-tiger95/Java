package com.boj.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold5_17144_미세먼지안녕 {
	static int R, C, T, ac1y, ac2y, x1, x2, Ans;
	static int[][] map, temp;
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int cnt = 0;

		map = new int[R][C];
		temp = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (cnt == 0 && map[i][j] == -1) {
					ac1y = i; // 반시계 방향
					x1 = j;
					temp[i][j] = -1;
					cnt++;
				} else if (cnt == 1 && map[i][j] == -1) {
					ac2y = i; // 시계 방향
					x2 = j;
					temp[i][j] = -1;
				}
			}
		}

		for (int t = 0; t < T; t++) {
//			printArr(map);
//			System.out.println(" 먼지확산---");
			spreadDust(); // 먼지 확산
//			printArr(temp);
//			System.out.println("-------------------");
//			System.out.println("공기 이동----");
			moveArea(); // 공기 이동
//			printArr(temp);
			for (int i = 0; i < R; i++) {
				map[i] = temp[i].clone();
			}
			for (int i = 0; i < R; i++) {
				Arrays.fill(temp[i], 0);
			}
			temp[ac1y][x1] = -1;
			temp[ac2y][x2] = -1;
//			System.out.println("-------------------");
//			printArr(map);
		}
		Ans = 0;
		count();
		System.out.println(Ans);
	}

	private static void count() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) {
					Ans += map[i][j];
				}
			}
		}
	}

	private static void printArr(int[][] arr) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void moveArea() {
		// 반시계
		// 왼 -> 우
		int tmp1 = 0;
		int tmp2 = 0;
		for (int i = C - 1; i > 0; i--) { // 0번째는 공기청전기
			if (i == C - 1) {
				tmp1 = temp[ac1y][i];
				continue;
			}
			temp[ac1y][i + 1] = temp[ac1y][i];
		}
		temp[ac1y][1] = 0;
		for (int i = 0; i < ac1y; i++) {
			if (i == 0) {
				tmp2 = temp[0][C - 1];
				temp[i][C - 1] = temp[i + 1][C - 1];
				continue;
			}
			if (i == ac1y - 1) {
				temp[i][C - 1] = tmp1;
				continue;
			}
			temp[i][C - 1] = temp[i + 1][C - 1];
		}
		for (int i = 0; i < C - 1; i++) {
			if (i == 0) {
				tmp1 = temp[0][0];
				temp[0][i] = temp[0][i + 1];
				continue;
			}
			if (i == C - 2) {
				temp[0][i] = tmp2;
				continue;
			}
			temp[0][i] = temp[0][i + 1];
		}
		for (int i = ac1y; i > 0; i--) {
			if (i == ac1y) {
				continue;
			}
			if (i == 1) {
				temp[i][0] = tmp1;
				continue;
			}
			temp[i][0] = temp[i-1][0];
		}
		// 시계
		tmp1 = 0;
		tmp2 = 0;
		for (int i = C - 1; i > 0; i--) { // 0번째는 공기청전기

			if (i == C - 1) {
				tmp1 = temp[ac2y][i];
				continue;
			}
			temp[ac2y][i + 1] = temp[ac2y][i];
		}
		temp[ac2y][1] = 0;
		for (int i = R - 1; i > ac2y; i--) {
			if (i == R - 1) {
				tmp2 = temp[i][C - 1];
				temp[i][C - 1] = temp[i - 1][C - 1];
				continue;
			}
			if (i == ac2y + 1) {
				temp[i][C - 1] = tmp1;
				continue;
			}
			temp[i][C - 1] = temp[i - 1][C - 1];
		}
		for (int i = 0; i < C - 1; i++) {
			if (i == 0) {
				tmp1 = temp[R - 1][0];
				temp[R - 1][i] = temp[R - 1][i + 1];
				continue;
			}
			if (i == C - 2) {
				temp[R - 1][i] = tmp2;
				continue;
			}
			temp[R - 1][i] = temp[R - 1][i + 1];
		}
		for (int i = ac2y + 1; i < R - 1; i++) {

			if (i == R - 2) {
				temp[i][0] = tmp1;
				continue;
			}
			temp[i][0] = temp[i + 1][0];
		}
	}

	private static void spreadDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) {
					int value = map[i][j] / 5;
					int possibleArea = 4;
					for (int d = 0; d < 4; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];

						if (!boundary(ny, nx) || map[ny][nx] == -1) {
							possibleArea--;
							continue;
						}
						temp[ny][nx] += value;

					}
					temp[i][j] += map[i][j] - value * possibleArea;
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
