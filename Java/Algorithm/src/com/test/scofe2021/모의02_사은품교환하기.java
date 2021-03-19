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
			answer = 0;

			// 조건1. 하나의 상품으로 교환하기 위해서는 12장의 쿠폰 필요
			if (N < 5 || N + M < 12) {
				sb.append(0).append("\n");
				continue;
			}

			// 조건2. 이 쿠폰들 중 최소 5개는 시즌 한정 음료 쿠폰
			while (true) {
				N = N - 5;
				M = M - 7;
				if (N < 0) {
					break;
				}

				if (M < 0) { // M이 0보다 작을 때, N이 채워줄 수 있는지 검사
					if (N + M >= 0) {
						answer++;
						break;
					} else {
						break;
					}
				}

				answer++;

			}

			// 출력. 쿠폰으로 얻을 수 있는 최대 상품의 수

			sb.append(answer).append("\n");
		} // for TC
		System.out.println(sb);
	}
}
