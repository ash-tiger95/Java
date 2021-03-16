package com.boj.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_14503_로봇청소기 {
	static int N, M, sy, sx, sd;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 반시계 방향, 북 -> 서 -> 남 -> 동

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		sd = Integer.parseInt(st.nextToken()); // 북:0 동:1 남:2 서:3
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int Ans = 1; // 현재위치 청소
		visited[sy][sx] = true;

		while (true) {
			boolean flag = false;
			for (int d = 1; d < 5; d++) { // 현재 위치에서 현재 방향을 기준으로 왼쪽부터 탐색
				int nd = (sd + d) % 4; // 방향 바꾸고 검사

				int ny = sy + dirs[nd][0];
				int nx = sx + dirs[nd][1];

				if (map[ny][nx] == 0 && !visited[ny][nx]) { // 청소할 공간이 있으면
					visited[ny][nx] = true; // 청소하고
					Ans++;
					sd = nd; // 그 방향으로 회전하고
					sy = ny; // 한 칸 전진
					sx = nx;
					flag = true;
					break;
				}
			}
			if (!flag) { // 청소할 공간이 없으면 후진
				int nd = (sd + 2) % 4; // 후진한 칸을 위함
				int ny = sy + dirs[nd][0]; // 후진 좌표
				int nx = sx + dirs[nd][1];

				if (map[ny][nx] == 0) {
					if (!visited[ny][nx]) { // 청소 안 한 공간이면
						visited[ny][nx] = true; // 청소하고
						Ans++;
					}

					sy = ny; // 좌표 이동, 단 방향은 그대로 유지
					sx = nx;
					flag = true;
				}

			}
			if (!flag) { // 4방향 모두 청소가 되어있거나 벽이면서 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우
				break;
			}

		}
		System.out.println(Ans);
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("---");
	}

}
