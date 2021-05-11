package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_Bronze1_2839_설탕배달 {

	static int T, N; // T: 테스트케이스, M: 목표 kg
	static boolean[] visited; // bfs 돌릴 때 방문한지 체크

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 입력 M
		visited = new boolean[5001]; // 3kg <= M <= 5000kg

		// 풀이1 - 그리디
		if (N == 4 || N == 7) {
			System.out.println(-1);
		} else if (N % 5 == 0) {
			System.out.println(N / 5);
		} else if (N % 5 == 1 || N % 5 == 3) {
			System.out.println((N / 5) + 1);
		} else if (N % 5 == 2 || N % 5 == 4) {
			System.out.println((N / 5) + 2);
		}

		// 풀이2 - BFS
		System.out.println(bfs());
	}

	private static int bfs() { // 3kg, 5kg를 돌린다.
		Queue<int[]> queue = new LinkedList<>();
		visited[0] = true; // 0kg부터 시작
		queue.offer(new int[] { 0, 0 }); // 0kg부터, Count==0

		int n; // 해당 케이스의 값을 저장하기 위한 변수
		while (!queue.isEmpty()) {
			int[] current = queue.poll(); // queue의 kg 부분

			if (current[0] == N)
				return current[1]; // 입력N을 만나면 최소 카운트를 전달하고 종료

			n = current[0] + 5; // 5kg 증가
			if (n <= 5000 && !visited[n]) { // 5000kg보다 작고 방문한 적이 없을 때
				visited[n] = true; // 방문처리하고
				queue.offer(new int[] { n, current[1] + 1 }); // 카운트 늘려서 큐에 추가
			}
			n = current[0] + 3; // 3kg 증가
			if (n <= 5000 && !visited[n]) {
				visited[n] = true; // 방문 처리하고
				queue.offer(new int[] { n, current[1] + 1 }); // 카운트 늘려서 큐에 추가
			}
		}
		return -1; // M을 만나지 못하면 -1 출력
	}

}
