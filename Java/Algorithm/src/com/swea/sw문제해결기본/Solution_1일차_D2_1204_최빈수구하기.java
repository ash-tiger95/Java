package com.swea.sw문제해결기본;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1일차_D2_1204_최빈수구하기 {

	static int T, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\1204.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			sb.append("#").append(N).append(" ");

			st = new StringTokenizer(br.readLine());
			int[] score = new int[101]; // 0 <= score <= 100
			for (int i = 0; i < 1000; i++) { // 학생 수는 1,000명
				score[Integer.parseInt(st.nextToken())]++;
			}

			int max = 0;
			int ans = 0; // 최빈수 값 (같을 경우 큰 점수)
			for (int i = 0; i < 101; i++) {
				if (max <= score[i]) {
					max = score[i];
					ans = i;
				}
			}
			
			sb.append(ans).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
