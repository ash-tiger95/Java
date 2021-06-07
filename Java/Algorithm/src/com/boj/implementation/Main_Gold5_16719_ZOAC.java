package com.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main_Gold5_16719_ZOAC {

	static String in;
	static PriorityQueue<Node> pq;
	static boolean[] visited;

	static class Node implements Comparable<Node> {
		char alpha;
		int idx;

		public Node(char alpha, int idx) {
			super();
			this.alpha = alpha;
			this.idx = idx;
		}

		@Override
		public int compareTo(Node o) {
			if (this.alpha - o.alpha == 0) {
				return Integer.compare(this.idx, o.idx);
			} else {
				return Integer.compare(this.alpha, o.alpha);
			}
		}

		@Override
		public String toString() {
			return "[" + alpha + " " + idx + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		in = br.readLine();
		visited = new boolean[in.length()];

		pq = new PriorityQueue<>();
		for (int i = 0; i < in.length(); i++) {
			pq.offer(new Node(in.charAt(i), i));
		}

		int startIdx = -1; // startIdx이상만을 검사
		ArrayList<Node> tempSave = new ArrayList<>(); // startIdx 미만 값을 임시 저장

		while (!pq.isEmpty()) {
			Node cn = pq.poll();

			if (cn.idx >= startIdx) { // startIdx 이상 값일 때
				visited[cn.idx] = true;

				startIdx = cn.idx;

				if (isFinish(startIdx)) { // ★. startIdx 이상을 더이상 검사할 필요 없다면
					int temp = -1;

					for (int i = 0; i < in.length() - 1; i++) {
						if (visited[i] && !visited[i + 1]) {
							temp = i;
						}
					}

					startIdx = temp; // 새로운 startIdx를 설정한다.
				}
				sb.append(print()).append("\n");

				if (tempSave.size() > 0) {
					pq.addAll(tempSave);
					tempSave.clear();
				}
			} else {
				tempSave.add(cn); // 임시 저장
			}

		}
		System.out.println(sb);

		br.close();
	}

	private static boolean isFinish(int idx) {
		for (int i = idx; i < in.length(); i++) {
			if (!visited[i]) {
				return false; // 방문할 곳이 더 남아있으면
			}
		}
		return true; // 더 이상 방문할 곳이 없다.
	}

	private static String print() {
		String temp = "";
		for (int i = 0; i < in.length(); i++) {
			if (visited[i]) {
				temp += in.charAt(i);
			}
		}
		return temp;
	}
}
