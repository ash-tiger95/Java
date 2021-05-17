package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold3_17472_다리만들기2 {
	static int R, C;
	static int[][] map;   // test
	static int isLandIdx;
	static Queue<int[]> queue;
	static boolean[][] visited;
	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };
	static int[][] graph;

	static class Node implements Comparable<Node> {
		int idx;
		int dist;

		public Node(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", dist=" + dist + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[R][C];
		int cnt = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 1) {
					islandBFS(i, j, cnt); // 섬 번호 매기기
					cnt++;
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("cnt: " + cnt);

		graph = new int[cnt][cnt];
		for (int i = 0; i < cnt; i++) {
			Arrays.fill(graph[i], 101); // NxM = 100
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 1) {
					makeGraph(i, j, map[i][j]);
				}
			}
		}
		
		
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}

		// 프림으로 MST 뽑자
		// graph[cnt][cnt]에서 0,1번은 이용 안 한다.(섬 번호가 2번부터 시작, 2~cnt-1번까지 이용)
		System.out.println(prim(cnt));

	}

	static int prim(int cnt) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		Node[] nodes = new Node[cnt];
		for (int i = 2; i < cnt; i++) {
			if (i == 2) {
				nodes[i] = new Node(i, 0); // 시작점을 2번부터 시작
			} else {
				nodes[i] = new Node(i, 101); // 최대값으로 초기화
			}
			pq.offer(nodes[i]);
		}
        System.out.println(pq.toString());

		int sum = 0;
		while (!pq.isEmpty()) {
			Node front = pq.poll();
			if (front.dist == 101) {
				return -1;
			}
			sum += front.dist;
			for (int i = 2; i < cnt; i++) { // 모든 섬들이랑 비교
            	System.out.println("for: " + pq.toString());
				Node child = nodes[i];
                System.out.println(front.idx+" Node child: " + child.toString());
				if (pq.contains(child)) {
					if (child.dist > graph[front.idx][i]) {
						child.dist = graph[front.idx][i];
						pq.remove(child);
						pq.offer(child);
					}
				}
			}
            System.out.println(pq.toString());
		}
		return sum;
	}

	private static void makeGraph(int i, int j, int value) {
		for (int d = 0; d < 4; d++) {
			for (int len = 1;; len++) {
				int ny = i + dy[d] * len;
				int nx = j + dx[d] * len;

				if (!boundary(ny, nx)) // map을 벗어날 경우
					break;

				if (map[ny][nx] == 0) // 바다를 만날 때
					continue;
				else if (map[ny][nx] == value) // 자기 섬 만날 때
					break;
				else { // 다른 섬을 만나면
					if (len > 2) {
						graph[value][map[ny][nx]] = graph[map[ny][nx]][value] = Math.min(graph[map[ny][nx]][value],
								len - 1); // 최소길이를 계속 업데이트 해준다.
						// 1 0 0 2인 경우 : len에는 2를 포함하고 있으니깐 -1 해줘야지.
					}
					break; // 만나면 끝
				}
			}
		}
	}

	private static void islandBFS(int i, int j, int cnt) { // 섬의 번호 매기기
		queue = new LinkedList<>();
		queue.offer(new int[] { i, j });

		while (!queue.isEmpty()) {
			int[] c = queue.poll();
			visited[c[0]][c[1]] = true;
			map[c[0]][c[1]] = cnt;

			for (int d = 0; d < 4; d++) {
				int ny = c[0] + dy[d];
				int nx = c[1] + dx[d];
				if (!boundary(ny, nx)) {
					continue;
				}
				if (!visited[ny][nx] && map[ny][nx] == 1) {
					queue.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
				}
			}
		} // while
	}

	private static boolean boundary(int ny, int nx) { // 외곽 검사
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
