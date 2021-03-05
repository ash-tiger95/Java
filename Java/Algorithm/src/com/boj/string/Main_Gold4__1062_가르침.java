package com.boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_Gold4__1062_가르침 {
	static int N, K, Ans;
	static boolean[] visited;
	static String[] word;
	static ArrayList<Character> teachWord;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 입력하는 단어 개수
		K = Integer.parseInt(st.nextToken()); // 가르칠 수 있는 단어 개수
		Ans = 0;

		if (K < 5) { // a,c,i,n,t는 필수로 필요
			System.out.println(0);
		} else if (K == 26) {
			System.out.println(N);
		} else {
			visited = new boolean[26];
			word = new String[N];

			visited['a' - 'a'] = true;
			visited['n' - 'a'] = true;
			visited['t' - 'a'] = true;
			visited['i' - 'a'] = true;
			visited['c' - 'a'] = true;

			for (int i = 0; i < N; i++) {
				word[i] = br.readLine().replaceAll("anta|tica", "");
			}

			nCr(0, 0);
			System.out.println(Ans);
		}

	}

	private static void nCr(int cur, int cnt) {
		if (cnt == K - 5) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = true;

				for (int j = 0; j < word[i].length(); j++) {
					/* 배우지않은 알파벳이 있는 경우 */
					if (!visited[word[i].charAt(j) - 'a']) {
						flag = false;
						break;
					}
				}

				if (flag) {
					temp++;
				}
			}

			Ans = Math.max(temp, Ans);
			return;
		}
		for (int i = cur; i < 26; i++) {
			if (!visited[i]) {
				visited[i] = true;
				nCr(i, cnt + 1);
				visited[i] = false;
			}
		}
	}

}
