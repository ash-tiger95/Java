package com.boj.slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver3_2003_수들의합2 {
	static int N, M, Ans;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // map 크기, 1 <= N <= 10,000
		M = Integer.parseInt(st.nextToken()); // 구간합이 M이 되는 경우의 수 찾기, 1 <= M <= 300,000,000

		st = new StringTokenizer(br.readLine());
		map = new int[N];
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		Ans = 0;
		
		for(int w=0;w<N;w++) { // Window 크기 결정
			int left = 0, right = w; // left부터 right까지 윈도우의 범위
			int sum =0;
			
			// 초기 sum값 세팅 -> 윈도우 범위만큼의 값을 최초로 가짐
			for(int i=0;i<=right;i++) {
				sum += map[i];
			}
			
			if(sum== M) {
				Ans++;
			}
			
			right++;
			
			while(right<N) {
				// [윈도우]의 다음 범위는 [윈도우]-map[left]+map[right]
			}
		}
	}

}
