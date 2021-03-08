package com.swea.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_8382_방향전환 {
	static int T, x1, y1, x2, y2, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			answer = 0;
			int sub_x = Math.abs(x1 - x2);
			int sub_y = Math.abs(y1 - y2);
			if (sub_x < sub_y) {
				// x의 차이만큼 대각으로 이동
				answer += sub_x * 2;
				// 대각으로 이동한 후 남은 y좌표 거리
				int dist = sub_y - sub_x;
				for (int i = 1; i <= dist; i++) {
					if (i % 2 == 1) {
						answer += 1;
					} else {
						answer += 3;
					}
				}
			} else {
				// x의 차이만큼 대각으로 이동
				answer += sub_y * 2;
				// 대각으로 이동한 후 남은 y좌표 거리
				int dist = sub_x - sub_y;
				for (int i = 1; i <= dist; i++) {
					if (i % 2 == 1) {
						answer += 1;
					} else {
						answer += 3;
					}
				}
			}
			System.out.println("#" + t + " " + answer);
		}
	}
}
