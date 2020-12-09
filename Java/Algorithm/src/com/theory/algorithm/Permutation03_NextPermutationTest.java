package com.theory.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author SungHo
 * 1. 뒤쪽부터 탐색하며 교환위치(i-1) 찾기 (i: 꼭대기)
 * 2. 교환위치(i-1)와 교환할 큰 값 위치(j) 찾기
 * 3. 두 위치 값(i-1,j) 교환
 * 4. 꼭대기 위치(i)부터 맨 뒤까지 오름차순으로 교환
 *
 */

public class Permutation03_NextPermutationTest {
	static int N;
	static int[] input;
	static int totalCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		for(int i=0; i<N;i++) {
			input[i] = sc.nextInt();
		}
		
		// 시작: 가장 작은 순열로 시작해야되.
		Arrays.sort(input);
		
		do {
			System.out.println(Arrays.toString(input));
		}while(nextPermutation());
		System.out.println("총 경우의 수: "+totalCnt);
		
	}
	
	private static boolean nextPermutation() { // 현재 순열에서 다음 순열을 만들 수 있는지 없는지 판단
		totalCnt++;
		// step 1
		int i=N-1;
		while(i>0 && input[i-1] >= input[i]) {
			--i;
		}
		if(i == 0) return false;//꼭대기까지 갔는데 다음으로 갈 때가 없어 
	
		// step 2
		int j=N-1;
		while(input[i-1] >= input[j]) {
			--j;
		}
		
		// step 3
		int temp = input[i-1];
		input[i-1] = input[j];
		input[j] = temp;
		
		// step 4
		int k = N-1;
		while(i<k) {
			temp = input[i];
			input[i] = input[k];
			input[k] = temp;
			
			++i;
			--k;
		}
		return true;
	}
}
