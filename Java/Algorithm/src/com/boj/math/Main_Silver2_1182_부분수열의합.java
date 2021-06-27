package com.boj.math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Silver2_1182_부분수열의합 {

	static int N, S, ans;
	static int[] num;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정수 개수, 1 ≤ N ≤ 20
		S = Integer.parseInt(st.nextToken()); // 만들기, |S| ≤ 1,000,000

		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken()); // 정수, |num[i]| ≤ 100,000
		}

		isSelected = new boolean[N];
		ans = 0;
//		subSet(0);
		recur(0, 0);

//		if(S == 0) {
//			ans--;
//		}
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static void recur(int cnt, int sum) { // 128ms
		if (cnt == N) {
			return;
		}

		if (sum + num[cnt] == S) {
			ans++;
		}

		recur(cnt + 1, sum);
		recur(cnt + 1, sum + num[cnt]);
	}

	private static void subSet(int cnt) { // 208ms
		if (cnt == N) {
			int sum = 0;

			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					sum += num[i];
				}
			}

			if (sum == S) {
				ans++;
			}
			return;
		}

		isSelected[cnt] = true;
		subSet(cnt + 1);
		isSelected[cnt] = false;
		subSet(cnt + 1);
	}
}
