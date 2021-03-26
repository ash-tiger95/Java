package com.test.scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모의02_사은품교환하기 {

	static long TC, N, M, answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Long.parseLong(st.nextToken()); // 시즌 한정 음료 쿠폰 개수
			M = Long.parseLong(st.nextToken()); // 일반 음료 쿠폰 개수
			answer = -1;

			// 조건1. 하나의 상품으로 교환하기 위해서는 12장의 쿠폰 필요
			if (N < 5 || N + M < 12) {
				sb.append(0).append("\n");
				continue;
			}

			// 조건2. 이 쿠폰들 중 최소 5개는 시즌 한정 음료 쿠폰
			for (int i = 5; i <= 12; i++) {
				// M먼저 처리
				long m = M / i;
				
				

//				if (i == 12) {
//
//					if (N % i + M >= 12) {
//						if (N % i >= 5) {
//							n++;
//						}
//					}
//
//					answer = Math.max(answer, n);
//
//				} else {
//					long m = M / (12 - i);
//					long temp = 0;
//					
//					if (n > m) {
//						temp = m;
//					} else {
//						temp = n;
//					}
//
//					long nn = N % i;
//					long mm = M % (12 - i);
//
//					if (nn + mm >= 12) {
//						if (nn >= 5) {
//							temp++;
//						}
//					}
//
//					answer = Math.max(answer, temp);
//				}

			}

			// 출력. 쿠폰으로 얻을 수 있는 최대 상품의 수

			sb.append(answer).append("\n");
		} // for TC
		System.out.println(sb);
	}
}
