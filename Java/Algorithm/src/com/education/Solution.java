package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, INPUT;
	static int[][] cook;
	static int R, N;
	static int[] nums;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			INPUT = Integer.parseInt(in.readLine());
			cook = new int[INPUT][INPUT];
			for (int i = 0; i < INPUT; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < INPUT; j++) {
					cook[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			N = INPUT;
			R = INPUT / 2;
			nums = new int[R];
			min = Integer.MAX_VALUE;
			nCr(0, 0, new boolean[N]);
			System.out.println("#"+t+" "+min);
		} // for T
	}

	public static void nCr(int start, int count, boolean[] b) {
		if (count == R) {
			int[] excep = new int[R];
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (!b[i]) {
					excep[idx] = i;
					idx++;
				}
			}
			
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < R; j++) {
					if(i!=j) {
						sum1 += cook[nums[i]][nums[j]];
					}
				}
			}
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < R; j++) {
					if(i!=j) {
						sum2 += cook[excep[i]][excep[j]];
					}
				}
			}
			int answer = Math.abs(sum1-sum2);
			if(min>answer)
				min = answer;

			return;
		}
		for (int i = start; i < N; i++) {
			if (!b[i]) {
				b[i] = true;
				nums[count]=i;
				nCr(i + 1, count + 1, b);
				b[i] = false;
			}
		}
	}
}
