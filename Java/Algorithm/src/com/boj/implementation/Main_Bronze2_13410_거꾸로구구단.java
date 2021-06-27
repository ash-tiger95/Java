package com.boj.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Bronze2_13410_거꾸로구구단 {

	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 단, 1 <= N <= 1,000
		K = Integer.parseInt(st.nextToken()); // 항, 1 <= K <= 1,000

		int ans = 0;
		
		for (int i = 1; i <= K; i++) {
			String temp = (N * i) + "";

			int reverse = 0;
			int index = 0;

			for (int j = 0; j <temp.length(); j++) {
				reverse += (temp.charAt(j) - '0') * (int) Math.pow(10, index++);
			}

			ans = Math.max(ans, reverse);
		}

		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
