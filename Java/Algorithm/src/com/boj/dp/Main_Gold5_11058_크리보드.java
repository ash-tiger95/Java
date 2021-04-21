package com.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Gold5_11058_크리보드 {

	static int N;
	static int[] memoi;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		memoi = new int[N + 1];
		memoi[1] = 1;
		memoi[2] = 1;
		memoi[3] = 1;
	}
}
