package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver5_7568_덩치 {

	static int N;
	static int[][] in;
	static int[] ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		in = new int[N][2]; // (몸무게, 키)
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			in[i][0] = Integer.parseInt(st.nextToken());
			in[i][1] = Integer.parseInt(st.nextToken());
		}
		
		ans = new int[N];
		for(int i=0;i<N;i++) {
			int rank = 1;
			
			for(int j=0;j<N;j++) {
				if(i == j) {
					continue;
				}
				
				if(in[i][0] < in[j][0] && in[i][1] < in[j][1]) {
					rank++;
				}
				
				// 등수가 같은 경우를 계산할 필요없는게 어차피 밀린다.
			}
			
			ans[i] = rank;
		}
		
		for(int i=0;i<N;i++) {
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}
