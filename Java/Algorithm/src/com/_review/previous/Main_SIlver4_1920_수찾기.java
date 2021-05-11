package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_SIlver4_1920_수찾기 {
	static int N, M;
	static int[] map, check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		check = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			check[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(map);
		for (int i = 0; i < M; i++) {
			System.out.println(binarySearch(map, check[i]) == true ? 1 : 0);

		}
	}

	private static boolean binarySearch(int[] arr, int key) {
		int left = 0;
		int right = arr.length - 1;
		int mid;

		while (left <= right) {
			mid = (left + right) / 2;

			if (key == arr[mid]) {
				return true;
			}

			if (key < arr[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return false;
	}

}
