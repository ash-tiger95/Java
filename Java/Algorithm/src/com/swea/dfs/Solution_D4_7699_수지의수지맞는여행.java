package com.swea.dfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_7699_수지의수지맞는여행 {

	static int T, R, C, Ans;
	static char[][] map;
	static boolean[] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\7699.txt")));

//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			Ans = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new char[R][C];
			visited = new boolean[26];
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
			}
			visited[map[0][0] - 'A'] = true;

			dfs(0, 0, 1);

			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int cy, int cx, int count) {

		Ans = Math.max(Ans, count);

		if (count == 26) {
			return;
		}

		for (int d = 0; d < 4; d++) {
			int ny = cy + dirs[d][0];
			int nx = cx + dirs[d][1];

			if (!boundary(ny, nx)) {
				continue;
			}

			if (!visited[map[ny][nx] - 'A']) {
				visited[map[ny][nx] - 'A'] = true;
				dfs(ny, nx, count + 1);
				visited[map[ny][nx] - 'A'] = false;
			}

		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}

}
