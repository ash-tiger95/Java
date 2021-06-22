package com.boj.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Bronze2_3040_백설공주와일곱난쟁이 {

	static int total;
	static int[] cap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = null;

		cap = new int[9];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			cap[i] = Integer.parseInt(st.nextToken());

			total += cap[i];
		}

		outer: for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (total - cap[i] - cap[j] == 100) {

					for (int k = 0; k < 9; k++) {
						if (i == k || j == k) {
							continue;
						} else {
							sb.append(cap[k]).append("\n");
						}
					}

					break outer;
				}
			}
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
