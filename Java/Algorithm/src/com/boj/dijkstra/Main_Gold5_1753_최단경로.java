package com.boj.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold5_1753_최단경로 {
	static int V, E, K;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Point>[] list;

	static class Point implements Comparable<Point> {
		int end;
		int value;

		public Point(int end, int value) {
			super();
			this.end = end;
			this.value = value;
		}

		@Override
		public int compareTo(Point o) {
			return this.value - o.value;
		}

		@Override
		public String toString() {
			return "Point [end=" + end + ", value=" + value + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수

		K = Integer.parseInt(br.readLine())-1; // 시작점

		// 각 ArrayList의 list에 Point 할당
		list = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			list[i] = new ArrayList<Point>(); // 2차원 리스트 생성
		}
		// input
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());
			list[start].add(new Point(end, val));
		}
		// input 확인
		/*
		for (int i = 0; i < V; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < list[i].size(); j++) {
				System.out.print(list[i].get(j) + " ");
			}
			System.out.println();
		}
		 */
		// 다익스트라 알고리즘은 distance가 제일 큰 값에서 시작한다.
		distance = new int[V];
		Arrays.fill(distance, Integer.MAX_VALUE);
		// 방문했는지 안 했는지 확인하는 visited
		visited = new boolean[V];

		
		
		// 우선순위 큐를 사용하면 성능이 더 좋아진다.
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		// 시작점을 설정해주고
		pq.add(new Point(K,0));
		distance[K] = 0; // 시작점에서 시작점 거리는 0
		
		while (!pq.isEmpty()) {
			// 다음에 방문할 V설정
			Point current = pq.poll();
			int cur_end = current.end;
			// 방문했기때문에 visited = true
//			if(visited[cur_end]) continue;
			visited[cur_end] = true;

			/*
			for (int i = 0; i < list[current].size(); i++) {
				// 현재 V에서 다음 V를 차근차근 넣어서 우선순위 큐에 넣는다.
				int next = list[current].get(i).end; // 다음 V
				int value = list[current].get(i).value; // 현재V -> 다음V (가중치)

				if (distance[next] > distance[current] + value) { // 한 번도 가지 않은 곳일 때
					distance[next] = value + distance[current];
					pq.add(next);
				}
			}
			*/
			for(Point points : list[cur_end]) {
				if(distance[points.end] > distance[cur_end] + points.value) {
					distance[points.end] = distance[cur_end] + points.value;
					pq.add(new Point(points.end, distance[points.end]));
				}
			}
			
		}
		// distance는 0번부터 i번째까지의 최단경로
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < V; i++) {
			if(distance[i] != Integer.MAX_VALUE) {
				sb.append(distance[i]).append('\n');
			} else {
				sb.append("INF").append('\n');
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
