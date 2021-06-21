package com.boj.lis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Silver2_11722_가장긴감소하는부분수열 {

	static int N;
	static int[] arr;
	static int[] lis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 초기설정
		lis = new int[arr.length];
		int index = 0;
		lis[index++] = arr[0];

		for (int i = 1; i < arr.length; i++) {
			if (lis[index - 1] > arr[i]) {
				lis[index++] = arr[i];
			} else {
				int temp = binarySearch(lis, 0, index, arr[i]);
				if (temp < 0) { // arr[i]보다 큰거 바로 앞 칸
					temp = -temp - 1;
				}

				lis[temp] = arr[i];
			}
		}

		sb.append(index);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static int binarySearch(int[] a, int start, int end, int key) { // LIS 가장 최적화
		int l = start;
		int r = end - 1;

		while (l <= r) {
			int mid = (l + r) / 2;
			int midVal = lis[mid];

			if (midVal > key) {
				l = mid + 1;
			} else if (midVal < key) {
				r = mid - 1;
			} else {
				return mid;
			}
		}

		return -(l + 1);
	}
}
