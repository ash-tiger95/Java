package com.test.day0327_scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 이차01_오디션연습 {

	static int N, sp, Ans;
	static int time; // HH:MM:SS
	static int[] playlist; // MM:SS

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		String t = st.nextToken(); // 초 단위로 계산
		time = Integer.parseInt(t.substring(0, 2)) * 3600 + Integer.parseInt(t.substring(3, 5)) * 60
				+ Integer.parseInt(t.substring(6, 8));

		playlist = new int[N];
		for (int i = 0; i < N; i++) {
			t = br.readLine();
			playlist[i] = Integer.parseInt(t.substring(0, 2)) * 60 + Integer.parseInt(t.substring(3, 5));
		}

		Ans = 0;
		for (int i = 0; i < N; i++) { // 하나하나 검사
			int temp = 0; // 하나 돌때마다 최대값
			int mid = time;
			int idx = i;
			while (idx < N) {

				if (mid - playlist[idx] >= 0) {
					mid -= playlist[idx];
					idx++;
					temp++;
				} else {
					if (mid > 0) {
						temp++;
						break;
					}
				}

				if (mid == 0) {
					break;
				}

			}

			if (Ans < temp) {
				Ans = temp;
				sp = i;
			}
		}
		System.out.println(Ans + " " + (sp + 1));
	}
}
