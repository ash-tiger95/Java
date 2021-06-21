package com.boj.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Silver2_10971_외판원순회2 {

	static int N, min;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			visited = new boolean[N]; // 시작할 때 시작점 방문처리하지 말고 dfs에서 처리하는게 이쁜듯!
			dfs(i, i, 0, 0);
		}

		sb.append(min);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static void dfs(int start, int cur, int cnt, int cost) {
		if (cnt == N && cur == start) {

			min = Math.min(min, cost);
			return;
		}

		if (cost > min) { // 있으면 172ms, 없으면 2180ms
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}

			if (map[cur][i] == 0) {
				continue;
			}

			visited[i] = true;

			dfs(start, i, cnt + 1, cost + map[cur][i]);

			visited[i] = false;
		}
	}
}
