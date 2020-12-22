package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_1261_알고스팟 {
	static class Pair {
		int y, x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Pair [y=" + y + ", x=" + x + "]";
		}

	}
	static class Edge implements Comparable<Edge>{
		int v,weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [v=" + v + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}

	static int N, M;
	static int[][] map;
	static int[][] room;

	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	
	static ArrayList<Edge>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		room = new int[M][N];
		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		print( map);

		int roomNum = 1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 && room[i][j] ==0) {
					
					numbering(i, j,roomNum++);
				}
			}
		}
		System.out.println(roomNum);
		System.out.println("--------");
		print(room);
		
		// 그래프 만들기
		list = new ArrayList[roomNum-1];
		for(int i=0;i<roomNum-1;i++) {
			list[i] = new ArrayList<Edge>();
		}
		
		boolean[] check = new boolean[roomNum-1];
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(room[i][j] != 0 && !check[room[i][j]]) {
					
				}
			}
		}
		

	}

	private static void numbering(int i, int j, int roomNum) {
		Queue<Pair> queue = new LinkedList<>();
		boolean[][] visited = new boolean[M][N];
		queue.offer(new Pair(i, j));
		visited[i][j] = true;
		room[i][j] = roomNum;

		while (!queue.isEmpty()) {
			Pair cp = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cp.y + dirs[d][0];
				int nx = cp.x + dirs[d][1];

				if (ny < 0 || nx < 0 || ny >= M || nx >= N)
					continue;

				if (visited[ny][nx])
					continue;

				if(map[ny][nx] == 0) {
					queue.offer(new Pair(ny,nx));
					visited[ny][nx] = true;
					room[ny][nx] = roomNum;
				}
			}
		}
	}

	private static void print(int[][] map) {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
