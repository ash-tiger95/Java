package com.boj.graph.floydwarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold4_11404_플로이드 {
	static final int INF = 1000000000;
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시 개수, 2 <= N <= 100
		M = Integer.parseInt(br.readLine()); // 버스 개수, 1 <= M <= 100,000
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}
		
		StringTokenizer st = null;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 출발
			int b = Integer.parseInt(st.nextToken()) - 1; // 도착
			int c = Integer.parseInt(st.nextToken()); // 가중치
			if(map[a][b] < c) {
				continue;
			}
			map[a][b] = c;
		}

		for (int k = 0; k < N; k++) { // 경유 정점
			for (int i = 0; i < N; i++) { // 출발 정점
				if (k == i) {
					continue;
				}
				for (int j = 0; j < N; j++) { // 도착 정점
					if (k == j | i == j) {
						continue;
					}
					if (map[i][k] != INF && map[k][j] != INF && map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] >= INF) {
					System.out.print(0+" ");
				}else {
					System.out.print(map[i][j] + " ");
					
				}
			}
			System.out.println();
		}
	}

}
