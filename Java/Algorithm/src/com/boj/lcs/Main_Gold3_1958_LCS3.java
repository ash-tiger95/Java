package com.boj.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Gold3_1958_LCS3 {

	static char[] a, b, c;
	static int[][][] LCS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		a = br.readLine().toCharArray();
		b = br.readLine().toCharArray();
		c = br.readLine().toCharArray();

		LCS = new int[a.length + 1][b.length + 1][c.length + 1];

		for (int i = 0; i < a.length + 1; i++) {
			for (int j = 0; j < b.length + 1; j++) {
				for (int k = 0; k < c.length + 1; k++) {

					if (i == 0 || j == 0 || k == 0) {
						continue;
					}

					if (a[i - 1] == b[j - 1] && b[j - 1] == c[k - 1]) {
						LCS[i][j][k] = LCS[i - 1][j - 1][k - 1] + 1;
					} else {
						LCS[i][j][k] = Math.max(LCS[i - 1][j][k], Math.max(LCS[i][j - 1][k], LCS[i][j][k - 1]));
					}

				}
			}
		}

		System.out.println(LCS[a.length][b.length][c.length]);
	}
}
