package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_16234_인구이동 {
	static int N, L, R, Ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken()); // L명 이상
		R = Integer.parseInt(st.nextToken()); // R명 이하

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Ans = 0; // 인구이동의 횟수

		while (true) {
			boolean change = false; // 인구 이동 여부 체크
			// 모든 칸 순회
			// BFS 탐색 인접한 국가와 L~R 범위 내의 인구 차
			visited = new boolean[N][N]; // 방문 여부 체크
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if (!visited[i][j] && search(i, j)) { // search: 인구이동이 일어 났는지 일어나지 않았는지
						change = true;
					}
				}
			}
			if (!change) {
				break;
			}
			Ans++;
		}
		System.out.println(Ans);

	} // main

	private static boolean search(int r, int c) {
		ArrayList<Loc> al = new ArrayList<>();
		Queue<Loc> queue = new LinkedList<>();
		Loc l = new Loc(r, c);
		al.add(l);
		queue.offer(l);
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			l = queue.poll();
			r = l.r;
			c = l.c;
			for (int i = 0; i < 4; i++) {
				int nr = r + dy[i];
				int nc = c + dx[i];
				if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] && diff(map[nr][nc], map[r][c])) {
					Loc nl = new Loc(nr, nc);
					queue.offer(nl);
					al.add(nl);
					visited[nr][nc] = true;
				}
			}

		}
		// 인접국가 개수가 2개 이상이면 인구이동 발생
		if(al.size() >= 2) {
			int total = 0;
			for(Loc loc : al) {
				total += map[loc.r][loc.c];
			}
			int avg = total/al.size();
			for(Loc loc : al) {
				map[loc.r][loc.c] = avg;
			}
			return true;
		}
		// 인구 이동이 있으면 true 없으면 false

		return false;
	}

	private static boolean diff(int i, int j) {
		int sub = i >= j ? i - j : j - i;
		return L <= sub && sub <= R;
	}

	static class Loc {
		int r, c;

		public Loc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Loc [r=" + r + ", c=" + c + "]";
		}

	}
}
