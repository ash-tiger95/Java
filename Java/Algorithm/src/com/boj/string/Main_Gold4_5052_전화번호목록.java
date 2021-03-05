package com.boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main_Gold4_5052_전화번호목록 {
	static int T, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			String[] input = new String[N];
			HashSet<String> set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				input[i] = br.readLine();
			}
			Arrays.sort(input);
			boolean check = false;
			

			for (int i = 0; i < N; i++) {
				if (check) {
					break;
				}

				for (int j = 1; j < input[i].length(); j++) {
					if (set.contains(input[i].substring(0, j))) {
						check = true;
						break;
					}
				}
				set.add(input[i]);
			}
			

			System.out.println(check == false ? "YES" : "NO");

		} // for T
	}

}
