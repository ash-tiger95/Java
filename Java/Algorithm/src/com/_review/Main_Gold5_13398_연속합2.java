package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_13398_연속합2 {

	static int N;
	static int[] in;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		
		in = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}
		
		
	}
}
