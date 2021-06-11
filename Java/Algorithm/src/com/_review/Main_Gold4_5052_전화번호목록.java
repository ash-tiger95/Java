package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold4_5052_전화번호목록 {

	static int T, N;
	static String[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			in = new String[N];
			for (int i = 0; i < N; i++) {
				in[i] = br.readLine();
			}

			Arrays.sort(in);

			boolean isPossible = false;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i+1; j < N; j++) {
					if(in[i].length() <= in[j].length()) {
						if(in[j].startsWith(in[i])) {
							sb.append("YES").append("\n");
							isPossible =  true;
							break;
						}
					}
				}
				
				if(isPossible) {
					break;
				}
			}

			if (!isPossible) {
				sb.append("NO").append("\n");
			}

		} // for T

		System.out.println(sb);
	}
}
