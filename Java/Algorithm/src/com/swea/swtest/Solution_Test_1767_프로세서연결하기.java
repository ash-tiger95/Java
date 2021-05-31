package com.swea.swtest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_Test_1767_프로세서연결하기 {

	static int T, N, maxCnt, minLen;
	static int[][] map;
	static ArrayList<int[]> list;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\1767.txt")));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i != 0 && i != N - 1 && j != 0 && j != N - 1) { // 가장자리는 제외하고 core를 저장한다.
							list.add(new int[] { i, j });
						}
					}
				}
			}

			maxCnt = Integer.MIN_VALUE; // 최대 코어 개수
			minLen = Integer.MAX_VALUE; // 최소 전선 길이
			dfs(0, 0, 0);

			sb.append("#" + t + " ").append(minLen).append("\n");
		} // for T

		System.out.println(sb);
	}

	private static void dfs(int idx, int coreCnt, int len) {
		if (idx == list.size()) {
			if (coreCnt > maxCnt) {
				minLen = len;
				maxCnt = coreCnt;
			} else if (coreCnt == maxCnt) {
				if (len < minLen) {
					minLen = len;
				}
			}

			return;
		}

		int[] cp = list.get(idx); // 좌표 하나 추출해서

		for (int d = 0; d < 4; d++) {
			int count = 0;
			int originY = cp[0];
			int originX = cp[1];

			int ny = cp[0];
			int nx = cp[1];

			while (true) { // 1. 먼저 방향순으로 방문 가능한지 검토한다.
				ny += dirs[d][0];
				nx += dirs[d][1];

				if (!boundary(ny, nx)) {
					break;
				}

				if (map[ny][nx] == 1) { // 이 방향으로 가면 안될 때
					count = 0; 
					break;
				}

				count++;
			}

			for (int i = 0; i < count; i++) { // 2. 방문 가능한 곳이면 방문처리
				originY += dirs[d][0];
				originX += dirs[d][1];

				map[originY][originX] = 1;
			}

			if (count == 0) {
				dfs(idx + 1, coreCnt, len);
			} else {
				dfs(idx + 1, coreCnt + 1, len + count);

				// 3. 한번 돌았으면 미방문 상태로 초기화
				originY = cp[0];
				originX = cp[1];
				for (int i = 0; i < count; i++) {
					originY += dirs[d][0];
					originX += dirs[d][1];

					map[originY][originX] = 0;
				}
			}

		} // for dirs
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
