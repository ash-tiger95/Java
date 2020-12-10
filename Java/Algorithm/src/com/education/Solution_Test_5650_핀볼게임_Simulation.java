package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_Test_5650_핀볼게임_Simulation {
	static class Wormhole{
		int y,x,num;

		public Wormhole(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}

		@Override
		public String toString() {
			return "WormHole [y=" + y + ", x=" + x + ", num=" + num + "]";
		}
		
	}
	static int T, N, Ans;
	static int[][] map;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static List<Wormhole> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(br.readLine());
			Ans = 0;
			map = new int[N][N];
			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6) {
						list.add(new Wormhole(i,j,map[i][j]));
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 0) {
						for (int d = 0; d < 4; d++) {
							int count = play(i, j, d); // 하 상 우 좌
							Ans = Math.max(count, Ans);
						}
					}
					

				}
			}

			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static int play(int sy, int sx, int direction) { // DFS
//		System.out.println("in?");

		int ey = sy; // 처음 시작한 자리 저장
		int ex = sx;

		int pow = 1;
		int count = 0;
		
		int ny,nx;
		while (true) {
			ny = sy + dirs[direction][0] * pow;
			nx = sx + dirs[direction][1] * pow;
			
			if (ny < 0 || nx < 0 || ny >= N  || nx >= N ) { // 벽을 만나면
				direction = changeDirection(direction);
				pow = 1;
				sy = ny;
				sx = nx;
				count++;
				continue;
			} 

			// 종료
			if (ny == ey && nx == ex) { // 출발지로 돌아왔을 때,
				break;
			}
			if (map[ny][nx] == -1) { // 블랙홀에 빠졌을 때,
				break;
			}

			

			if (map[ny][nx] != 0) {
				if (map[ny][nx] <= 5) { // 1~5는 블록을 만났을 때
					direction = block(map[ny][nx], direction);
					sy = ny;
					sx = nx;
					count++;
					
				} else { // 웜홀에 빠졌을 때
					for(int i=0 ; i<list.size();i++) {
						if(list.get(i).num == map[ny][nx]) {
							if(list.get(i).y == ny && list.get(i).x == nx) {
								continue;
							}else {
								sy = list.get(i).y;
								sx = list.get(i).x;
							}
						}
					}
					
				}
				pow = 1;
				continue;
			}
			
			
			pow++;
		}

		return count;
	}
	private static void print(int y, int x) {
		for(int i=0; i <N;i++) {
			for(int j=0;j<N;j++) {
				if(i == y && j == x) {
					System.out.print("*"+" ");
				}else {
					System.out.print(map[i][j]+" ");
				}
			}
			System.out.println();
		}
	}

	private static int block(int blockNum, int dir) { // 하 상 우 좌
		if (blockNum == 1) {
			if (dir == 0) {
				dir = 2;
			} else if (dir == 3) {
				dir = 1;
			} else {
				dir = changeDirection(dir);
			}
		} else if (blockNum == 2) {
			if (dir == 1) {
				dir = 2;
			} else if (dir == 3) {
				dir = 0;
			} else {
				dir = changeDirection(dir);
			}
		} else if (blockNum == 3) {
			if (dir == 2) {
				dir = 0;
			} else if (dir == 1) {
				dir = 3;
			} else {
				dir = changeDirection(dir);
			}
		} else if (blockNum == 4) {
			if (dir == 0) {
				dir = 3;
			} else if (dir == 2) {
				dir = 1;
			} else {
				dir = changeDirection(dir);
			}
		} else {
			dir = changeDirection(dir);
		}

		return dir;
	}

	private static int changeDirection(int dir) { // 벽 만났을 때, 방향 전환, 하 상 우 좌
		if (dir == 0) { // 하
			dir = 1; // 상
		} else if (dir == 1) {
			dir = 0;
		} else if (dir == 2) { // 우
			dir = 3; // 좌
		} else {
			dir = 2;
		}
		return dir;
	}

}
