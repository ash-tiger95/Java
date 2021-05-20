package com.boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이) 시뮬레이션
 * 1. 톱니가 회전하는 방향만을 저장하는 배열 선언 후 설정
 * 2. 톱니 회전시키기
 * 
 * @author jugia
 *
 */
public class Main_Gold5_14891_톱니바퀴 {

	static int K;
	static int[][] gear; // 톱니의 상태
	static int[] dirs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		gear = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String in = br.readLine();

			for (int j = 0; j < 8; j++) {
				gear[i][j] = in.charAt(j) - '0';
			}
		}

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken()) - 1; // 움직이는 톱니 번호, 0번 ~ 4번
			int dir = Integer.parseInt(st.nextToken()); // 1: 시계방향, -1: 반시계방향

			// 1. 톱니가 회전할 방향 설정
			dirs = new int[4]; // 이번 턴에 톱니가 회전하는 방향 설정
			dirs[num] = dir;
			setLeft(num - 1, dir);
			setRight(num + 1, dir);

			// 2. 톱니 회전시키기
			for (int d = 0; d < 4; d++) {
				if (dirs[d] == 1) { // 시계 방향 회전
					int temp = gear[d][7];
					for (int g = 7; g >= 1; g--) {
						gear[d][g] = gear[d][g - 1];
					}
					gear[d][0] = temp;
				} else if (dirs[d] == -1) { // 반시계 방향 회전
					int temp = gear[d][0];
					for (int g = 0; g < 7; g++) {
						gear[d][g] = gear[d][g + 1];
					}
					gear[d][7] = temp;
				} else {
					continue;
				}
			}

		} // end rotate

		int Ans = 0;
		for (int i = 0; i < 4; i++) { // N극: 0, S극: 1
			if (gear[i][0] == 1) {
				Ans += Math.pow(2, i);
			}
		}
		System.out.println(Ans);

	}

	private static void setLeft(int num, int dir) {
		if (num >= 0) {
			if (gear[num][2] == gear[num + 1][6]) { // 맞닿은 면의 극이 같으면 회전 x
				return;
			} else {
				dirs[num] = dir * (-1);
				setLeft(num - 1, dir * (-1));
			}
		} else {
			return;
		}
	}

	private static void setRight(int num, int dir) {
		if (num < 4) {
			if (gear[num - 1][2] == gear[num][6]) { // 맞닿은 면의 극이 같으면 회전 x
				return;
			} else {
				dirs[num] = dir * (-1);
				setRight(num + 1, dir * (-1));
			}
		} else {
			return;
		}
	}
}
