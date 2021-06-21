package com.boj.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver2_18232_텔레포트정거장 { // 1차원 BFS

	static int N, M, S, E, min;
	static ArrayList<Integer>[] teleport;
	static int[] dirs = { 1, -1 };

	static class Node {
		int x, cnt; // x: 좌표, cnt: 이동거리

		public Node(int x, int cnt) {
			super();
			this.x = x;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1; // 일차원 Map 크기, 2 ≤ N ≤ 300,000
		M = Integer.parseInt(st.nextToken()); // teleport 개수, 0 ≤ M ≤ min(N×(N-1)/2, 300,000)

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); // 1 ≤ S, E ≤ N
		E = Integer.parseInt(st.nextToken());

		// 시간초과
//		teleport = new int[M][2];
//		for (int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//			teleport[i][0] = Integer.parseInt(st.nextToken());
//			teleport[i][1] = Integer.parseInt(st.nextToken());
//		}

		// 시간초과 해결
		teleport = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			teleport[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			teleport[a].add(b);
			teleport[b].add(a);
		}

		min = 0;
		bfs();

		sb.append(min);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N];

		q.offer(new Node(S, 0));
		visited[S] = true;

		while (!q.isEmpty()) {
			Node cn = q.poll();

			if (cn.x == E) {
				min = cn.cnt;
				q.clear();
				return;
			}

			// 텔레포트로 이동하기
			for (int next : teleport[cn.x]) {
				if (!visited[next]) {
					q.offer(new Node(next, cn.cnt + 1));
					visited[next] = true;
				}
			}

			// +1, -1로 이동하기
			for (int d = 0; d < 2; d++) {
				int nx = cn.x + dirs[d];

				if (nx < 0 || nx >= N) {
					continue;
				}

				if (!visited[nx]) {
					q.offer(new Node(nx, cn.cnt + 1));
					visited[nx] = true;
				}
			}
		}
	}
}
