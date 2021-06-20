package com.boj.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Bronze1_20546_기적의매매법 {

	static int cash;
	static int[] stock;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		cash = Integer.parseInt(st.nextToken());

		stock = new int[14];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 14; i++) {
			stock[i] = Integer.parseInt(st.nextToken());
		}
		int BNP = calBNP();
		int TIMING = calTIMING();

		if (BNP > TIMING) {
			sb.append("BNP");
		} else if (BNP < TIMING) {
			sb.append("TIMING");
		} else {
			sb.append("SAMESAME");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static int calBNP() {
		int c = cash;
		int holding = 0; // 보유 주식 수

		for (int i = 0; i < 14; i++) {
			if (c >= stock[i]) { // 매수(주식 삼)
				holding += c / stock[i];
				c %= stock[i];
			}

			if (c == 0) {
				break;
			}
		}

		return holding * stock[13] + c;
	}

	private static int calTIMING() {
		int c = cash;
		int holding = 0; // 보유 주식 수

		for (int i = 0; i < 11; i++) {
			if (stock[i] < stock[i + 1] && stock[i + 1] < stock[i + 2]) { // 매도(주식 팜)
				c += holding * stock[i + 3];
				holding = 0;
			} else if (stock[i] > stock[i + 1] && stock[i + 1] > stock[i + 2]) { // 매수(주식 삼)
				holding += c / stock[i + 3];
				c %= stock[i + 3];
			}

		}

		return holding * stock[13] + c;
	}
}
