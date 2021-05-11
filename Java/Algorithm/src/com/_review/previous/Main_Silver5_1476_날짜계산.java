package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver5_1476_날짜계산 {
	static int[] input; // E, S, M

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		input = new int[3];
		for (int i = 0; i < 3; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		// 1 <= E <= 15
		// 1 <= S <= 28
		// 1 <= M <= 19
		int[] start = new int[3];
		start[0] = 1;
		start[1] = 1;
		start[2] = 1;

		int time = 1;
		while (true) {
			if (check(start[0], start[1], start[2])) {
				break;
			}
			for(int i=0;i<3;i++) {
				start[i]++;
				if(start[0] == 16) {
					start[0] = 1;
				}
				
				if(start[1] == 29) {
					start[1] = 1;
				}
				
				if(start[2] == 20) {
					start[2] = 1;
				}
			}
			time++;
		}

		System.out.println(time);

	}

	private static boolean check(int e, int s, int m) {
		if (e == input[0] && s == input[1] && m == input[2]) {
			return true;
		} else {
			return false;
		}
	}
}
