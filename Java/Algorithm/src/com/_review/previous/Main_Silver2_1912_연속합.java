package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver2_1912_연속합 {
	static int N,answer;
	static int[] input;
	static Integer[] memoi;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new int[N];
		for(int i=0;i<N;i++) {
			input[i]= Integer.parseInt(st.nextToken());
		}
		
		memoi = new Integer[N];
		memoi[0] = input[0];
		answer = input[0];
		
		recur(N-1);
		System.out.println(answer);
		
	}

	private static int recur(int N) {
		if(memoi[N] == null) {
			memoi[N] = Math.max(recur(N-1)+input[N], input[N]);
			
			answer = Math.max(memoi[N], answer);
		}
		
		return memoi[N];
	}
}
