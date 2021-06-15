package com.swea.sw역량테스트;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Test_5650_핀볼게임 {

	static int T, N, Ans;
	static int[][] map;
	static HashMap<Integer, WarmHole> warmhole;
	static int[][] dirs = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

	static class Node {
		int y, x, dir, cnt;

		public Node(int y, int x, int dir, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

	static class WarmHole {
		int sy, sx, ey, ex;

		public WarmHole(int sy, int sx, int ey, int ex) {
			super();
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\5650.txt")));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			Ans = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			warmhole = new HashMap<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] >= 6) { // 웜홀은 6번~10번 최대 5쌍
						if (warmhole.containsKey(map[i][j])) {
							WarmHole wh = warmhole.get(map[i][j]);
							wh.ey = i;
							wh.ex = j;
							warmhole.put(map[i][j], wh);
						} else {
							warmhole.put(map[i][j], new WarmHole(i, j, 0, 0));
						}
					}
				}
			}

			// Game Start
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					if (map[i][j] == 0) {
						for (int d = 0; d < 4; d++) {
							int cnt = startGame(i, j, d);
							
							if (cnt > Ans) { // 점수의 최댓값 구하기
								Ans = cnt;
							}
						}
					}
					
				}
			}

			sb.append(Ans).append("\n");
		} // for T

		System.out.println(sb);
		br.close();
	}

	private static int startGame(int sy, int sx, int sd) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(sy, sx, sd, 0));
		
		boolean isFirst = true; // 최초 시작을 시작점에서 하므로

		while (true) {
			Node cn = q.poll(); // 현재 좌표

			if (!isFirst && cn.y == sy && cn.x == sx) { // 시작 지점으로 돌아온 경우
				return cn.cnt;
			}
			
			if(!isFirst && map[cn.y][cn.x]== -1 ) { // 블랙홀에 빠진 경우
				return cn.cnt;
			}

			int ny = cn.y + dirs[cn.dir][0]; // 다음 이동할 좌표
			int nx = cn.x + dirs[cn.dir][1];

			int nd = cn.dir;
			int nCnt = cn.cnt;

			if (!boundary(ny, nx)) { // 벽에 부딛히는 경우
				ny = cn.y; // 원래 좌표로 되돌리고
				nx = cn.x;
				nd = (nd + 2) % 4; // 방향 바꾸기
				nCnt++;
			} 
			
			if (map[ny][nx] >= 1 && map[ny][nx] <= 5) { // 블록을 만나는 경우
				if (map[ny][nx] == 1 && (nd == 0 || nd == 1)) { // 1번 블록을 옳게 들어가는 경우
					nd = nd == 0 ? 3 : 2;
				} else if (map[ny][nx] == 2 && (nd == 1 || nd == 2)) {// 2번 블록을 옳게 들어가는 경우
					nd = nd == 1 ? 0 : 3;
				} else if (map[ny][nx] == 3 && (nd == 2 || nd == 3)) {// 3번 블록을 옳게 들어가는 경우
					nd = nd == 2 ? 1 : 0;
				} else if (map[ny][nx] == 4 && (nd == 3 || nd == 0)) {// 4번 블록을 옳게 들어가는 경우
					nd = nd == 3 ? 2 : 1;
				} else { // 5번 블록 혹은 옳지 않게 들어가는 경우
					nd = (nd + 2) % 4;
				}

				nCnt++;
			} else if (map[ny][nx] >= 6) { // 웜홀을 만나는 경우
				WarmHole wh = warmhole.get(map[ny][nx]);

				if (wh.sy == ny && wh.sx == nx) {
					ny = wh.ey;
					nx = wh.ex;
				} else {
					ny = wh.sy;
					nx = wh.sx;
				}
			}

			isFirst = false;

			q.offer(new Node(ny, nx, nd, nCnt)); // 다음 이동하는 좌표의 모양을 만든다.
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

}
