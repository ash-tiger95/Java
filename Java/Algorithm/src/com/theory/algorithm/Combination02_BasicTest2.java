package com.theory.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Combination02_BasicTest2 {
	static int N, R;
	static int[] input, number;
	static int totalCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		number = new int[R];
		
		for(int i=0 ; i<N;i++) {
			input[i] = sc.nextInt();
		}
		
		combination(N, R);
		System.out.println("총 경우의 수: "+totalCnt);
		
	}
	
	// 끝자리로 접근하기
	// nCr에서  -->  n번째 원소를 선택한 경우: n-1Cr-1 vs n번째 원소를 선택하지 않은 경우 n-1Cr
	// nCr = n-1Cr-1(선택) + n-1Cr(비선택)
	// n번째 원소(순서)를 r위치(순서)에 조합해보기
	private static void combination(int n, int r) { // n: index, r개 뽑기
		if(r==0) {
			++totalCnt;
			System.out.println("ANSWER: " + Arrays.toString(number));
			return;
		}
		// 아예 조합이 불가능한 상황
		if(n<r) {
			return;
		}
		
		number[r-1] = input[n-1];
		// 선택
		System.out.println("r-1: " + Arrays.toString(number));
		combination(n-1, r-1);
		// 비선택
		System.out.println("r: " + Arrays.toString(number));
		combination(n-1, r);
		
	}
}
