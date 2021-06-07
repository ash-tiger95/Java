package com.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Bronze2_1942_디지털시계 {

	static int Ans;
	static int[] start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 3; t++) { // testcase는 3개
			StringTokenizer st = new StringTokenizer(br.readLine());

			start = new int[3];
			end = new int[3];

			String[] in = st.nextToken().split(":");
			for (int i = 0; i < 3; i++) {
				start[i] = Integer.parseInt(in[i]);
			}

			in = st.nextToken().split(":");
			for (int i = 0; i < 3; i++) {
				end[i] = Integer.parseInt(in[i]);
			}

			Ans = 0;

			int dest = end[0] * 10000 + end[1] * 100 + end[2]; // 목표지점
			int temp = 0;

			while (dest != temp) {
				temp = start[0] * 10000 + start[1] * 100 + start[2]; // start[]를 int로 변환

				if (temp % 3 == 0) {
					Ans++;
				}

				start[2]++; // 초 증가

				if (start[2] == 60) {
					start[1]++;
					start[2] = 0;
				}

				if (start[1] == 60) {
					start[0]++;
					start[1] = 0;
				}

				if (start[0] == 24) {
					start[0] = 0;
				}
			}
			sb.append(Ans).append("\n");

		}

		System.out.println(sb);
		
		br.close();
	}
}
