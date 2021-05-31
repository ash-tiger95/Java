package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold4_15918_랭퍼드는수열쟁이야 {

	static int N, X, Y;
	static int[] out;
	static boolean[] used;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		out = new int[2*N + 1];
		out[X] = out[Y] = Y-X-1; // 고정
		used = new boolean[26];
		
	}
	
	private static void recur(int cur) {
		if(cur == N+1) {
			return;
		}
		
		if(cur == 1) {
			
		}
		
		for(int i=1;i<2*N + 1 - cur;i++) {
			if(out[i] != 0 && out[i+cur] != 0) {
				out[i] = cur;
				recur(cur+1);
			}
		}
	}
}
