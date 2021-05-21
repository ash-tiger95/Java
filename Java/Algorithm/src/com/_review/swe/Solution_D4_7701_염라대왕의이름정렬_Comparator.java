package com._review.swe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution_D4_7701_염라대왕의이름정렬_Comparator {
	static int T, N;
	static String[] input;
	static TreeSet<String> name;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append("\n");
			N = Integer.parseInt(br.readLine());
			
			// 1. 이름의 길이가 짧을수록 앞에
			// 2. 길이가 같으면 사전순으로 정렬
			

			/* 
			input = new String[N];
			for (int i = 0; i < N; i++) {
				input[i] = br.readLine();
			}

			// 방법 1. 선택정렬
			for (int i = 0; i < input.length; i++) {
				int minIndex = i;
				for (int j = i; j < input.length; j++) {
					if (compare(input[minIndex], input[j]) > 0) { // pre가 next보다 빠르다는 의미
						System.out.println(input[minIndex] + "," + input[j] + " " + compare(input[minIndex], input[j]));
						minIndex = j;
					}
				}

				// swap
				String temp = input[i];
				input[i] = input[minIndex];
				input[minIndex] = temp;
			}

			
			// 방법2. Arrays.sort
			// 정렬
			Arrays.sort(input, new Comparator<String>() {
				public int compare(String pre, String next) { // pre가 먼저 들어온거
					System.out.println("pre: " + pre);
					System.out.println("next: " + next);
					if (pre.length() != next.length()) { // 글자의 길이 짧은 순서
						return pre.length() - next.length(); // -이면 그대로, +이면 바꿈
					} else { // 길이가 같으면 사전순
						return pre.compareTo(next); // 오름차순
					}
				}
			});
			

			// 중복 제거
			String temp = null;
			for (int i = 0; i < input.length; i++) {
				if (!input[i].equals(temp)) {
					sb.append(input[i]).append("\n");
				}
				temp = input[i];
			}
			*/
			
			// 방법3. TreeSet
			name = new TreeSet<String>(new Comparator<String>() {

				@Override
				public int compare(String pre, String next) {
					if (pre.length() != next.length()) { // 글자의 길이가 짧은 수넛대로
						return pre.length() - next.length();
					} else { // 길이가 같으면 사전순
						return pre.compareTo(next); // 오름차순
					}
				}
				
			});
			
			for(int i=0;i<N;i++) {
				name.add(br.readLine());
			}
			
			

		} // for T
		System.out.println(sb);

	}

	private static int compare(String pre, String next) {
		if (pre.length() != next.length()) {
			return pre.length() - next.length();
		} else {
			return pre.compareTo(next);
		}
	}
}
