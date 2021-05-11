package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Silver2_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("-");
		int Ans = 0;
		boolean first = true;

		for (int i = 0; i < input.length; i++) {
			int sum = 0;
			String[] plus = input[i].split("\\+");
			for (int j = 0; j < plus.length; j++) {
				sum += Integer.parseInt(plus[j]);
			}

			if (first) {
				Ans += sum;
				first = false;
			} else {
				Ans -= sum;
			}
		}
		System.out.println(Ans);

	}

}
