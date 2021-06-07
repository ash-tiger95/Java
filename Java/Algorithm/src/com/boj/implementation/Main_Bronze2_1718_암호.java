package com.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Bronze2_1718_암호 {

	static char[] in, key;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		in = br.readLine().toCharArray();
		key = br.readLine().toCharArray();

		int idx = 0;

		for (int i = 0; i < in.length; i++) {
			if (in[i] == ' ') {
				sb.append(" ");
				
			} else {
				int ch = key[idx] - 97 + 1;
				if (in[i] - ch < 97) {
					ch = 122 - (ch - (in[i] - 97)) + 1;
					sb.append((char) (ch));
				} else {
					sb.append((char) (in[i] - ch));
				}
			}

			idx++;
			if (idx == key.length) {
				idx = 0;
			}
		}

		System.out.println(sb);

		br.close();
	}
}
