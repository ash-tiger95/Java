package com.boj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver2_10819_차이를최대로 {

	static int N, Ans;
	static int[] in;
	static boolean[] isSelected;
	static int[] out;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		in = new int[N];
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		isSelected = new boolean[N];
		out = new int[N];
		Ans = -1;
		nPr(0);
		System.out.println(Ans);
	}

	private static void nPr(int cnt) {
		if (cnt == N) {
			int temp = 0;
			for(int i=0;i<N-1;i++) {
				temp += Math.abs(in[out[i]] - in[out[i+1]]);
			}
			
			if(temp > Ans) {
				Ans = temp;
			}
			
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i]) {
				continue;
			}

			out[cnt] = i;
			isSelected[i] = true;
			nPr(cnt + 1);
			isSelected[i] = false;
		}
	}
}
