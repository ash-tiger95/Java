package com.boj.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이) 단순히 재귀로 하려 했지만 시간초과.
 * 또 다른 풀이) DP
 * 
 * @author jugia
 *
 */
public class Main_Gold5_5557_1학년 {

	static int N, Ans;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		in = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		Ans = 0;
		recur(0, 0);
		System.out.println(Ans);
	}

	private static void recur(int cnt, int value) {
		if (value < 0 || value > 20) {
			return;
		}
		
		if (cnt == N - 1) {
			if (value == in[N - 1]) {
				Ans++;
			}
			return;
		}

		recur(cnt + 1, value + in[cnt]);

		recur(cnt + 1, value - in[cnt]);
	}
}
