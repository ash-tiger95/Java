package com.boj.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이1) 단순히 구간합 -> 시간초과
 * 풀이2) 아이디어
 * 
 * @author jugia
 *
 */
public class Main_Gold4_10986_나머지합 {

	static int N, M;
	static long Ans;
	static long[] cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cnt = new long[M];
		long sum = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N ; i++) {
			sum = (sum + Long.parseLong(st.nextToken())) % M;
			cnt[(int)sum]++;
		}

		Ans = cnt[0];

		for (int i = 0; i < M; i++) {
			Ans += cnt[i] * (cnt[i] - 1) / 2;
		}
		System.out.println(Ans);

	}
	/*
	private static void 내풀이() {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		in = new int[N];
		prefixSum = new int[N];
		st = new StringTokenizer(br.readLine());
		in[0] = Integer.parseInt(st.nextToken());
		prefixSum[0] = in[0];
		for (int i = 1; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
			prefixSum[i] = prefixSum[i - 1] + in[i];
		}
		

		Ans = 0;
		
		for(int i=0;i<N;i++) {
			if(prefixSum[i] %M == 0) {
				Ans++;
			}
		}
		
		for(int i=0;i<N-1;i++) {
			for(int j=i+1;j<N;j++) {
				if((prefixSum[j]-prefixSum[i])%M == 0) {
					Ans++;
				}
			}
		}

		System.out.println(Ans);
	}
	*/
}
