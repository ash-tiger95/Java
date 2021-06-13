package com.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver5_19947_투자의귀재배주형 {

	static int H, Y;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken()); // 초기 비용, 10,000 <= H <= 100,000
		Y = Integer.parseInt(st.nextToken()); // 투자 기간, 0 <= Y <= 10

		dp = new int[11];

		dp[0] = H;
		for (int i = 1; i <= Y; i++) {
			dp[i] = (int) (dp[i - 1] * 1.05);
			
			if (i >= 3) {
				dp[i] = Math.max(dp[i], (int) (dp[i - 3] * 1.2));
			}
			if (i >= 5) {
				dp[i] = Math.max(dp[i], (int) (dp[i - 5] * 1.35));
			}
		}
		
		System.out.println(dp[Y]);
		
		br.close();
	}
}
