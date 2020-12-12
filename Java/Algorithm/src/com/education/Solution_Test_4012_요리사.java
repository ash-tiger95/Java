package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Test_4012_요리사 {
	static int T,N,Ans;
	static int[][] map;
	static boolean[] isSelected;
	static int[] result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T;tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = new int[N/2];
			
			
			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
		
	}
	private static void Permutation(int cnt) {
		if(cnt == N/2) {
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(isSelected[i]) {
				continue;
			}
			
			isSelected[i] = true;
			result[cnt] = i;
			Permutation(cnt+1);
			isSelected[i] = false;
			result[cnt] = 0;
		}
	}
}
