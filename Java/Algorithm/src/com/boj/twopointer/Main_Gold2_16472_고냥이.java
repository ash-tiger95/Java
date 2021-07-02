package com.boj.twopointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Gold2_16472_고냥이 {

	static int N;
	static String word;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		word = br.readLine();

		int max = 1;
		int cnt = 0;
		int left = 0;
		int right = 0;

		int[] check = new int[26]; // ★. 선택한 알파벳 개수 저장
		check[word.charAt(0) - 'a']++;
		cnt++;

		while (true) {
			right++;

			if (right == word.length()) {
				break;
			}

			int num = word.charAt(right) - 'a';
			check[num]++;

			if (check[num] == 1) {
				cnt++;
			}
			
			// ★. N개를 선택할 때 검사하는 것이 아니라, 선택한 것이 N개를 넘을 때 검사
			while (cnt > N) {
				int num2 = word.charAt(left) - 'a';
				check[num2]--;

				if (check[num2] == 0) {
					cnt--;
				}

				left++;
			}

			max = Math.max(max, right - left + 1);
		}

		sb.append(max);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
