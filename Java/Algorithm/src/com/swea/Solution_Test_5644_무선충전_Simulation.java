package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Test_5644_무선충전_Simulation {
	static class PointBC implements Comparable<PointBC> {
		int id, p;

		public PointBC(int id, int p) {
			super();
			this.id = id;
			this.p = p;
		}

		@Override
		public String toString() {
			return "PointBC [id=" + id + ", p=" + p + "]";
		}

		@Override
		public int compareTo(PointBC o) {
			return o.p - this.p;
		}

	}

	static int T, M, A, Ans;
	static int[][] move;
	static ArrayList<PointBC>[][] map; // 2차원 ArrayList

	static int[][] dirs = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } }; // 0, 상 우 하 좌
	static boolean[][] visited;
	static Queue<int[]> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for (int time = 1; time <= T; time++) {
			sb.append("#").append(time).append(" ");
			Ans = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			move = new int[2][M];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					move[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 2차원 리스트 Map 생성
			map = new ArrayList[10][10];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j] = new ArrayList<PointBC>();
				}
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				map[y][x].add(new PointBC(i, p));
				visited = new boolean[10][10];
				queue = new LinkedList<>();
				queue.offer(new int[] { y, x });
				visited[y][x] = true;
				int t = 0;
				
				while (t != c) {
					int size = queue.size();
					for(int s=0;s<size;s++) {
						int[] cp = queue.poll();

						for (int d = 1; d < 5; d++) {
							int ny = cp[0] + dirs[d][0];
							int nx = cp[1] + dirs[d][1];

							if (!boundary(ny, nx)) {
								continue;
							}

//							if (!calDistance(cp[0], cp[1], ny, nx, c)) {
//								continue;
//							}

							if (!visited[ny][nx]) {
								queue.offer(new int[] { ny, nx });
								visited[ny][nx] = true;
								map[ny][nx].add(new PointBC(i, p));
							}
						}
					}
					
					t++;
				}
			}
			
			
//			for(int i=0;i<10;i++) {
//				for(int j=0;j<10;j++) {
//					System.out.print(map[i][j].size());
//				}
//				System.out.println();
//			}
//			System.out.println("-----------");

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					Collections.sort(map[i][j]);
				}
			}

			int ay = 0;
			int ax = 0;
			int by = 9;
			int bx = 9;
			Ans += play(ay, ax, by, bx);
			for (int m = 0; m < M; m++) {
				ay = ay + dirs[move[0][m]][0]; // 0, 상, 우, 하, 좌
				ax = ax + dirs[move[0][m]][1];
				by = by + dirs[move[1][m]][0];
				bx = bx + dirs[move[1][m]][1];

				Ans += play(ay, ax, by, bx);

			}

			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static int play(int ay, int ax, int by, int bx) {
		int aSize = map[ay][ax].size();
		int bSize = map[by][bx].size();

		if (aSize == 0 && bSize == 0) {
			return 0;
		} else if (aSize != 0 && bSize == 0) {
			return map[ay][ax].get(0).p;
		} else if (aSize == 0 && bSize != 0) {
			return map[by][bx].get(0).p;
		} else {
			if(aSize == 1 && bSize == 1) {
				if(map[ay][ax].get(0).id == map[by][bx].get(0).id) {
					return map[ay][ax].get(0).p;
				} else {
					return map[ay][ax].get(0).p+ map[by][bx].get(0).p;
				}
			} else {
				if(map[ay][ax].get(0).id == map[by][bx].get(0).id) {
					if(aSize == 1) {
						return map[ay][ax].get(0).p + map[by][bx].get(1).p;
					} else if(bSize == 1) {
						return map[ay][ax].get(1).p + map[by][bx].get(0).p;
					} else {
						int case1 = map[ay][ax].get(0).p + map[by][bx].get(1).p;
						int case2 = map[ay][ax].get(1).p + map[by][bx].get(0).p;
						return case1 > case2 ? case1 : case2;
					}
				} else {
					return map[ay][ax].get(0).p + map[by][bx].get(0).p;
				}
				
			}
		}
	}

	private static boolean calDistance(int i, int j, int ny, int nx, int c) {
		int d = Math.abs(i - ny) + Math.abs(j - nx);
		if (d > c) {

			return false;
		} else {
			return true;
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < 10 && nx < 10;
	}

}
