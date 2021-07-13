package com.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver1_9205_맥주마시면서걸어가기 {

	static int T, N, sy, sx, ey, ex;
	static List<int[]> list;
	static boolean[] visited;
	static boolean isPossible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 편의점 개수

			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());

			list = new ArrayList<>();
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.add(new int[] { a, b });
			}

			st = new StringTokenizer(br.readLine());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());

			isPossible = false;
			visited = new boolean[N];
			bfs();

			sb.append(isPossible ? "happy" : "sad").append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sy,sx});
		
		while(!q.isEmpty()) {
			int[] cp = q.poll();
			
			if(Math.abs(cp[0]-ey) + Math.abs(cp[1]-ex) <= 1000) {
				isPossible = true;
				return;
			}
			
			for(int i=0;i<N;i++) {
				if(!visited[i]) {
					if(Math.abs(list.get(i)[0]-cp[0]) + Math.abs(list.get(i)[1]-cp[1]) <= 1000) {
						visited[i] = true;
						q.offer(new int[] {list.get(i)[0], list.get(i)[1]});
					}
				}
			}
		}
	}
	
	
}
