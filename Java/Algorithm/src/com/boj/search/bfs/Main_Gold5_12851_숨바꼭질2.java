package com.boj.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어)
 * 이전시간과 현재시간을 이용한다.
 * 
 * @author jugia
 *
 */
public class Main_Gold5_12851_숨바꼭질2 { // 시간초과

	static int N, K, minTime, Case;
	static boolean[] visited;
	static int[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈, 0 <= N <= 100,000
		K = Integer.parseInt(st.nextToken()); // 동생, 0 <= K <= 100,000

		minTime = Integer.MAX_VALUE; // 수빈이가 동생을 찾는 가장 빠른 시간
		Case = 0; // 가장 빠른 시간의 경우의 수

		if (N >= K) {
			System.out.println((N - K) + "\n1");
			return;
		}

		time = new int[100001];

		bfs();

		System.out.println(minTime);
		System.out.println(Case);
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		time[N] = 1;

		while (!q.isEmpty()) {
			int cn = q.poll(); // 현재시간

			if (minTime < time[cn]) { // ★ 최소시간을 넘으면 뒤에는 더 이상 볼 필요 없다.
				return;
			}

			for (int i = 0; i < 3; i++) {
				int next;

				if (i == 0) { // 경우의 수
					next = cn + 1;
				} else if (i == 1) {
					next = cn - 1;
				} else {
					next = cn * 2;
				}

				if (next < 0 || next > 100000) {
					continue;
				}

				if (next == K) { // K를 만나면 업데이트
					minTime = time[cn];
					Case++;
				}

				// 처음 방문하는 곳 || ★ 같은 시간에 방문했다면 Queue에 한 번 더 넣는다.
				// 이유는? 최소시간일 경우, 경우의 수에 추가하기 위해서
				if (time[next] == 0 || time[next] == time[cn] + 1) {
					q.offer(next);
					time[next] = time[cn] + 1;
				}
				System.out.println(cn+ " " + next);
				System.out.println(Arrays.toString(time));
			}
		}
	}
}
