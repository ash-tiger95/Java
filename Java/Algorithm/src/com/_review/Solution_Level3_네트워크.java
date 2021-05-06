package com._review;

public class Solution_Level3_네트워크 {

	static int Ans;
	static boolean[] visited;

	private static int solution(int n, int[][] computers) {
		Ans = 0;
		visited = new boolean[n]; // 정점 방문 처리

		for (int i = 0; i < n; i++) {
			if (!visited[i]) { // 방문하지 않은 정점인 경우
				visited[i] = true;
				dfs(i, n, computers); // 깊이 우선 탐색
				Ans++;
			}
		}

		return Ans;
	}

	private static void dfs(int start, int n, int[][] computers) { // start(열)를 기준으로 행 검사
		for (int j = 0; j < n; j++) {
			if (start == j) {
				continue;
			}

			if (!visited[j] && computers[start][j] == 1) {
				visited[j] = true; // 방문 처리 후 DFS
				dfs(j, n, computers);
			}
		}
	}

	public static void main(String[] args) {
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		System.out.println(solution(n, computers));
	}

}
