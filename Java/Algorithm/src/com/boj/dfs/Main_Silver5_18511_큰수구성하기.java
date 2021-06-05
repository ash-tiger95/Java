package com.boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver5_18511_큰수구성하기 {

	static int N, K, Ans;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		in = new int[K];
		for (int i = 0; i < K; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(in);

		dfs(0);

		System.out.println(Ans);

	}

	private static void dfs(int cur) {
//		System.out.println(cur);
		if (cur > N) {
			System.out.println("return");
			return;
		}

		if (Ans < cur) {
			Ans = cur;
//			System.out.println("CHAGER ANSWER: "+ Ans);
		}

		for (int i = K - 1; i >= 0; i--) {
			dfs(cur * 10 + in[i]);
		}
	}
}
