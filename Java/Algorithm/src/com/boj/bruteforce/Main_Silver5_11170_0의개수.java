package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver5_11170_0의개수 {

	static int T, start, end, Ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			Ans = 0;
			for (int i = start; i <= end; i++) {
				Ans += findZero(i);
			}
			
			sb.append(Ans).append("\n");
		} // for T
		
		System.out.println(sb);
	}

	private static int findZero(int num) {
		int count = 0;

		if (num == 0) {
			return 1;
		}
		while (num != 0) {
			if (num % 10 == 0) {
				count++;
			}
			num /= 10;
		}

		return count;
	}
}
