package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Test_5656_벽돌깨기_DFS {
	static class Brick {
		int y, x, pow;

		public Brick(int y, int x, int pow) {
			super();
			this.y = y;
			this.x = x;
			this.pow = pow;
		}

		@Override
		public String toString() {
			return "Brick [y=" + y + ", x=" + x + ", pow=" + pow + "]";
		}
	}

	static int T, N, W, H, brickCnt, Ans;
	static int[][] map;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 구슬 개수, 1 <= N <= 4
			W = Integer.parseInt(st.nextToken()); // 가로, 2 <= W <= 12
			H = Integer.parseInt(st.nextToken()); // 세로, 2 <= H <= 15
			brickCnt = 0;

			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						brickCnt++;
					}
				}
			}

			Ans = brickCnt;

			dropMarble(N, brickCnt, map);
			System.out.println("#" + t + " " + Ans);
		}

	}

	private static void dropMarble(int n, int brickCnt, int[][] map) {
		if (n == 0) {
			Ans = Math.min(Ans, brickCnt);
			return;
		}

		for (int w = 0; w < W; w++) {
			// 1. Map을 복사해서 사용한다.
			int[][] clonedMap = cloneMap(map);

			// 2. 첫번째 벽돌을 선택한다.
			Brick first = getFirstBrick(w, clonedMap);
			// 2-1. 벽돌이 null이면 continue;
			if (first == null) {
				continue;
			}

			int broken = crash(first, clonedMap);

			if (broken >= brickCnt) {
				Ans = 0;
				return;
			}

			cleanMap(clonedMap);

			dropMarble(n - 1, brickCnt - broken, clonedMap);
		}
	}

	private static void cleanMap(int[][] clonedMap) {
		for (int w = 0; w < W; w++) {
			for (int h = H - 1, nh = H - 1; h >= 0; h--) {
				if (clonedMap[h][w] != 0) {
					int temp = clonedMap[h][w];
					clonedMap[h][w] = 0;
					clonedMap[nh--][w] = temp;
				}
			}
		}

	}

	private static int crash(Brick first, int[][] clonedMap) {
		int broken = 0;

		// 맞은 돌 0으로 바꾸고 깨진 개수 1증가
		clonedMap[first.y][first.x] = 0;
		broken++;

		// pow만큼 깨기
		for (int p = 1; p < first.pow; p++) {
			for (int d = 0; d < 4; d++) {
				int ny = first.y + dirs[d][0] * p;
				int nx = first.x + dirs[d][1] * p;

				if (!boundary(ny, nx)) {
					continue;
				}

				if (clonedMap[ny][nx] != 0) {
					broken += crash(new Brick(ny, nx, clonedMap[ny][nx]), clonedMap); // 계속 벽돌 뿌시기
				}
			}
		}
		return broken;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < H && nx < W;
	}

	private static Brick getFirstBrick(int w, int[][] clonedMap) {
		for (int h = 0; h < H; h++) {
			if (clonedMap[h][w] != 0) { // 벽돌일 때,
				return new Brick(h, w, clonedMap[h][w]);
			}
		}
		return null;
	}

	private static int[][] cloneMap(int[][] map2) {
		int[][] temp = new int[H][W];
		for (int h = 0; h < H; h++) {
			temp[h] = map2[h].clone(); // deep copy, 내용 복사, 새 객체
			// temp[h] = map[h]; // swallow copy, 단순 레퍼런스 복사
		}
		return temp;
	}
}
