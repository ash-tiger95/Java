package com.boj.dp.memoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Silver1_1309_동물원 {
	static final int MOD = 9901;
	static int N, Ans;
	static int[][] memoi;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 1 <= N <= 100,000
		Ans = 0;
		memoi = new int[N][3];

		memoi[0][0] = 1;
		memoi[0][1] = 1;
		memoi[0][2] = 1;

		for (int i = 1; i < N; i++) {
			memoi[i][0] = (memoi[i - 1][0] + memoi[i - 1][1] + memoi[i - 1][2]) % MOD;
			memoi[i][1] = (memoi[i - 1][0] + memoi[i - 1][2]) % MOD;
			memoi[i][2] = (memoi[i - 1][0] + memoi[i - 1][1]) % MOD;
		}
		
		Ans  =  memoi[N-1][0] + memoi[N-1][1] + memoi[N-1][2];

		System.out.println(Ans%MOD);
	}

}
