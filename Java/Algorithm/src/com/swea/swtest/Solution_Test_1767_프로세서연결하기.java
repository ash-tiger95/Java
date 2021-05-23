package com.swea.swtest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_Test_1767_프로세서연결하기 {

	static int T, N, Ans;
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
					if (i != 0 && i != N - 1 && j != 0 && j != N - 1) { // 가장자리는 제외하고 core를 저장한다.
						if (map[i][j] == 1) {
							list.add(new int[] { i, j });
						}
					}
				}
			}

			Ans = Integer.MAX_VALUE;
			dfs(0, new boolean[N][N], 0);

			sb.append("#"+t+" ").append(Ans).append("\n");

		} // for T
		System.out.println(sb);

	}

	private static void dfs(int idx, boolean[][] visited, int len) {
		if(idx == list.size()) {
			Ans = Math.min(Ans, len);
			
			
			
			return;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(visited[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("---");

		int[] cp = list.get(idx); // 좌표 하나 추출해서

		for (int d = 0; d < 4; d++) { // 상, 하, 좌, 우 순서대로 방문처리 한다.

			int value = 0;

			while (true) {
				int ny = cp[0] + dirs[d][0] * value;
				int nx = cp[1] + dirs[d][1] * value;

				if (!boundary(ny, nx)) {
					break;
				}

				if (visited[ny][nx]) { // 방문한 좌표 == 전선이 겹친 경우
					break;
				}

				visited[ny][nx] = true;
				value++;
				len++;
			}
			
			

			if (idx + 1 != list.size()) { // 마지막 좌표인지 판별
				dfs(idx + 1, visited, len);
			} else {
				Ans = Math.min(Ans, len);
			}
		}

	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
