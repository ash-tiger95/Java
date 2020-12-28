package com.theory.algorithm;

import java.util.Arrays;

public class DAC01_MergeSort {
	static int[] input = { 38, 27, 43, 3, 9, 82, 10 };

	public static void main(String[] args) {
		System.out.println(Arrays.toString(mergeSort(input)));
	}

	private static int[] mergeSort(int[] arr) {
		if (arr.length < 2) // 기저조건은 배열크기가 2보다 작을 때
			return arr;

		int mid = arr.length / 2;
		int[] lowArr = mergeSort(Arrays.copyOfRange(arr, 0, mid)); // Arrays.copyOfRange(original, from, to)
		int[] highArr = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

		int[] mergedArr = new int[arr.length]; // 최적화하는 부분, (in-place sort)
		int m = 0, l = 0, h = 0;
		
		// lowArr과 highArr를 비교하면서 값 넣기
		while (l < lowArr.length && h < highArr.length) {
			if (lowArr[l] < highArr[h])
				mergedArr[m++] = lowArr[l++];
			else
				mergedArr[m++] = highArr[h++];
		}
		
		// lowArr과 highArr를 비교하고 남아있는 나머지 원소 넣기
		while (l < lowArr.length) {
			mergedArr[m++] = lowArr[l++];
		}
		while (h < highArr.length) {
			mergedArr[m++] = highArr[h++];
		}
		
		return mergedArr;
	}
}
