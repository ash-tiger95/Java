package com.theory.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Permutation02_BitmaskingTest {
	static int N, R;
	static int[] input, output;
	static int totalCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 2~10개 -> int형은 32비트
		R = sc.nextInt();
		input = new int[N];
		output = new int[R];
		
		for(int i=0 ; i<N;i++) {
			input[i] = sc.nextInt();
		}
		
		permutation(0,0);
		System.out.println("총 경우의 수: " + totalCnt);
		
	}
	
	private static void permutation(int cnt, int flag) { // cnt: 순열을 뽑는 자리(index), flag는 0000000000000000
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(output));
			return;
		}
		
		// 해당 자리에 뽑을 가능한 모든 수에 대해 시도(앞자리까지 선택된 수 배제)
		for(int i=0; i<N;i++) {
			if((flag & 1<<i) != 0) { // 한 비트 이상이 켜져있다는 것
				continue;
			}
			output[cnt] = input[i];
//			flag = flag | 1<<i; // 이렇게 하면 밑에서 되돌려야 됨
			permutation(cnt+1, flag | 1<<i); // 결과를 보내는 것, 안 되돌려도 되!
		}
		
		
	}
}
