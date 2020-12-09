package com.theory.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Combination03_DiceTest {
	static int N,M;
	static int[] nums;
	static boolean[] isSelected;
	static int totalCnt;
	public static void main(String[] args) {
		Scanner	sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt(); // 주사위 던지기 1,2,3,4 선택하기 위함
		nums = new int[N];
		isSelected = new boolean[7];
		totalCnt = 0;
		
		switch(M) {
		case 1: // 주사위 던지기 1: 중복순열
			dice1(0);
			break;
		
		case 2:
			dice2(0);
			break;
			
		case 3:
			dice3(0,1);
			break;
		
		case 4:
			dice4(0,1);
			break;
		}
		System.out.println("총 경우의 수: "+totalCnt);
	}
	private static void dice1(int cnt) { // 주사위 던지기1: 중복순열
		if(cnt == N) {
			totalCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}
		for(int i=1; i<=6;i++) {
			nums[cnt] = i;
			dice1(cnt+1);
		}
	}
	
	private static void dice2(int cnt) { // 주사위 던지기2: 순열
		if(cnt == N) {
			totalCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}
		for(int i=1; i<=6;i++) {
			if(isSelected[i]) {
				continue;
			}
			nums[cnt] = i;
			isSelected[i] = true;
			dice2(cnt+1);
			isSelected[i] = false;
		}
	}
	
	private static void dice3(int cnt, int cur) { // 주사위 던지기3: 중복조합, cur은 시작
		if(cnt == N) {
			totalCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}
		for(int i=cur; i<=6;i++) {
			nums[cnt] = i;
			dice3(cnt+1,i); 
		}
	}
	
	private static void dice4(int cnt, int cur) { // 주사위 던지기4: 조합
		if(cnt == N) {
			totalCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}
		for(int i=cur; i<=6;i++) {
			nums[cnt] = i;
			dice4(cnt+1,i+1);
		}
	}
}
