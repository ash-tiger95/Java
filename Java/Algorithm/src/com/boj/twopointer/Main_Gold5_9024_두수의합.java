package com.boj.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 다른 방향 투포인터
 * @author jugia
 *
 */
public class Main_Gold5_9024_두수의합 {

	static int T, N, K, Ans;
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

			Arrays.sort(in);
			Ans = 0;
			int gap = Integer.MAX_VALUE;

			int left = 0;
			int right = N - 1;

			while (left < right) {
				int sum = in[left] + in[right];

				if (Math.abs(K - sum) < gap) {
					gap = Math.abs(K - sum);
					Ans = 1;
				} else if (Math.abs(K - sum) == gap) {
					Ans++;
				}

				if (K == sum) {
					left++;
					right--;
				} else if (K < sum) {
					right--;
				} else {
					left++;
				}
			}
			
			sb.append(Ans).append("\n");
			
		} // for T
		System.out.print(sb);
	}
}
