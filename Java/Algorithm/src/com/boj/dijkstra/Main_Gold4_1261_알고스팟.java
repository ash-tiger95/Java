package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
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

	static class Edge implements Comparable<Edge> {
		int v, weight;

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
		print(map);

		int roomNum = 1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 && room[i][j] == 0) {
					numbering(i, j, roomNum++);
				}
			}
		}
		System.out.println(roomNum);
		System.out.println("--------");
		print(room);

		// 그래프 만들기
		list = new ArrayList[roomNum]; // 총 방 개수, roomNum-1개
		for (int i = 0; i < roomNum; i++) {
			list[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (room[i][j] != 0) {
//					System.out.println("standard: "+i+" "+j+"--------------");
					for (int d = 0; d < 4; d++) {
						int ny = i + dirs[d][0];
						int nx = j + dirs[d][1];

						if (!boundary(ny, nx) || room[i][j] == room[ny][nx]) { // 경계선 밖으로 나가거나 같은 방이면 패스
							continue;
						}

						int pow = 2;
//						System.out.println("start: ("+i+", "+j+") ("+ny+", "+nx+")");
						while (true) {
							ny = i + dirs[d][0] * pow;
							nx = j + dirs[d][1] * pow;
							if(!boundary(ny,nx)) {
								break;
							}
							
							if(room[i][j] == room[ny][nx]) {
								break;
							}

							if (room[ny][nx] != room[i][j] && room[ny][nx] !=0) {
//								System.out.println("in: ("+i+", "+j+") ("+ny+", "+nx+")");
								list[room[i][j]].add(new Edge(room[ny][nx], pow-1));
								break;
							}

							pow++;
						}
					}
				}
			}
		}

		for (int i = 0; i < roomNum; i++) {
			System.out.println(list[i].toString());
		}
		
		int start = room[0][0];
		int end = room[M-1][N-1];
		System.out.println(dijkstra(start,end,roomNum));

	}

	private static int dijkstra(int start, int end,int roomNum) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[roomNum];
		int[] distance = new int[roomNum];
		
		pq.add(new Edge(start,0));
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge ce = pq.poll();
			
			if(visited[ce.v]) {
				continue;
			}
			
			for(Edge next : list[ce.v]) {
				if(distance[next.v] > distance[ce.v] + next.weight) {
					distance[next.v] = distance[ce.v] + next.weight;
					pq.add(new Edge(next.v,distance[next.v]));
				}
			}
			
			visited[ce.v] = true;
		}
		
		return distance[end];
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

				if (map[ny][nx] == 0) {
					queue.offer(new Pair(ny, nx));
					visited[ny][nx] = true;
					room[ny][nx] = roomNum;
				}
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < M && nx < N;
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
