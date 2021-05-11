package com._review.previous;

import java.util.Arrays;
import java.util.Scanner;

public class Main_Silver3_1699_제곱수의합 {
	
	static int Ans;
	static int[] memoi;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 1 <= N <= 100,000
		
		memoi = new int[N+1];
		
		/*
		// 내 풀이(잘못된 풀이): 가장 큰 제곱수를 계속 빼가면 될줄 알았지만, 이렇게 하면 안됨.
		// 항상 최솟값을 만들어나가야함. (모든 제곱수를 검사해야함)
		int temp = 1;
		memoi[0] = 0;
		memoi[1] = 1;
		for(int i=2;i<N+1;i++) {
			if(i < Math.pow(temp, 2)) {
				memoi[i] = memoi[i-1];
			} else {
				memoi[i] = (int) Math.pow(temp, 2);
				temp++;
			}
		}
		System.out.println(Arrays.toString(memoi));
		Ans =0;
		dfs(N,0);
		System.out.println(Ans);
		*/
		
		memoi[1] = 1;
		for (int i = 2; i < N + 1; i++) {
			memoi[i] = i;
			for (int j = 1; j * j <= i; j++) {
				memoi[i] = Math.min(memoi[i], memoi[i - j * j] + 1);
			}
		}
		
		System.out.println(memoi[N]);
	}
	
	private static void dfs(int start, int cnt) {
		System.out.println(start+ " "+cnt);
		if(start ==0) {
			System.out.println("in");
			Ans = cnt;
			return;
		}
		if(start - memoi[start] >= 0) {
			dfs(start-memoi[start], cnt+1);
		}
	}

}
