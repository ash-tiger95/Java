package com.boj.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Silver2_11047_동전0 {

	static int N, K, ans;
	static int[] coin;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 동전 수, 1 ≤ N ≤ 10
		K = Integer.parseInt(st.nextToken()); // 만들기, 1 ≤ K ≤ 100,000,000

		coin = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); // 오름차순으로 주어진다.
			coin[i] = Integer.parseInt(st.nextToken());
		}

		ans = 0;

		for (int i = N - 1; i >= 0; i--) {
			if (K >= coin[i]) {
				ans += K / coin[i];
				K %= coin[i];
			}

			if (K == 0) { // 효율성
				break;
			}
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

}
