package com.test.kakao;

public class Solution_3번 {

	static int max, num, N;
	static int[][] memoi;
	static boolean[] visited;

	public static int[] solution(int n, int[] passenger, int[][] train) {
		int[] answer = {};
		N = n;
		memoi = new int[n][n];
		visited = new boolean[n];

		int[][] adj = new int[n][n];
		for (int i = 0; i < train.length; i++) { // adj[i][j]는 i->j로 가면 adj[i][j] 승객을 태울 수 있다.
			adj[train[i][0] - 1][train[i][1] - 1] = passenger[train[i][1] - 1];
			adj[train[i][1] - 1][train[i][0] - 1] = passenger[train[i][0] - 1];
		}

		// 0번 역부터 시작
		int[] distance = new int[n];
		distance[0] = passenger[0];

		for (int i = 0; i < N; i++) {
			if (adj[0][i] != 0) {

				dfs(adj, 0, i);
			}
		}
		return answer;
	}

	private static int dfs(int[][] adj, int start, int end) {
		for (int j = 0; j < N; j++) {
			if (adj[end][j] != 0 && !visited[j]) {
				visited[j] = true;
				dfs(adj, start, j);
				visited[j] = false;
			}
		}

		return adj[start][end];
	}

	public static void main(String[] args) {
		int n = 6;
		int[] passenger = { 1, 1, 1, 1, 1, 1 };
		int[][] train = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 3, 5 }, { 3, 6 } };
		System.out.println(solution(n, passenger, train));
	}
}
