package com.boj.sort.topologysort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold4_2056_작업 {
	static int N, Ans;

	static boolean[] complete;
	static int[] indegree;
	static ArrayList<Node>[] list;
	static Queue<Node> q;

	static class Node {
		int next, time;

		public Node(int next, int time) {
			super();
			this.next = next;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [next=" + next + ", time=" + time + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		indegree = new int[N];
		complete = new boolean[N];
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) { // i ~ N-1 차례대로 정점
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시간
			int b = Integer.parseInt(st.nextToken()); // 연결된 정점 개수
			if (b == 0) { // 시작점
				list[i].add(new Node(i, a));
				indegree[i] = 0;
			} else {
				for (int j = 0; j < b; j++) {
					int c = Integer.parseInt(st.nextToken()) - 1; // 선행정점
					list[c].add(new Node(i, a)); // c->i
					indegree[i]++;
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(list[i]);
//		}
//		System.out.println(Arrays.toString(indegree));

		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] == 0) {
				q.add(new Node(list[i].get(0).next, list[i].get(0).time));

			}
		}

		Ans = 0;

		while (!q.isEmpty()) {
			int size = q.size();
//			System.out.println(size + " " + q);
			Ans++;
			for (int t = 0; t < size; t++) {
				Node cn = q.poll();

				int time = --cn.time;
				if (time == 0) {
					for (int i = 0; i < list[cn.next].size(); i++) {
						Node nn = list[cn.next].get(i);
						indegree[nn.next]--;
						if (indegree[nn.next] == 0) {
							q.add(nn);
						}
					}
				} else {
					q.add(new Node(cn.next, cn.time));
				}

			}

		}
		System.out.println((Ans));

	}

}
