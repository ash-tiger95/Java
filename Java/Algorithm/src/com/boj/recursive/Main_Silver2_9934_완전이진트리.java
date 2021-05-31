package com.boj.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver2_9934_완전이진트리 {

	static int N;
	static int[] in;
	static StringBuilder[] ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		in = new int[(int) Math.pow(2, N) - 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < (int) Math.pow(2, N) - 1; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = new StringBuilder[N];
		for (int i = 0; i < N; i++) {
			ans[i] = new StringBuilder();
		}

		recur(0, in.length - 1, 0);

		for (int i = 0; i < N; i++) {
			System.out.println(ans[i]);
		}

	}

	public static void recur(int s, int e, int floor) {
		if (floor == N) {
			return;
		}

		int m = (s + e) / 2;
		ans[floor].append(in[m] + " ");

		recur(s, m - 1, floor + 1);
		recur(m + 1, e, floor + 1);
	}
}
