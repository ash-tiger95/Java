package com.boj.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Two Pointer 기본 문제
 * 
 * @author jugia
 *
 */
public class Main_Gold4_1806_부분합 {

	static int N, S, Ans;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Ans = Integer.MAX_VALUE;

		N = Integer.parseInt(st.nextToken()); // 길이 N짜리 수열
		S = Integer.parseInt(st.nextToken()); // 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이 구하기

		in = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 0;
		long sum = 0;
		
		System.out.println(Arrays.toString(in));

		while (end <= N) {
			System.out.println(start + " "+end +" " +sum);
			if (sum >= S) {
				Ans = Math.min(Ans, end - start); // S를 넘을 때마다 최소 길이 검사
				sum -= in[start++];
			} else {
				sum += in[end++];
			}

		}

		System.out.println(Ans == Integer.MAX_VALUE ? 0 : Ans);
		
		br.close();
	}
}
