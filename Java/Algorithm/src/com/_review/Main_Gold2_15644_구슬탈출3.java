package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold2_15644_구슬탈출3 {

	static int R, C, ry, rx, by, bx, ey, ex;
	static char[][] map;
	static int[][] dirs = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } }; // 하 좌 상 우

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();

			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'R') {
					ry = i;
					rx = j;
				} else if (map[i][j] == 'B') {
					by = i;
					bx = j;
				} else if (map[i][j] == 'O') {
					ey = i;
					ex = j;
				}
			}
		}

		for (int d = 0; d < 4; d++) {
			dfs(-1, d + "", -1);
		}

	}

	private static void dfs(int previousDir, String dirs, int ans) {

		boolean finishedRed = false;
		boolean finishedBlue = false;

while(true) {
	
	for (int d = 0; d < 4; d++) {
		if (d == previousDir) {
			continue; // 같은 방향은 패스
		} else {
			char ball = selectBall(d); // 어느 공을 먼저 움직일지 선택

			if (ball == 'r') { // 빨간색 먼저 이동
				finishedRed = move(d, ball);
				finishedBlue = move(d, 'b');

			} else if (ball == 'b') {
				finishedBlue = move(d, ball);
				finishedRed = move(d, 'r');
			} else {
				// 겹치는 일 없을 때는 아무거나 먼저 실행 (상관없다.)
				finishedRed = move(d, 'r');
				finishedBlue = move(d, 'b');
			}
		}

		if (finishedRed && !finishedBlue) {
			break;
		} else if (finishedBlue) {
			
		} else {
			
		}
	}
}
		

	}

	private static boolean move(int cd, char ball) {
		int cy, cx;

		if (ball == 'r') {
			cy = ry;
			cx = rx;
		} else {
			cy = by;
			cx = bx;
		}

		while (true) {
			int ny = cy + dirs[cd][0];
			int nx = cx + dirs[cd][1];

			if (ny == ey && nx == ex) {
				return true;
			}

			if (map[ny][nx] == '.') {
				cy = ny;
				cx = nx;
				continue;
			} else {
				if (ball == 'r') {
					ry = cy;
					rx = cx;
				} else {
					by = cy;
					bx = cx;
				}

				break;
			}
		}

		return false;
	}

	private static char selectBall(int cd) { // 기울였을 때, 빨간공/파란공 중 어느 공을 먼저 옮겨야 하는지 판별
		if (cd == 0) { // 아래쪽으로 기울였을 때
			if (rx == bx) {
				if (ry > by) {
					return 'r'; // 빨간색을 먼저 움직여야 한다는 의미
				} else {
					return 'b'; // 파란색을 먼저 움직여야 한다는 의미
				}
			} else {
				return 'n'; // 아무거나 상관없다는 의미
			}
		} else if (cd == 1) { // 왼쪽으로 기울였을 때
			if (ry == by) {
				if (rx > bx) {
					return 'b';
				} else {
					return 'r';
				}
			} else {
				return 'n';
			}
		} else if (cd == 2) { // 위쪽으로 기울였을 때
			if (rx == bx) {
				if (ry > by) {
					return 'b';
				} else {
					return 'r';
				}
			} else {
				return 'n';
			}
		} else { // 오른쪽으로 기울였을 때
			if (ry == by) {
				if (rx > bx) {
					return 'r';
				} else {
					return 'b';
				}
			} else {
				return 'n';
			}
		}
	}
}
