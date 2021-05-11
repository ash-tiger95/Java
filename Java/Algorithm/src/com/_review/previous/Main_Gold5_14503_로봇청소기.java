package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_14503_로봇청소기 {
	static int N, M, time;
	static int sy, sx, direction;
	static int[][] map;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};

	static class Robot {
		int y;
		int x;
		int dir;

		public Robot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "robot [y=" + y + ", x=" + x + ", dir=" + dir + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		direction = Integer.parseInt(st.nextToken()); // 0 1 2 3: 북 동 남 서 바라보기
		
		

		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map[sy][sx] = 9; // 청소한 곳은 9로 변경
		bfs();
		time = 0;
		check();
		System.out.println(time);

	}
	private static void check() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 9) {
					time++;
				}
			}
		}
	}
	private static void bfs() {
		Queue<Robot> que = new LinkedList<>();
		que.offer(new Robot(sy,sx,direction));
		while(!que.isEmpty()) {
			Robot cur = que.poll();
			
			boolean flag = false; // 4방향이 모두 청소되어있거나 벽을 만날 경우를 위함
			
			for(int d=0;d<4;d++) {
				// 핵심
				cur.dir = (cur.dir+3)%4; // 다음 이동할 방향
				int ny = cur.y + dy[cur.dir]; // 다음 이동할 Y좌표
				int nx = cur.x + dx[cur.dir]; // 다음 이동할 X좌표
				
				if(!boundary(ny,nx))
					continue;
				
				if(map[ny][nx] == 0) {
//					System.out.println("전진: "+ny+" "+nx+" "+cur.dir);
					que.offer(new Robot(ny,nx,cur.dir));
					map[ny][nx] = 9;
					flag = true;
					break;
				}
			}
			
			// 후진이 필요한 경우
			if(!flag) { // 4방향 모두 청소 완료 || 벽 -> 후진 (방향 유지)
				int nd = (cur.dir + 2) %4; // ny와 nx를 계산하기 위함.
				int ny = cur.y + dy[nd];
				int nx = cur.x + dx[nd];
				
				// 만약 후진할 곳이 있으면
				if(map[ny][nx] != 1 ) {
					map[ny][nx] = 9;
//					System.out.println("후진: "+ny+" "+nx+" "+cur.dir);
					que.offer(new Robot(ny,nx,cur.dir));
				} 
			}
			
			
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println("-------------------");
			
		}
	}
	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx>=0 && nx<M;
	}
}
