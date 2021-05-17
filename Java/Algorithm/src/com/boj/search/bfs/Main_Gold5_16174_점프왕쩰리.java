package com.boj.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이) BFS 기본 문제
 * 
 * @author jugia
 *
 */
public class Main_Gold5_16174_점프왕쩰리 {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs() == true ? "HaruHaru" : "Hing");
	}

	private static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0 });
		visited[0][0] = true; // 방문 처리를 하는 이유는 추후에 같은 점에서 출발하는 경우가 발생할 수 있다.

		while (!q.isEmpty()) {
			int[] cp = q.poll();
			int value = map[cp[0]][cp[1]]; // 좌표의 크기만큼 이동 가능

			for (int d = 0; d < 2; d++) {
				int ny = cp[0] + dirs[d][0] * value;
				int nx = cp[1] + dirs[d][1] * value;

				if (!boundary(ny, nx)) {
					continue;
				}

				if (ny == N - 1 && nx == N - 1) { // 쩰리가 끝점에 도달 가능하면 return true
					return true;
				}

				if (!visited[ny][nx]) {
					visited[ny][nx] = true;
					q.add(new int[] { ny, nx });
				}

			}
		}

		return false;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
