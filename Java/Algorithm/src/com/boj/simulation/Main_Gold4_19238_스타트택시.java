package com.boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_19238_스타트택시 {

	static int N, M, gas, sy, sx, Ans;
	static int[][] map;
	static ArrayList<Node> client;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Node {
		int sy, sx, ey, ex;

		public Node(int sy, int sx, int ey, int ex) {
			super();
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N x N, 2 <= N <= 20
		M = Integer.parseInt(st.nextToken()); // 고객 수, 1 <= M <= N^2
		gas = Integer.parseInt(st.nextToken()); // 초기 연료, 1 <= gas <= 500,000

		// map
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 벽: 1
			}
		}

		// 택시 시작 지점
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken()) - 1;
		sx = Integer.parseInt(st.nextToken()) - 1;

		// 고객 출발지점 및 도착지점
		client = new ArrayList<>(); // sy, sx, ey, ex
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;

			client.add(new Node(sy, sx, ey, ex));
			map[sy][sx] = 2; // 효율성을 높이기 위해. map을 q.size()만큼 돌리면서 체크
		}

		Ans = 0;
		boolean impossible = false; // -1이 나오는 경우

		while (!client.isEmpty()) {
			int[] point = searchClient();
			int idx = -1;

			if (point[0] == -1) {
				System.out.println(-1);
				impossible = true;
				break;
			} else {
				for (int i = 0; i < client.size(); i++) {
					if (client.get(i).sy == point[0] && client.get(i).sx == point[1]) {
						idx = i;
					}
				}
			}

			map[point[0]][point[1]] = 0;
			int usedGas = moveToDest(idx);

			if (usedGas == -1) {
				System.out.println(-1);
				impossible = true;
				break;
			} else {
				gas = gas + usedGas;
			}
		}
		
		if (!impossible) {
			System.out.println(gas);
		}
	}

	private static int moveToDest(int clientNum) { // 고객을 목적지까지 데랴다주기
		Node ccn = client.get(clientNum); // 현재 고객의 출발좌표 및 도착좌표

		// bfs
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		q.offer(new int[] { ccn.sy, ccn.sx, 0 });
		visited[ccn.sy][ccn.sx] = true;

		while (!q.isEmpty()) {
			int[] cn = q.poll();

			if (cn[0] == ccn.ey && cn[1] == ccn.ex) {
				if (gas < cn[2]) {
					return -1;
				}
				
				sy = ccn.ey; // 택시좌표 업데이트
				sx = ccn.ex;
				client.remove(clientNum); // 완료한 고객 제거
				return cn[2];
			}

			for (int d = 0; d < 4; d++) {
				int ny = cn[0] + dirs[d][0];
				int nx = cn[1] + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (!visited[ny][nx] && map[ny][nx] != 1) {
					q.offer(new int[] { ny, nx, cn[2] + 1 });
					visited[ny][nx] = true;
				}
			}
		}

		return -1; // 목적지까지 이동할 수 없는 경우
	}

	private static int[] searchClient() { // 가장 가까운 고객 찾기
		int minDist = Integer.MAX_VALUE;
		int[] point = new int[2];

		// bfs
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		q.offer(new int[] { sy, sx, 0 });
		visited[sy][sx] = true;

		while (!q.isEmpty()) {

			boolean find = false;

			int size = q.size();
			for (int i = 0; i < size; i++) {

				int[] cn = q.poll();

				if (map[cn[0]][cn[1]] == 2) { // 고객좌표 검사
					if (gas < cn[2]) { // 불가능한 경우
						point[0] = -1;
						return point;
					}

					if (cn[2] < minDist) {
						minDist = cn[2];
						point[0] = cn[0];
						point[1] = cn[1];
					} else if (cn[2] == minDist) {
						if (point[0] > cn[0]) {
							point[0] = cn[0];
							point[1] = cn[1];
						} else if (point[0] == cn[0]) {
							if (point[1] > cn[1]) {
								point[0] = cn[0];
								point[1] = cn[1];
							}
						}
					}

					find = true;
				}

				for (int d = 0; d < 4; d++) {
					int ny = cn[0] + dirs[d][0];
					int nx = cn[1] + dirs[d][1];

					if (!boundary(ny, nx)) {
						continue;
					}

					if (!visited[ny][nx] && map[ny][nx] != 1) {
						q.offer(new int[] { ny, nx, cn[2] + 1 });
						visited[ny][nx] = true;
					}
				}
			} // for size

			if (find) {
				gas -= minDist;
				return point;
			}
		} // for while

		point[0] = -1; // 다음 출발지로 이동 못하는 경우
		return point;
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
