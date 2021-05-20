package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이) 완탐
 * 
 * @author jugia
 *
 */
public class Main_Gold5_7682_틱택토 {

	static int xCnt, oCnt, xPossible, oPossible;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String in = br.readLine();

			if (in.equals("end")) {
				break;
			} else {
				xCnt = oCnt = xPossible = oPossible = 0;
				map = new char[3][3];

				for (int i = 0; i < 3; i++) {
					map[i] = in.substring(i * 3, (i + 1) * 3).toCharArray();
				}

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (map[i][j] == 'X') {
							xCnt++;
						} else if (map[i][j] == 'O') {
							oCnt++;
						}
					}
				}

				check();

				// x/o의 빙고 개수 판별 + x/o의 개수 판별
				if (xPossible != 0 && oPossible != 0) { // 둘 다 빙고인 경우는 그냥 불가능
					sb.append("invalid").append("\n");

				} else if (xPossible != 0 && oPossible == 0) { // X만 빙고인 경우
					if (xCnt == oCnt + 1) {
						sb.append("valid").append("\n");
					} else {
						sb.append("invalid").append("\n");
					}
				} else if (xPossible == 0 && oPossible != 0) { // o만 빙고인 경우
					if (xCnt == oCnt) { // 개수가 같아야 한다.
						sb.append("valid").append("\n");
					} else {
						sb.append("invalid").append("\n");
					}
				} else { // 둘 다 빙고가 아닌 경우
					if (xCnt == oCnt + 1 && xCnt + oCnt == 9) { // 개수로 판별
						sb.append("valid").append("\n");
					} else {
						sb.append("invalid").append("\n");
					}
				}

			} // end if

		} // end while
		System.out.println(sb);
	}

	private static void check() {
		for (int i = 0; i < 3; i++) { // 가로 검사
			if (map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
				if (map[i][0] == 'X') {
					xPossible++;
				} else if (map[i][0] == 'O') {
					oPossible++;
				}
			}
		}

		for (int i = 0; i < 3; i++) { // 세로 검사
			if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
				if (map[0][i] == 'X') {
					xPossible++;
				} else if (map[0][i] == 'O') {
					oPossible++;
				}
			}
		}

		// 대각선 검사
		if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
			if (map[0][0] == 'X') {
				xPossible++;
			} else if (map[0][0] == 'O') {
				oPossible++;
			}
		}

		if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
			if (map[0][2] == 'X') {
				xPossible++;
			} else if (map[0][2] == 'O') {
				oPossible++;
			}
		}
	}
}
