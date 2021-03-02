package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Test_5653_줄기세포배양 {
	static class Cell {
		int y, x, type, life, wait;

		public Cell(int y, int x, int life, int type, int wait) {
			super();
			this.y = y;
			this.x = x;
			this.life = life; // 생명력
			this.type = type; // 1: 대기, 2: 실행, 3: 죽음
			this.wait = wait; // 대기까지 시간
		}

		@Override
		public String toString() {
			return "Cell [y=" + y + ", x=" + x + ", type=" + type + ", life=" + life + ", wait=" + wait + "]";
		}
		

	}

	static int T, N, M, K, Ans;
	static int[][] map;
	static ArrayList<Cell>[] cell;
	static boolean[][] visited;
	static Queue<Cell> queue;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			Ans = 0;

			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N + K][M + K];
			cell = new ArrayList[10];
			for (int i = 0; i < 10; i++) {
				cell[i] = new ArrayList<Cell>();
			}

			visited = new boolean[N + K][M + K];

			queue = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i + K / 2][j + K / 2] = Integer.parseInt(st.nextToken());
					if (map[i + K / 2][j + K / 2] != 0) {
						cell[map[i + K / 2][j + K / 2]-1].add(new Cell(i + K / 2, j + K / 2, map[i + K / 2][j + K / 2], 1,
								map[i + K / 2][j + K / 2]));
						visited[i + K / 2][j + K / 2] = true;
					}
				}
			}

			
			int time = 0;
			while (time < K) {
				
				time++; // 1초 후

				// 큰 것부터 필요한 거 넣기
				for (int i = 9; i >= 0; i--) {
					if (cell[i].size() != 0) {
						int cellSize = cell[i].size();
						for (int s = 0; s < cellSize; s++) {

							queue.offer(cell[i].get(s));

						}
						cell[i].clear();

					}
				}
				

				int size = queue.size();
//				System.out.println("start size: "+size);
				

				for (int s = 0; s < size; s++) {
					Cell cur = queue.poll();

					if (cur.type == 1) { // 대기상태
						if (cur.wait == 1) {
							cell[cur.life-1].add(new Cell(cur.y, cur.x, cur.life, 2, 0));
						} else {
							cell[cur.life-1].add(new Cell(cur.y, cur.x, cur.life, 1, cur.wait-1)); // 대기
						}
					} else if (cur.type == 2) { // 활성상태
						if(cur.life!=1) {
							cell[cur.life-1].add(new Cell(cur.y, cur.x, cur.life, 3, cur.life-1));
						}
						
						// BFS
						for(int d=0;d<4;d++) {
							int ny = cur.y + dirs[d][0];
							int nx = cur.x + dirs[d][1];
							if(!boundary(ny,nx)) {
								continue;
							}
//							System.out.println(ny+","+nx);
							if(!visited[ny][nx]) {
								
								visited[ny][nx] = true;
								cell[cur.life-1].add(new Cell(ny,nx,cur.life,1,cur.life));
							}
						}
						
						
					} else if(cur.type == 3) {
						if (cur.wait == 1) {
							continue;
						} else {
							cell[cur.life-1].add(new Cell(cur.y, cur.x, cur.life, 3, cur.wait-1)); // 대기
						}
					}
				}
//				print();
//				for(int i=0;i<10;i++) {
//					int css = cell[i].size();
//					for(int s=0;s<css;s++) {
//						System.out.println(cell[i].get(s).toString());
//					}
//				}
//				System.out.println("==========");
			}
			for(int i=0;i<10;i++) {
				Ans += cell[i].size();
			}

			sb.append(Ans).append('\n');
		}
		System.out.println(sb.toString());
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < N + K && nx < M + K;
	}

	private static void print() {
		for (int i = 0; i < N + K; i++) {
			for (int j = 0; j < M + K; j++) {
				System.out.print(visited[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
