package com.boj.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 
 * 응용문제) Gold4 3151 합이 0 (Two Pointer)
 * 
 * @author jugia
 *
 */
public class Main_Gold4_2473_세용액 {

	static int N;
	static long min;
	static long[] in, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		in = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(in);
		min = Long.MAX_VALUE;
		ans = new long[3];

		for (int i = 0; i < N - 2; i++) {

			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				long sum = in[i] + in[left] + in[right];

				if (Math.abs(sum) < min) { // 업데이트
					min = Math.abs(sum);
					ans[0] = in[i];
					ans[1] = in[left];
					ans[2] = in[right];

				}

				if (sum > 0) {
					right--;
				} else {
					left++;
				}
			}
		}

		Arrays.sort(ans);
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
		
		br.close();
	}
}
