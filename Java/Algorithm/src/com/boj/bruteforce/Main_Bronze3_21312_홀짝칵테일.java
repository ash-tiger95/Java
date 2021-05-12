package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이>
 * Case1. 홀수가 하나라도 있으면 홀수만을 곱한다.
 * 	홀 * 홀 = 홀
 * 	홀 * 짝 = 짝
 * 	짝 * 짝 = 짝
 * 
 * Case2. 홀수가 없으면 모든 수를 곱한다.
 * 
 * @author jugia
 *
 */
public class Main_Bronze3_21312_홀짝칵테일 {

	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력
		in = new int[3]; // 1 <= N <= 100
		for (int i = 0; i < 3; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		int temp = 1;
		boolean hasOdd = false; // 홀수를 가지고 있는지 판별
		for (int i = 0; i < 3; i++) {
			if (in[i] % 2 != 0) {
				temp *= in[i]; // 홀수만을 곱한다.
				hasOdd = true;
			}
		}

		if (hasOdd) { // Case1.
			System.out.println(temp);
		} else { // Case2.
			System.out.println(in[0] * in[1] * in[2]);
		}

	}

}
