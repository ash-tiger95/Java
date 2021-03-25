package com.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_Silver1_1946_신입사원 {

	static int T, N, Ans;
	static int[][] map;
	static ArrayList<Node> list;

	static class Node implements Comparable<Node> {
		int doc, iv;

		public Node(int doc, int iv) { // 서류 순위, 면접 순위
			super();
			this.doc = doc;
			this.iv = iv;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.doc, o.doc);
		}

		@Override
		public String toString() {
			return "Node [doc=" + doc + ", iv=" + iv + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			Ans = 0;
			Collections.sort(list); // 정렬 필수
			int standard = list.get(0).iv;

			for (Node cn : list) {
				if (cn.iv <= standard) {
					Ans++;
					standard = cn.iv; // 면접 순위 업데이트
				}
			}

			System.out.println(Ans);
		}

	}

}
