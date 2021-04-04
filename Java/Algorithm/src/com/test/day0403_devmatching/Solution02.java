package com.test.day0403_devmatching;

import java.util.Arrays;

public class Solution02 { // 성공

	static int[][] map;
	static int[] answer;

	public static int[] solution(int rows, int columns, int[][] queries) {
		answer = new int[queries.length]; // 회전에 의해 위치가 바뀐 숫자들 중 가자 ㅇ작은 숫자들을 순서대로 출력

		map = new int[rows][columns];
		int v = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				map[i][j] = v++;
			}
		}

		// 시계방향 회전
		for (int t = 0; t < queries.length; t++) {
			answer[t] = rotate(queries[t][0] - 1, queries[t][1] - 1, queries[t][2] - 1, queries[t][3] - 1);
		}
		System.out.println(Arrays.toString(answer));

		return answer;
	}

	private static int rotate(int sy, int sx, int ey, int ex) {
		int min = Integer.MAX_VALUE;
		
		int temp = map[sy][ex];
		min = Math.min(min, temp);
		for (int i = ex; i > sx; i--) {
			map[sy][i] = map[sy][i - 1];
			min = Math.min(min, map[sy][i - 1]);
		}

		int temp2 = map[ey][ex];
		min = Math.min(min, temp2);
		for (int i = ey; i > sy; i--) {
			map[i][ex] = map[i - 1][ex];
			min = Math.min(min, map[i - 1][ex]);
		}
		map[sy + 1][ex] = temp;
		min = Math.min(min, temp);

		int temp3 = map[ey][sx];
		for (int i = sx ; i < ex; i++) {
			map[ey][i] = map[ey][i + 1];
			min = Math.min(min, map[ey][i + 1]);
		}
		map[ey][ex-1] = temp2;
		min = Math.min(min, temp2);

		for(int i=sy;i<ey;i++) {
			map[i][sx] = map[i+1][sx];
			min = Math.min(min, map[i+1][sx]);
		}
		map[ey-1][sx] = temp3;
		min = Math.min(min, temp3);
		
		return min;
	}

	private static void print() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/*
	 * rows	columns	queries										result
		6	6		[[2,2,5,4],[3,3,6,6],[5,1,6,3]]				[8, 10, 25]
		3	3		[[1,1,2,2],[1,2,2,3],[2,1,3,2],[2,2,3,3]]	[1, 1, 5, 3]
		100	97		[[1,1,100,97]]								[1]
	 */
	public static void main(String[] args) {
		int rows = 6;
		int columns = 6;
		int[][] queries = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } }; // {y1,x1,y2,x2}

		System.out.println(solution(rows, columns, queries));
	}
}
