package com.boj.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_14502_연구소 {
	static int N, M, Ans;
	static int[][] map;
	static ArrayList<int[]> virusList = new ArrayList<>();
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Ans = Integer.MAX_VALUE; // 안전 영역 크기의 최댓값 구하기

		map = new int[N][M];
		int wallCnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virusList.add(new int[] { i, j });
				} else if (map[i][j] == 1) {
					wallCnt++;
				}
			}
		}
		makeWall(map, 0);
		System.out.println(N * M - wallCnt - Ans - 3);

	}

	private static void makeWall(int[][] cloneMap, int count) {
		if (count == 3) {
			bfs(cloneMap);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cloneMap[i][j] == 0) {
					cloneMap[i][j] = 1;
					makeWall(cloneMap, count + 1);
					cloneMap[i][j] = 0;
				}
			}
		}

	}

	private static void bfs(int[][] cloneMap) {
		int count = virusList.size();
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < virusList.size(); i++) {
			q.offer(virusList.get(i));
			visited[virusList.get(i)[0]][virusList.get(i)[1]] = true;
		}

		while (!q.isEmpty()) {
			int[] cp = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (cloneMap[ny][nx] == 0 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					q.offer(new int[] { ny, nx });
					count++;
				}
			}
		}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(visited[i][j] +" ");
//			}
//			System.out.println();
//		}
		Ans = Ans > count ? count : Ans; // 바이러스 최솟값 찾기
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
