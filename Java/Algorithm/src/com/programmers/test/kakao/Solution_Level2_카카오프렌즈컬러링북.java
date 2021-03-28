package com.programmers.test.kakao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_Level2_카카오프렌즈컬러링북 {

	static int numberOfArea, maxSizeOfOneArea, N, M;

	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Node {
		int y, x, v; // y좌표, x좌표, picture[y][x]값

		public Node(int y, int x, int v) {
			super();
			this.y = y;
			this.x = x;
			this.v = v;
		}
	}

	public static int[] solution(int m, int n, int[][] picture) {
		N = m;
		M = n;
		numberOfArea = 0;
		maxSizeOfOneArea = 0;

		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && picture[i][j] != 0) { // 방문한 적 없고 0이 아니면
					visited[i][j] = true;
					bfs(i, j, picture);
				}
			}
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea; // 영역 개수
		answer[1] = maxSizeOfOneArea; // 영역 중 가장 큰 영역

		return answer;
	}

	private static void bfs(int sy, int sx, int[][] map) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(sy, sx, map[sy][sx]));

		int area = 1;

		while (!q.isEmpty()) {
			Node cn = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] == cn.v) {
					q.offer(new Node(ny, nx, cn.v));
					visited[ny][nx] = true; // Queue에 넣을 때 방문처리!
					area++;
				}
			}
		}

		numberOfArea++;
		maxSizeOfOneArea = Math.max(maxSizeOfOneArea, area); // 최대 영역크기 업데이트

	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

	public static void main(String[] args) {
		int m = 6; // 세로, 1 <= m <= 100
		int n = 4; // 가로, 1 <= n <= 100
		int[][] picture = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 },
				{ 0, 0, 0, 3 } };

		System.out.println(Arrays.toString(solution(m, n, picture)));
	}
}
