package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이) BFS
 * @author jugia
 *
 */
public class Main_Gold1_15806_영우의기숙사청소 { // 도대체 어디서 nullpointerException인지 모르겟다....

	static int N, M, K, T;
	static boolean[][] map; // 가장 마지막 일차 곰팡이 map
	static ArrayList<int[]> mold;
	static ArrayList<int[]> check;
	static Queue<int[]> q;
	static int[][] dirs = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정사각형 방의 크기 N*N, 1 <= N <= 300
		M = Integer.parseInt(st.nextToken()); // 곰팡이 개수, 0 <= M <= N^2
		K = Integer.parseInt(st.nextToken()); // 검사할 방바닥 좌표 개수, 0 <= K <= N^2
		T = Integer.parseInt(st.nextToken()); // 청소 검사가 실시되기까지 남은 일수, 1 <= T <= 10,000

		map = new boolean[N][N];
		mold = new ArrayList<>(); // 곰팡이 좌표
		check = new ArrayList<>(); // 검사할 방바닥 좌표
		q = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			mold.add(new int[] { b, a });
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			check.add(new int[] { b, a });
		}

		if (K == 0) { // 검사하는 좌표가 없는 경우
			System.out.println("NO");
		} else if (M == 0) { // 곰팡이가 없는 경우
			System.out.println("NO");
		} else {
			q.addAll(mold);

			for (int t = 0; t < T; t++) { // T일 후
				if (t == T - 1) { // 곰팡이 번식 마지막 날인 경우
					bfs(true);
				} else {
					bfs(false);
				}
			}

			System.out.println();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + "\t");
				}
				System.out.println();
			}

			boolean hasClean = false; // 청소해야 되는지 검사
			for (int[] cp : check) {
				if (map[cp[0]][cp[1]]) {
					System.out.println("YES");
					hasClean = true;
					break;
				}
			}

			if (!hasClean) {
				System.out.println("NO");
			}
		}
	}

	private static void bfs(boolean isFinalDay) {
		int size = 0;
		size = q.size();

		for (int i = 0; i < size; i++) {
			int[] cp = q.poll();

			for (int d = 0; d < 8; d++) {
				int ny = cp[0] + dirs[d][0];
				int nx = cp[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (isFinalDay) { // 청소 검사 날짜인 경우
					map[ny][nx] = true;
				} else {
					q.add(new int[] { ny, nx });
				}

			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
