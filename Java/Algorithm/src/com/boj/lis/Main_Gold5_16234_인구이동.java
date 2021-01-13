package com.boj.lis;

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
			visited = new boolean[N][N]; // 방문 여부 체크 (하루동안 인구 이동 일어나고 다음날 다시 리셋)
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if (!visited[i][j] && search(i, j)) { // search: 인구이동이 일어 났는지 일어나지 않았는지
						change = true; // 인구이동이 일어났다.
					}
				}
			}
			if (!change) { // 인구이동이 안 일어나면 종료
				break;
			}
			Ans++; // 인구이동 일어난 횟수
		}
		System.out.println(Ans);

	} // main

	private static boolean search(int r, int c) {
		ArrayList<Loc> al = new ArrayList<>(); // 인구 이동 시 인구 수를 계산하기 위함.
		Queue<Loc> queue = new LinkedList<>(); // BFS 돌리기 위함.
		Loc l = new Loc(r, c);
		al.add(l);
		queue.offer(l);
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			l = queue.poll();
			r = l.r;
			c = l.c;
			for (int i = 0; i < 4; i++) { // BFS로 검사
				int nr = r + dy[i];
				int nc = c + dx[i];
				if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] && diff(map[nr][nc], map[r][c])) {
					Loc nl = new Loc(nr, nc);
					queue.offer(nl);
					al.add(nl);
					visited[nr][nc] = true; // 방문처리
				}
			}
		}
		
		if (al.size() >= 2) { // 인접국가 개수가 2개 이상이면 인구이동 발생
			int total = 0;
			for (Loc loc : al) { // type var : iterate
				total += map[loc.r][loc.c];
			}
			int avg = total / al.size(); // 평균 구하기
			for (Loc loc : al) {
				map[loc.r][loc.c] = avg; // map에 변화된 인구 수 적용
			}
			return true; // 인구 이동이 있으면 true 없으면 false
		}
		return false;
	}

	private static boolean diff(int i, int j) { // 인접국가 인구 차 계산
		int sub = i >= j ? i - j : j - i;
		return L <= sub && sub <= R;
	}

}