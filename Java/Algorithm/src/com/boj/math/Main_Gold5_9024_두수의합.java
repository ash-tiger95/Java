package com.boj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_9024_두수의합 {

	static int T, N, K, max, Ans;
	static int[] in, out;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			in = new int[N];
			for (int i = 0; i < N; i++) {
				in[i] = Integer.parseInt(st.nextToken());
			}

			out = new int[N];
			max = Integer.MAX_VALUE;
			Ans = 0;
			nCr(0, 0);
			sb.append(Ans).append("\n");
			
		} // for T
		System.out.println(sb);
	}

	private static void nCr(int cnt, int cur) {
		if (cnt == 2) {
			int temp = 0;
			int count = 0;
			for(int i=0;i<2;i++) {
				temp+=in[out[i]];
			}

			if (Math.abs(K - temp) < max) {
				max = Math.abs(K - temp);
				Ans = 1;
			} else if (Math.abs(K - temp) == max) {
				Ans++;
			}

			return;
		}

		for (int i = cur; i < N; i++) {
			out[cnt] = i;
			nCr(cnt+1,i+1);
		}
	}
	
}
