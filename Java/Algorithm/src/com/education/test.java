package com.education;

import java.util.ArrayList;

public class test {
	public static void main(String[] args) {
		// 매개변수 이어서 쓰기 test
		int a = 1;
		int b = dfs(1);
		System.out.println(b);

		// break 문법 test
		outer: for (int i = 0; i < 100 - 1; i++) {
			System.out.println(i + "-----------");
			for (int j = i + 1; j < 100; j++) {
				if (j == 10) {
					break outer;
				}
				System.out.println(j);
			}
		}
		System.out.println("end");

		// 사칙연산 test
		a += 1 + 34;
		System.out.println(a);

		// 2차원 리스트 test
		ArrayList<Integer>[][] myList = new ArrayList[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				myList[i][j] = new ArrayList<Integer>();
				int n = (int) (Math.random() * 4);
				for (int q = 0; q < n; q++) {
					myList[i][j].add((int) (Math.random()));
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.printf("%d ", myList[i][j].size());
			}
			System.out.printf("\n");
		}
	}

	private static int dfs(int i) {
		if (i == 1) {
			i = 2;
		}
		return i;
	}

}
