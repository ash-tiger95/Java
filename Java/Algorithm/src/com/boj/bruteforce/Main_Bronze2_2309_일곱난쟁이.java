package com.boj.bruteforce;

import java.util.Arrays;
import java.util.Scanner;

public class Main_Bronze2_2309_일곱난쟁이 {
	static int[] input; // 9명의 키
	static boolean[] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		input = new int[9];
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			input[i] = sc.nextInt();
			sum += input[i];
		}

		// 간단히
		boolean check = false;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(i == j) {
					continue;
				}
				if(sum-input[i]-input[j] == 100) {
					input[i] = 0;
					input[j] = 0;
					check = true;
					break;
				}
			}
			if(check) {
				break;
			}
		}
		Arrays.sort(input);
		for(int i=0;i<9;i++) {
			if(input[i] != 0) {
				System.out.println(input[i]);
			}
		}
		
		// 내 풀이
		/*
		selected = new boolean[9];
		boolean flag = false;
		for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				selected[i] = true;
				selected[j] = true;
				if (check()) {
					flag = true;
					break;
				}
				selected[i] = false;
				selected[j] = false;
			}
			if (flag)
				break;
		}
		int[] output = new int[7];
		int idx = 0;
		for (int i = 0; i < 9; i++) {
			if (!selected[i]) {
				output[idx++] = input[i];
			}
			if (idx == 7) {
				break;
			}
		}
		Arrays.sort(output);
		for (Integer out : output) {
			System.out.println(out);
		}
		*/
	}

	private static boolean check() {
		int ans = 0;
		for (int i = 0; i < 9; i++) {
			if (!selected[i]) {
				ans += input[i];
			}
		}

		if (ans == 100) {
			return true;
		} else {
			return false;
		}
	}
}
