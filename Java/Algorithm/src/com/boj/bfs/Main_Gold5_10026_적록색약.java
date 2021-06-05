package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_10026_적록색약 {

	static int N, Ans, RGBAns;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Node {
		int y, x;
		char color;

		public Node(int y, int x, char color) {
			super();
			this.y = y;
			this.x = x;
			this.color = color;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		visited = new boolean[N][N];
		Ans = RGBAns = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, map[i][j]);
					Ans++;
					RGBAns++;
				}
			}
		}

		System.out.println(Ans + " " + RGBAns);
	}

	private static void bfs(int sy, int sx, char color) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sy, sx });
		visited[sy][sx] = true;

		ArrayList<Node> list = new ArrayList<>();

		while (!q.isEmpty()) {
			int[] cn = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cn[0] + dirs[d][0];
				int nx = cn[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] == color) {
					q.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
				}

				if (!visited[ny][nx]
						&& ((color == 'R' && map[ny][nx] == 'G') || (color == 'G' && map[ny][nx] == 'R'))) {
					list.add(new Node(ny, nx, map[ny][nx])); // RGB를 위해 RG가 연결되는 부분을 모아놓는다.
				}
			}
		}

		for (Node cn : list) {
			if (!visited[cn.y][cn.x]) { // 이어지는 부분 방문 체크
				Ans++;
				bfs(cn.y, cn.x, cn.color);
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
