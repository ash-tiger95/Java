package com.test.scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class 일차01_대여시간을추천해드립니다 {

	static String answer;
	static int N;
	static int[] start;
	static int[] end;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		start = new int[N]; // 시:분
		end = new int[N];
		answer = "";

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			start[i] = Integer.parseInt(input.substring(0, 2) + input.substring(3, 5));
			end[i] = Integer.parseInt(input.substring(8, 10) + input.substring(11, 13));
		}

		// 입력은 시작 순으로 입력하니 start 배열의 마지막 값이 시작
		Arrays.sort(start);
		Arrays.sort(end);

		int startMax = start[N - 1];
		int endMin = 0;
		System.out.println(Arrays.toString(start));
		System.out.println(Arrays.toString(end));
		
		

		if (end[0] <= startMax) {
			System.out.println(-1);
			return;
		} else { // 아니면 가장 최초의 값 출력
			endMin = end[0];
		}

		String a = startMax + "";
		String b = endMin + "";
		
		if (a.length() == 1) {
			a = "000".concat(a);
		}

		if (b.length() == 1) {
			b = "000".concat(b);
		}
		
		if (a.length() == 2) {
			a = "00".concat(a);
		}

		if (b.length() == 2) {
			b = "00".concat(b);
		}
		

		if (a.length() == 3) {
			a = "0".concat(a);
		}

		if (b.length() == 3) {
			b = "0".concat(b);
		}

		answer = a.substring(0, 2) + ":" + a.substring(2, 4) + " ~ " + b.substring(0, 2) + ":" + b.substring(2, 4);

		System.out.println(answer);

	}

}
