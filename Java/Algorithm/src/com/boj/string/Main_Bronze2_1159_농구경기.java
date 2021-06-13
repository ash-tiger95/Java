package com.boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Bronze2_1159_농구경기 {

	static int N;
	static int[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		alpha = new int[26];

		for (int i = 0; i < N; i++) {
			char[] in = br.readLine().toCharArray();

			alpha[in[0] - 'a']++;
		}

		boolean isPossible = false;
		
		for (int i = 0; i < 26; i++) {
			if (alpha[i] >= 5) {
				sb.append((char) (97 + i)); // ★ 괄호 처음부터 끝까지 묶는 것 주의
				isPossible = true;
			}
		}

		if (isPossible) {
			System.out.println(sb);
		} else {
			System.out.println("PREDAJA");
		}
		
		br.close();
	}
}
