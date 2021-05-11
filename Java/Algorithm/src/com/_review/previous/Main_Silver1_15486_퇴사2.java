package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver1_15486_퇴사2 {
	static int N;
	static int[] T, P;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // N+1일 후 퇴사
		T = new int[N + 2]; // 시간
		P = new int[N + 2]; // 이익
		StringTokenizer st = null;
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		int max = 0;
		dp = new int[N + 2]; 
        /*
         * N + 2가 된 이유는?
         * 1. index 0을 사용하지 않고 1부터 사용하려고 +1 되었고, 
         * 2. i번째까지 일한 돈은 i+1번째날에 받기 때문에 +1이 또 되었다. 그래서 총 + 2이다.
         */
        
        for (int i = 1; i < N + 2; i++) {
        
            // 현재 시점까지의 최대 수익을 알아야 i + T[i]까지 일했을 때 P[i]를 더해서 최대 수익을 낼 수 있다.
            if(max < dp[i])
                max = dp[i];
            
            // day날까지 일했을 때, max + P[i]와 이미 구해진 그 날의 수익 중에 최대 수익을 택하자! 
            int day = i + T[i];
            if(day < N + 2)
            	dp[day] = Math.max(dp[day], max + P[i]);
            
        }
    
        System.out.println(max);


	}
}
