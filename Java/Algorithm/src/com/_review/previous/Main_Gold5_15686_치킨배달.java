package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_15686_치킨배달 {
	static class Chicken {
		int y;
		int x;
		int dist;

		public Chicken(int y, int x, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Chicken [y=" + y + ", x=" + x + "]";
		}

	} 
	static int N, M, chickenCount, houseCount, Ans;
	static int[][] map;
	static ArrayList<Chicken> list;

	// nCr을 위한 변수
	static int[] number;

	// bfs()를 위한 변수
	static Queue<Chicken> queue;
	static int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] visited;
	static int[][] distance;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력
		N = Integer.parseInt(st.nextToken()); // 지도 크기
		M = Integer.parseInt(st.nextToken()); // 치킨집 개수

		// 초기화
		map = new int[N][N];
		list = new ArrayList<>();
		houseCount = 0;
		Ans = Integer.MAX_VALUE;

		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Chicken(i, j, 0));
				} else if (map[i][j] == 1) {
					houseCount++; // 입력된 집의 개수를 알기 위함
				}
			}
		}

		number = new int[list.size()];
		nCr(0, 0);

		System.out.println(Ans);

	}

	private static void nCr(int cnt, int cur) { // chickenCount(치킨집 총 개수)에서 M개의 치킨집을 뽑는다.
		if (cnt == M) {
			queue = new LinkedList<Chicken>();
			visited = new boolean[N][N];
			distance = new int[N][N];
			result = 0;

			for (int i = 0; i < M; i++) {
				int cy = list.get(number[i]).y;
				int cx = list.get(number[i]).x;
				queue.offer(new Chicken(cy, cx, 0));
				visited[cy][cx] = true;
				distance[cy][cx] = 1;
			}
			Ans = Math.min(Ans, bfs());

			return;
		}
		for (int i = cur; i < list.size(); i++) {
			number[cnt] = i;
			nCr(cnt + 1, i + 1);
		}
	}

	private static int bfs() {
		while (!queue.isEmpty()) {
			Chicken current = queue.poll();
			int cy = current.y;
			int cx = current.x;
			int cd = current.dist;
			for (int d = 0; d < direction.length; d++) {
				int ny = cy + direction[d][0];
				int nx = cx + direction[d][1];
				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx]) {
					visited[ny][nx] = true;
					distance[ny][nx] = cd + 1;
					queue.offer(new Chicken(ny, nx, cd + 1));
					if(map[ny][nx] == 1) {
						result += cd + 1;
					} 
				} 
			}
		}
		return result;
	}

	private static boolean boundary(int cy, int cx) {
		return cy >= 0 && cy < N && cx >= 0 && cx < N;
	}
}
