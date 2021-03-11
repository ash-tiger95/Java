package com.boj.sort.topologysort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold2_2252_줄세우기 {
	static int N, M;
	static int[] indegree; // 자신을 가리키고 있는 화살표 개수
	static ArrayList<Integer>[] list; // a->b 연결 정보

	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 핵생 수
		M = Integer.parseInt(st.nextToken()); // 간선 수

		indegree = new int[N];
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			list[a].add(b);
			indegree[b]++; // 자신이 연결된 정점 개수
		}

		// 위상 정렬은 큐가 일반적(스택도 가능)
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] == 0) { // 자신에게 연결되지 않은 점이 시작점
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cp = q.poll();
			System.out.print(cp+1+ " ");
			
			for(int i=0;i<list[cp].size();i++) {
				int next = list[cp].get(i);
				indegree[next]--;
				if(indegree[next] == 0) {
					q.add(next);
				}
			}
		}
		

	}

}
