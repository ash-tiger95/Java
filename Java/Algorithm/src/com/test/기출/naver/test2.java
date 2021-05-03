package com.test.기출.naver;

public class test2 {
	private static double solution(int n, int[] p, int[] c) {
		double answer = 0;
		
		int ball = 0;
		int danga = 100; // 단가는 100원 -> 50원 -> (연속2틀 비충족 시)25원
		for(int i=0;i<n;i++) {
			
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		int n = 6;
		int[] p = {5,4,7,2,0,6};
		int[] c = {4,6,4,9,2,3};
		
		System.out.println(solution(n,p,c));
	}

}
