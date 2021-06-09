package com.boj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver2_6603_로또 {

	static int K;
	static int[] in, out;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());

			if (K == 0) {
				break;
			}

			in = new int[K];
			for (int i = 0; i < K; i++) {
				in[i] = Integer.parseInt(st.nextToken());
			}

			out = new int[6];
			nCr(0, 0);

			sb.append("\n");
		} // while

		System.out.println(sb);
	}

	private static void nCr(int cnt, int cur) {
		if (cnt == 6) {
			
			for (int i = 0; i < 6; i++) {
				sb.append(in[out[i]]).append(" ");
			}
			sb.append("\n");

			return;
		}

		for (int i = cur; i < K; i++) {
			out[cnt] = i;
			nCr(cnt + 1, i + 1); // ★ i 주의
		}
	}
}
