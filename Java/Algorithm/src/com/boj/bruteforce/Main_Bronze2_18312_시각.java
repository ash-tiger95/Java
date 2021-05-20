package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 내 풀이) hour:minute:second 모양 만들어서 검사하기, 30772KB, 364ms
 * 점화식을 만들라 했으나 실패
 * 
 * 다른 풀이) 간단한 풀이, 14536KB, 144ms
 * 
 * @author jugia
 *
 */
public class Main_Bronze2_18312_시각 {

	static int N, K, Ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 00:00:00부터 N:59:59까지 중
		K = Integer.parseInt(st.nextToken()); // K가 포함된 시간의 개수 구하기

		Ans = 0;

		for (int i = 0; i <= N; i++) {
			String hour = "";

			if (i < 10) {
				hour += "0" + i;
			} else {
				hour += i + "";
			}

			for (int j = 0; j < 60; j++) {
				String minute = "";

				if (j < 10) {
					minute += "0" + j;
				} else {
					minute += j + "";
				}

				for (int k = 0; k < 60; k++) {
					String second = "";

					if (k < 10) {
						second += "0" + k;
					} else {
						second += k + "";
					}

					String temp = hour + minute + second;
					if (temp.contains(K + "")) {
						Ans++;
					}
				}

			}
		}

		System.out.println(Ans);
	}

	private static void refer() {
		int count = 0;
		int sec = 0;

		for (int i = 0; i < 60; i++)
			if (i % 10 == K || i / 10 == K)
				sec++;

		for (int hour = 0; hour <= N; hour++) {
			if (hour % 10 == K || hour / 10 == K)
				count += 60 * 60;
			else {
				for (int min = 0; min < 60; min++) {
					if (min % 10 == K || min / 10 == K)
						count += 60;
					else
						count += sec;
				}
			}
		}
		System.out.println(count);
	}

}
