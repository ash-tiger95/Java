package com.theory.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Permutation01_BooleanArrayTest {
	static int N, R;
	static int[] input, output;
	static boolean[] isSelected;
	static int totalCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];
		output = new int[R];
		
		for(int i=0 ; i<N;i++) {
			input[i] = sc.nextInt();
		}
		
		permutation(0); // 0번부터 시작
		System.out.println("총 경우의 수: " + totalCnt);
		
	}
	
	private static void permutation(int cnt) { // cnt: 순열을 뽑는 자리(index)
		if(cnt == R) { // R번째이면 끝
			totalCnt++;
			System.out.println(Arrays.toString(output));
			return;
		}
		
		// 해당 자리에 뽑을 가능한 모든 수에 대해 시도(앞자리까지 선택된 수 배제)
		for(int i=0; i<N;i++) {
			if(isSelected[i]) {
				continue;
			}
			output[cnt] = input[i];
//			System.out.println(Arrays.toString(number));
			isSelected[i] = true;
			permutation(cnt+1); // 다음자리의 순열 뽑기
			isSelected[i] = false;
		}
		
		
	}
}
