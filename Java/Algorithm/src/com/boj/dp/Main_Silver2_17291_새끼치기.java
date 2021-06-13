package com.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver2_17291_새끼치기 {

	static int N;
	static Queue<Integer> insect;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 20, N년 말에 존재하는 벌레 수 구하기

		// 내 풀이: DFS, 24004KB, 180ms
		insect = new LinkedList<>();
		insect.offer(1); // 1년에 한 마리 탄생

		if (N == 1) {
			System.out.println(1);
		} else {
			dfs(1);
		}

		// 다른 풀이: DP, 14088KB, 128ms
		int[] dp = new int[21];
		dp[0] = dp[1] = 1;

		for (int i = 1; i < N; ++i) {
			dp[i + 1] = dp[i] * 2;
			
			if (i + 1 - 3 >= 1 && (i - 2) % 2 == 1)
				dp[i + 1] -= dp[i - 3];
			if (i + 1 - 4 >= 1 && (i - 3) % 2 == 0)
				dp[i + 1] -= dp[i - 4];
		}

		System.out.println(dp[N]);
		
		br.close();
	}

	private static void dfs(int year) {
		if (year == N) {
//			System.out.println(insect);
			System.out.println(insect.size());

			return;
		}

//		System.out.println("year: " + year +" " +insect);

		int size = insect.size();
		for (int i = 0; i < size; i++) {
			int cur = insect.poll();

			if (cur % 2 == 1) {
				if (cur + 3 == year + 1) {
					insect.offer(year + 1);
				} else {
					insect.offer(cur);
					insect.offer(year + 1);
				}
			} else {
				if (cur + 4 == year + 1) {
					insect.offer(year + 1);
				} else {
					insect.offer(cur);
					insect.offer(year + 1);
				}
			}

		}
//		System.out.println("after: " + year +" " +insect);
		dfs(year + 1);
	}
}
