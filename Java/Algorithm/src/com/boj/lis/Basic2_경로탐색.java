package com.boj.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Basic2_경로탐색 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] a = { 1, 8, 9, 2, 4, 3, 6, 11, 7, 10, 14, 5 };
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int n = Integer.parseInt(br.readLine());
//		int[] a = new int[n];
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for(int i=0;i<n;i++) {
//			a[i] = Integer.parseInt(st.nextToken());
//		}
		int[] c = new int[a.length]; // LIS index 저장
		int[] path = new int[a.length]; // 경로를 저장할 배열 역추적할 index를 저장
		int size = 0; // LIS의 개수 관리할 변수

		path[size] = -1; // 첫번째 숫자라는 의미
		c[size++] = 0; // 첫번쨰 숫자의 index를 저장

//		System.out.println("path: " + Arrays.toString(path));
//		System.out.println("c: " + Arrays.toString(c));
//		System.out.println();
		
		
		for (int i = 1; i < a.length; i++) {
//			System.out.println("a[i]: " + a[i] + " i: " + i);
			if (a[c[size - 1]] < a[i]) { // c배열의 가장 마지막 값이랑만 비교한다.
				path[i] = c[size - 1];
				c[size++] = i; // 맨 뒤에 붙인다.
//				System.out.println("path: " + Arrays.toString(path));
//				System.out.println("c: " + Arrays.toString(c));
//				System.out.println();
			} else { // c배열의 가장 마지막 값이랑만 비교한다.
//				int temp = binarySearch0(a, c, 0, size, a[i]);
				int temp = Arrays.binarySearch(c, 0, size, a[i]);
				// c배열에서 0번부터 size-1까지 a[i]가 있는지 검사를 한다.
				if (temp < 0) {
					temp = -temp - 1;
				}
//				System.out.println("temp: "+temp);
				path[i] = path[c[temp]]; // 덮어쓸 위치의 index를 내껄로 복사
				c[temp] = i; // 수열의 값을 LIS에 삽이할 위치에 덮어쓰기
//				System.out.println("path: " + Arrays.toString(path));
//				System.out.println("c: " + Arrays.toString(c));
//				System.out.println();
			}
		}
//		System.out.println("LIS 개수: " + size);

		// 역추적
		String result = "";
		for (int i = c[size - 1]; i != -1; i = path[i]) {
			result = a[i] + " " + result;
		}
		System.out.println(size);
		System.out.println(result);

	}

	private static int binarySearch0(int[] a, int[] c, int fromIndex, int toIndex, int key) { // a배열의 c index를 찾아야되
		int low = fromIndex;
		int high = toIndex - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = a[c[mid]];

			if (midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1); // key not found.
	}
}
