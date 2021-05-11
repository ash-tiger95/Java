package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main_Silver1_2667_단지번호붙이기 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		int apartment = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					list.add(bfs(++apartment, i, j));
				}
			}
		}

		System.out.println(apartment);
//		Collections.sort(list);
		list.sort(null);
		for (Integer i : list) {
			System.out.println(i);
		}

	}

	private static int bfs(int apartment, int sy, int sx) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { sy, sx });
		visited[sy][sx] = true;
		int cnt = 1;

		while (!queue.isEmpty()) {
			int[] cp = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];
				if (!boundary(ny, nx)) {
					continue;
				}
				if (!visited[ny][nx] && map[ny][nx] == 1) {
					queue.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
					cnt++;
				}

			}
		}

		return cnt;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
