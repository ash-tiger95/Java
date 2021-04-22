package com.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Gold5_1759_암호만들기 {

	static int L, C;
	static String[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 3 <= L <= C <= 15
		L = Integer.parseInt(st.nextToken()); // 암호는 서로 다른 L개의 알파벳 소문자로 구성
		C = Integer.parseInt(st.nextToken()); // 암호로 사용한 문자의 종류는 C가지

		input = new String[C];
		input = br.readLine().split(" ");

		Arrays.sort(input);

		recur(0, "");
	}

	private static void recur(int cnt, String str) {
		if (str.length() == L) {
			if (isPossble(str)) {
				System.out.println(str);
			}
			return;
		}

		if (cnt >= C) {
			return;
		}

		recur(cnt + 1, str + input[cnt]);
		recur(cnt + 1, str);
	}

	private static boolean isPossble(String str) {
		int moCnt = 0;
		int jaCnt = 0;

		for (int i = 0; i < L; i++) {
			char c = str.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				moCnt++;
			} else {
				jaCnt++;
			}
		}

		if (moCnt >= 1 && jaCnt >= 2) { // 최소 1개 모음 + 최소 2개 자음으로 구성
			return true;
		} else {
			return false;
		}

	}
}
