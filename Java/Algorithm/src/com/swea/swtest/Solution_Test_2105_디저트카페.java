package com.swea.swtest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Test_2105_디저트카페 {

	static int T, N, Ans;
	static int[][] map;
	static int[][] dirs = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\2105.txt")));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Ans = -1;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, i, j, 0, new boolean[101], 1);
				}
			}

			sb.append("#" + t + " " + Ans + "\n");
		} // for T

		System.out.println(sb);
	}

	private static void dfs(int sy, int sx, int y, int x, int dir, boolean[] dessert, int dessertCnt) {
		dessert[map[y][x]] = true; // 최초 시작하는 좌표의 디저트를 true 처리하기 위해

		if (dir > 3) {
			return;
		}

		int ny = y + dirs[dir][0];
		int nx = x + dirs[dir][1];

		if (!boundary(ny, nx)) {
			return;
		}

		if (ny == sy && nx == sx) { // 시작한 점으로 돌아오면 종료
			Ans = Math.max(Ans, dessertCnt);
			return;
		}

		if (dessert[map[ny][nx]]) { // 이미 먹은 디저트인 경우 return
			return;
		} else {
			dessert[map[ny][nx]] = true;
			dessertCnt++;
		}

		dfs(sy, sx, ny, nx, dir, dessert, dessertCnt); // 같은 방향으로 계속 이어가는 경우
		dfs(sy, sx, ny, nx, dir + 1, dessert, dessertCnt); // 방향을 바꾸는 경우

		dessert[map[ny][nx]] = false; // ★ 방문해제 처리
		return;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

}
