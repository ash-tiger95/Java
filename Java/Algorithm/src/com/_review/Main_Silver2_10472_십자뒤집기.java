package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver2_10472_십자뒤집기 {
	
	static int T, Ans;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0;t<T;t++) {
			map = new char[3][3];
			for(int i=0;i<3;i++) {
				map[i] = br.readLine().toCharArray();
			}

			
			
			
		} // for T

		System.out.println(sb);
		br.close();
	}
}
