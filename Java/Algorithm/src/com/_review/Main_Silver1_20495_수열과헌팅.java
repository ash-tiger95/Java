package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver1_20495_수열과헌팅 {

	static int N;
	static int[][] in, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		in = new int[N][2];
		ans = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			in[i][0] = Integer.parseInt(st.nextToken());
			in[i][1] = Integer.parseInt(st.nextToken());
		}

		// 최소 인덱스 구하기
		minIdx();
		maxIdx();

		for (int i = 0; i < N; i++) {
			sb.append(ans[i][0] + " " + ans[i][1]).append("\n");
		}
		System.out.println(sb);
	}

	private static void minIdx() {
		ans[0][0] = 1;
		for (int i = 1; i < N; i++) {
			int temp = -1;
			for (int j = i - 1; j >= 0; j--) {
				if (in[i][0] - in[i][1] <= in[j][0] + in[j][1]) {
					temp = j;
				} else {
					break;
				}
			}

			if (temp == -1) {
				ans[i][0] = i + 1;
			} else {
				ans[i][0] = temp + 1;
			}
		}

	}

	private static void maxIdx() {
		ans[N - 1][1] = N;

		for (int i = 0; i < N - 1; i++) {

			int temp = -1;
			for (int j = i + 1; j < N; j++) {
				if (in[i][0] + in[i][1] >= in[j][0] - in[j][1]) {
					temp = j;
				} else {
					break;
				}
			}

			if (temp == -1) {
				ans[i][1] = i + 1;
			} else {
				ans[i][1] = temp + 1;
			}

		}
	}
}
