package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Test_5644_무선충전 {
	static class BC {
		int y, x, c, p;

		public BC(int y, int x, int c, int p) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}

		@Override
		public String toString() {
			return "BC [y=" + y + ", x=" + x + ", c=" + c + ", p=" + p + "]";
		}

	}
	static class User{
		int y, x,type;

		@Override
		public String toString() {
			return "User [y=" + y + ", x=" + x + ", type=" + type + "]";
		}

		public User(int y, int x, int type) {
			super();
			this.y = y;
			this.x = x;
			this.type = type;
		}
		
		
		
	}

	static int T, M, A,Ans;
	static int[][] move;
	static ArrayList<BC> listBC;
	static ArrayList<Integer>[][] listMap; // 2차원 ArrayList 만들기

	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] visited;
	static Queue<BC> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 총 이동시간, 20 <= M <= 100
			A = Integer.parseInt(st.nextToken()); // BC 개수, 1 <= A <= 8
			Ans = 0;

			move = new int[2][M]; // 사용자A: move[0], 사용자B: move[1]
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					move[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			listBC = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken()); // 성능, 10 <= P <= 500의 짝수
				listBC.add(new BC(y, x, c, p));
			}

			listMap = new ArrayList[10][10]; // map의 크기는 10x10으로 고정
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					listMap[i][j] = new ArrayList<>();
				}
			}

			// 충전범위 설정하기
			for (int i = 0; i < A; i++) { // BC 모든 경우 실행
				// 1. 초기화
				queue = new LinkedList<BC>();
				visited = new boolean[10][10];
				
				BC centerBC = listBC.get(i); // BC 중심
				queue.offer(centerBC); // BC 하나하나 BFS를 돌린다.
				
				visited[centerBC.y][centerBC.x] = true;
				listMap[centerBC.y][centerBC.x].add(centerBC.p);
				
				while(!queue.isEmpty()) { // BFS
					BC current = queue.poll();
					
					for (int d = 0; d < 4; d++) { // BFS
						int ny = current.y + dirs[d][0];
						int nx = current.x + dirs[d][1];

						if (!boundary(ny, nx)) {
							continue;
						}
						
						// 거리 계산, D = |Xa-Xb| + |Ya-Yb|
						if(!checkDistance(centerBC,ny,nx)) {
							continue;
						}
						
						if(!visited[ny][nx]) {
							listMap[ny][nx].add(centerBC.p);
							queue.add(new BC(ny,nx,current.c,current.p));
							visited[ny][nx] = true;
						}
					}
				} // while BFS
			} // 충전범위 설정하기
			
			User A = new User(0,0,1);
			User B = new User(9,9,2);
			// 사용자 이동시키기
			for(int time=0;time<=M;time++) { // time은 0초부터 M초까지
				Ans += checkBC(A,B);
			}
			
			

		}
	}

	private static int checkBC(User a, User b) {
		return 0;
	}

	private static boolean checkDistance(BC centerBC, int ny, int nx) {
		int d = Math.abs(centerBC.x - nx) + Math.abs(centerBC.y - ny);
		if(d > centerBC.c) {
			return false;
		} else {
			return true;
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < 10 && nx < 10;
	}
}
