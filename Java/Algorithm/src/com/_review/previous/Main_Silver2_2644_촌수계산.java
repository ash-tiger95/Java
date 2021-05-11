package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver2_2644_촌수계산 {

	static int N, M, start, end;
	static int[][] adj;
	static boolean[] visited;

	static class Node {
		int idx;
		int cnt;

		public Node(int idx, int cnt) {
			super();
			this.idx = idx;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		adj = new int[N + 1][N + 1];
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a][b] = adj[b][a] = 1; // 인접행렬
		}

		visited = new boolean[N + 1];
		bfs(start,end);

	}

	private static void bfs(int start, int end) {
		boolean isFind = false;

		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(start, 0));
		visited[start] = true;

		while (!q.isEmpty()) {
			Node cn = q.poll();
			int start2 = cn.idx;

			if (start2 == end) { // 더이상 탐색할 필요 없으니 여기서 끝내도 무방하다.
				isFind = true;
				System.out.println(cn.cnt);
				break;
			}

			for (int i = 1; i <= N; i++) { // 아직 방문 안하고 관계가 있는 촌수 탐색
				if (!visited[i] && adj[start2][i] == 1) {
					visited[i] = true;
					q.offer(new Node(i, cn.cnt + 1));
				}
			}
		}

		if (!isFind)
			System.out.println(-1); // 촌수관계 X

	}
}
