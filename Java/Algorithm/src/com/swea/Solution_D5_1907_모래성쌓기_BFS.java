package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_1907_모래성쌓기_BFS {

	static int T, H, W, answer;
	static int[][] dir = { { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
	static int[][] map;
	static Queue<int[]> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			queue = new LinkedList<>();

			// Point! 바다를 기준으로 깎아나가자
			for (int i = 0; i < H; i++) {
				String input = br.readLine();
				for (int j = 0; j < W; j++) {
					if (input.charAt(j) == '.') {
						queue.add(new int[] { i, j });
					} else {
						map[i][j] = input.charAt(j)-'0';
					}
				}
			}
			
			while (!queue.isEmpty()) {
				answer++; // 1초일 때
				int size = queue.size();
				for (int s = 0; s < size; s++) {
					
					int[] c = queue.poll();
					
					for (int d = 0; d < 8; d++) {
						int ny = c[0] + dir[d][0];
						int nx = c[1] + dir[d][1];

						if (!boundary(ny, nx)) {
							continue;
						}
						if (map[ny][nx] != 0) {
							map[ny][nx]--;
							if(map[ny][nx] == 0) {
								queue.add(new int[] {ny,nx});
							}
						}
					}
					
				}
				

			}
			System.out.println("#"+t+" "+(answer-1));

		}

	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < H && nx < W;
	}
}
