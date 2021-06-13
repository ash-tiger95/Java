package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Bronze2_2309_일곱난쟁이 {

	static int[] height;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		height = new int[9];
		int total = 0;
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			height[i] = a;
			total += a;
		}

		Arrays.sort(height); // 출력을 오름차순으로 해야하기 때문에

		int except1 = -1, except2 = -1;
		outer: for (int i = 0; i < 9; i++) {
			total -= height[i];
			
			for (int j = 0; j < 9; j++) {
				if (i == j) {
					continue;
				}

				total -= height[j]; // 검사 후
				
				if (total == 100) {
					except1 = i;
					except2 = j;
					break outer;
				}
				
				total += height[j]; // 복귀
			}
			
			total += height[i];
		}

		for (int i = 0; i < 9; i++) {
			if (i == except1 || i == except2) {
				continue;
			} else {
				sb.append(height[i]).append("\n");
			}
		}

		System.out.println(sb);
	}
}
