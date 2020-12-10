package com.theory.algorithm;

import java.util.Scanner;

public class SubSet02_BinaryCountingTest {
	static int N, totalCnt;
	static int[] input;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];
		
		for(int i=0;i<N;i++)
			input[i] = sc.nextInt();
		
		totalCnt = 1<<N; //2^N
		generateSubSet(totalCnt);
		System.out.println("총 경우의 수: "+totalCnt);
		
	}
	
	// 비트열 생성 작업은 안 해도 된다.
	private static void generateSubSet(int caseCnt) { // 매개변수 크기 만큼 돌린다.
		for(int flag=0;flag<caseCnt-1;flag++) {
			// flag 비트열 별로 원소수만틈 각 자리 비트를 확인
			for(int i=0;i<N;i++) {
				System.out.print((((flag & 1<<i) != 0)?input[i]:"X")+"\t"); // 사용여부 확인, 1인 경우 선택된 경우
				
			}
			System.out.println();
		}
	}
}
