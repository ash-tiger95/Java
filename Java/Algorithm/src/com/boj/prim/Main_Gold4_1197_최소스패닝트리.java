package com.boj.prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold4_1197_최소스패닝트리 { // 메모리초과, 런타임에러
	static int V, E;
	static int[][] adj; // 인접행렬
	static int Ans;
	public static void main(String[] args) throws IOException {
		// int형 범위: –2,147,483,648 ~ 2,147,483,647
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // 1 <= V <= 10,000
		E = Integer.parseInt(st.nextToken()); // 1 <= E <= 100,000

		adj = new int[V][V]; // 10,000 * 10,000 = 100,000,000
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			adj[a][b] = adj[b][a] = c;
		}

		prim();
		System.out.println(Ans);
	}

	private static void prim() {
		boolean[] check = new boolean[V]; // 정점을 골랐는지
		int[] parents = new int[V]; // 최소신장트리의 구조를 저장할 배열
		int[] value = new int[V]; // 현재 선택된 정점들로부터 도달할 수 있는 최소거리

		Arrays.fill(value, Integer.MAX_VALUE);

		// 0번 정점부터 시작
		parents[0] = -1;
		value[0] = 0;

		for (int i = 0; i < V - 1; i++) { // for(): 단순히 V-1번 돌기 위함 (위에서 0을 넣었으니 V-1번)

			// 가지 않은 점 중 value가 가장 작은 값 + index 찾기
			int min = Integer.MAX_VALUE;
			int index = -1;
			for (int j = 0; j < V; j++) {
				if (!check[j] && value[j] < min) {
					min = value[j];
					index = j;
				}
			}

			// 찾으면 방문처리
			check[index] = true;

			// 연결된 점을 가지고 parents와 value를 업데이트 시켜준다.
			for (int j = 0; j < V; j++) {
				if (!check[j] && adj[index][j] != 0 && value[j] > adj[index][j]) {
					parents[j] = index;
					value[j] = adj[index][j];
				}
			}

		}
		Ans = 0;
		for (int i = 0; i < V; i++) {
			Ans += value[i];
		}

	}

}
