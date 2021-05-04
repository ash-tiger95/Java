package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상정렬이란? 
 * 일을 하는 순서를 찾는 알고리즘 
 * 즉, 방향 그래프에 존재하는 각 정점들의 선행 순서를 위배하지 않으면서 모든 정점을 나열하는 것
 * 
 * 특징 1. 하나의 방향 그래프에는 여러 위상 정렬이 가능하다.
 * 
 * @author jugia
 *
 */
public class Main_Gold2_2252_줄세우기 {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학생 수, 1 <= N <= 32,000
		M = Integer.parseInt(st.nextToken()); // 비교 횟수, 1 <= M <= 100,000

		int[] indegree = new int[N + 1]; // 진입차수, 자신을 가리키고 있는 화살표의 개수
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// a가 b 앞에 서야한다.
			list[a].add(b);
			indegree[b]++;
		}

		// Topological Sorting
		Queue<Integer> q = new LinkedList<>();

		// indegree가 0일 때 큐에 넣는다. 0은 자신 앞에 아무도 없다는 의미
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int c = q.poll();
			System.out.print(c + " ");

			for (int i = 0; i < list[c].size(); i++) { // c와 연결된 점의 indegree를 -1
				int check = list[c].get(i);

				indegree[check]--;
				if (indegree[check] == 0) {
					q.offer(check);
				}
			}
		}
	}
}
