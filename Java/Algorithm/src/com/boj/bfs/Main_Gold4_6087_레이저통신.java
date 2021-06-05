package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 내 풀이) 83364KB, 348ms
 * 이전 방향을 가지고 현재 방향과 비교해가며 Queue에 넣을지 판단한다.
 * 단점으로 처음에는 거울 개수가 적을 경우만 넣으면 된다고 생각했는데 같을 경우도 존재한다. 그래서 q에 너무 많이 싸여 느려지는 것 같다.
 * 
 * 다른 풀이) 15268KB, 148ms
 * 처음에는 이렇게 풀라했는데 실패.
 * 시작지점부터 방향이 바뀔때마다 cnt+1을 해가면서 ey, ex를 찾는다.
 * 
 * @author jugia
 *
 */
public class Main_Gold4_6087_레이저통신 {

	static int R, C, sy, sx, ey, ex;
	static char[][] map;
	static int[][] visited;
	static Queue<Node> q;
	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static class Node implements Comparable<Node> {
		int y, x, preDir, cnt;

		public Node(int y, int x, int preDir, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.preDir = preDir;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new int[R][C];
		boolean isFirst = true;
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {

				visited[i][j] = R * C; // 가장 큰 값으로 초기화

				if (map[i][j] == 'C') {
					if (isFirst) {
						sy = i;
						sx = j;
						isFirst = false;
					} else {
						ey = i;
						ex = j;
					}
				}

			}
		}

		q = new LinkedList<>();
		q.offer(new Node(sy, sx, 0, 0)); // 세로
		q.offer(new Node(sy, sx, 1, 0)); // 가로
		q.offer(new Node(sy, sx, 2, 0)); // 세로
		q.offer(new Node(sy, sx, 3, 0)); // 가로

		visited[sy][sx] = 0;

		while (!q.isEmpty()) {

			Node cn = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (map[ny][nx] == '*') {
					continue;
				}

				if (cn.preDir == d) { // 이전과 같은 방향일 때
					if (visited[ny][nx] >= cn.cnt) {
						q.offer(new Node(ny, nx, cn.preDir, cn.cnt));
						visited[ny][nx] = cn.cnt;
					}
				} else { // 이전과 다른 방향일 때
					if (visited[ny][nx] >= cn.cnt + 1) {
						q.offer(new Node(ny, nx, d, cn.cnt + 1));
						visited[ny][nx] = cn.cnt + 1;
					}

				}
			}

		}

//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(visited[i][j] + "\t");
//			}
//			System.out.println();
//		}

		System.out.println(visited[ey][ex]);
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}

	private static void 다른풀이() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");

		int n = Integer.parseInt(temp[1]);
		int m = Integer.parseInt(temp[0]);

		char[][] map = new char[n][m];
		int[][] dist = new int[n][m];

		int startX = -1;
		int startY = -1;
		int endX = -1;
		int endY = -1;

		for (int i = 0; i < n; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				dist[i][j] = -1;
				map[i][j] = row[j];
				if (map[i][j] == 'C') {
					if (startX == -1) {
						startX = i;
						startY = j;
					} else {
						endX = i;
						endY = j;
					}
					map[i][j] = '.';
				}
			}
		}

		Queue<Integer> q = new LinkedList<>();
		q.add(startX);
		q.add(startY);
		dist[startX][startY] = 0;

		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, -1, 0, 1 };

		while (!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();

			// System.out.println(x + " " + y);
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				while (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == '.') {
					if (dist[nx][ny] == -1) {
						q.add(nx);
						q.add(ny);
						dist[nx][ny] = dist[x][y] + 1;
						if (nx == endX && ny == endY) {
							System.out.print(dist[endX][endY] - 1);
							return;
						}
					}
					nx += dx[k];
					ny += dy[k];
				}
			}
		}
	}
}
