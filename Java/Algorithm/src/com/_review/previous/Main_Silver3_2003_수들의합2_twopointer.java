package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver3_2003_수들의합2_twopointer {

	static int N, M, Ans;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // map 크기, 1 <= N <= 10,000
		M = Integer.parseInt(st.nextToken()); // 구간합이 M이 되는 경우의 수 찾기, 1 <= M <= 300,000,000

		st = new StringTokenizer(br.readLine());
		map = new int[N + 1]; // ★. e는 포함되지 않기 때문에 N까지 들어가는 경우를 생각해야 한다.
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		Ans = 0;

		// 1. 포인터 2개 준비 (항상 s <= e, s가 가리키는 칸은 포함o / e가 가리키는 칸은 포함x)
		int s = 0;
		int e = 0;
		long sum = 0;

		while (e <= N) { // e는 N을 포함하지 않는다. 그러므로 N까지 검사해야하고 배열의 크기도 N+1이 되어야 한다.
//			System.out.println(s + " " + e + " " + sum);

			if (sum >= M) { // 2. 만약 현재 부분합이 M 이상이거나, 이미 e = N이면 s++
				sum -= map[s++];
			} else if (sum < M) {
				sum += map[e++];
			}

			if (sum == M) { // 4. 현재 부분합이 M과 같으면 결과++
				Ans++;
			}
		}
		System.out.println(Ans);

	}

}
