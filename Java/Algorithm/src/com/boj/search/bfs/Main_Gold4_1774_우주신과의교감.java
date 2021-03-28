package com.boj.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 풀이)
 * 1. 일단 N 정보는 가지고 있는다.
 * 2. M 정보를 입력받으면 인접리스트에 거리가 0.0으로 저장해놓는다.
 * 3. N 정보를 각 인접리스트에 저장한다.
 * 4. PQ에서 cost가 최소인 것 부터 뽑아온다.
 * 5. visited[] 처리하면서 최소값을 구한다.
 * 6. 여기서 추가적으로 visited이 true로 바뀔 때마다 개수를 세어 최적화시킨다. 
 * -> PQ에 들어간 모든 간선을 검사할 필요가 없다. (은근 실행시간 차이 크다.)
 */

public class Main_Gold4_1774_우주신과의교감 {

	static int N, M;
	static double Ans;
	static int[][] map;
	static ArrayList<Node>[] adj;

	static class Node implements Comparable<Node> {
		int dest;
		double cost;

		public Node(int dest, double cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "[dest=" + dest + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Node o) { // cost가 작은 것이 우선
			return Double.compare(this.cost, o.cost);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 우주신들의 개수, N <= 1,000
		M = Integer.parseInt(st.nextToken()); // 신들과의 통로 개수, M <= 1,000

		map = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken()) - 1;
			map[i][1] = Integer.parseInt(st.nextToken()) - 1;
		}

		adj = new ArrayList[N + 1]; // 인접 리스트 만들기
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			// 통로가 있으면 거리가 0.0으로 저장한다.
			adj[a].add(new Node(b, 0.0)); 
			adj[b].add(new Node(a, 0.0));
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				// 각 인접리스트에 거리 정보를 저장한다.
				double len = Math.sqrt(Math.pow(map[i][0] - map[j][0], 2) + Math.pow(map[i][1] - map[j][1], 2));
				adj[i].add(new Node(j, len));
				adj[j].add(new Node(i, len));
			}
		}

		Ans = 0.0;
		prim();
		System.out.println(String.format("%.2f", Ans));

	}

	private static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];

		int count = 1; // 다 채웠으면 종료시키기 위함 (있으면 488ms, 없으면 1188ms)
		visited[0] = true;
		pq.addAll(adj[0]);

		while (!pq.isEmpty()) {
			if (count == N) { // 모든 정점을 연결했으면 종료
				break;
			}
			Node cn = pq.poll();

			if (visited[cn.dest]) {
				continue;
			}

			Ans += cn.cost; // 최솟값만 저장된다.
			count++;
			visited[cn.dest] = true;
			pq.addAll(adj[cn.dest]);
		}
	}

}
