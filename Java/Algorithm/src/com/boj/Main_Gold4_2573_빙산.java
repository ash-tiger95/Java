package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_2573_빙산 {
	static int N, M, time, mass;
	static int[][] map;
	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Queue<int[]> eachIceberg;
	static Queue<int[]> linkIceberg;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		eachIceberg = new LinkedList<>(); // 빙산 녹이기 위한 큐
		linkIceberg = new LinkedList<>(); // 연결되어 있는 빙산을 찾기 위한 큐
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					eachIceberg.offer(new int[] { i, j }); // 빙산 각각의 좌표 저장
				}
			}
		}
		time = 0;
		
		while (true) {
			// 연결되어 있는 빙산 개수 찾기
			visited = new boolean[N][M]; // 방문 처리 초기화
			mass = 0; // 덩어리 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						countIceberg(i, j);
						mass++;
					}
				}
			}
			// 연결되어 있는 빙산이 하나보다 많으면 종료
			if (mass > 1) {
				break;
			}

			// 빙산 녹이기
			meltIceberg();
			time++;
			
			// 빙산이 전부 녹을 때 까지 빙산이 1개인 경우
			if (eachIceberg.isEmpty()) {
				time = 0;
				break;
			}
		}
		System.out.println(time);

	}

	// 빙산 녹이기
	private static void meltIceberg() {
		// 빙산 각각을 녹이기 위해 복사해서 사용
		int[][] iceberg = new int[N][M];
		for (int i = 0; i < N; i++) {
			iceberg[i] = map[i].clone();
		}
		
		// 현재 빙산 개수만큼만 돌린다.
		int queSize = eachIceberg.size();
		while (queSize > 0) {
			int[] current = eachIceberg.poll();
			int cy = current[0];
			int cx = current[1];
			
			// 접촉한 바다 개수만큼 녹인다.
			int sea = 0;
			for (int d = 0; d < 4; d++) {
				int ny = cy + dir[d][0];
				int nx = cx + dir[d][1];
				if (!boundary(ny, nx)) {
					continue;
				}
				if (map[ny][nx] == 0) {
					sea++;
				}
			}
			iceberg[cy][cx] -= sea;
			
			if (iceberg[cy][cx] < 0) { // 모두 녹았으면 0
				iceberg[cy][cx] = 0;
			} else { // 아직 덜 녹았으면 다음 번에 큐를 돌리기 위해 다시 큐에 저장
				eachIceberg.offer(new int[] { cy, cx });
			}
			
			queSize--;
		}

		// 빙산 녹인 후 업데이트
		for (int i = 0; i < N; i++) {
			map[i] = iceberg[i].clone();
		}
	}

	// 연결되어 있는 빙산 개수 찾기
	private static void countIceberg(int iy, int ix) {
		linkIceberg.offer(new int[] { iy, ix });

		while (!linkIceberg.isEmpty()) {
			int[] current = linkIceberg.poll();
			int cy = current[0];
			int cx = current[1];
			visited[cy][cx] = true;

			for (int d = 0; d < 4; d++) {
				int ny = cy + dir[d][0];
				int nx = cx + dir[d][1];
				if (!boundary(ny, nx)) {
					continue;
				}
				if (!visited[ny][nx] && map[ny][nx] > 0) {
					visited[ny][nx] = true;
					linkIceberg.offer(new int[] { ny, nx });
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
