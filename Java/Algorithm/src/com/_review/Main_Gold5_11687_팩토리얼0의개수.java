package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_11687_팩토리얼0의개수 {

	static int M;
	static long N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 1 <= M <= 100,000,000

		
		if(M % 5 == 0) {
			System.out.println(-1);
		} else {
			int temp = M/5;
			
			System.out.println(M*5 - temp*5);
		}
		
	}
}
