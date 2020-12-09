package com.theory.algorithm;

import java.util.Scanner;

public class Recursive03_FiboMemoTest {
	static long[] call1, call2, memo;
	static long totalCnt1, totalCnt2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		call1 = new long[N+1];
		call2 = new long[N+1];
		memo = new long[N+1];
		
		System.out.println("Memo o");
		System.out.println(fibo2(N));
		for (int i = 1; i <= N; i++) {
			System.out.println("fibo2("+i+") : "+call2[i]);
		}
		System.out.println("fibo2 call count : "+totalCnt2);
		
		
		System.out.println("===============================");
		
		System.out.println("Memo x");
		System.out.println(fibo(N));
		for (int i = 1; i <= N; i++) {
			System.out.println("fibo("+i+") : "+call1[i]);
		}
		System.out.println("fibo call count : "+totalCnt1);
		
	}
	
	private static long fibo(int n) { // Memo x
		++call1[n]; // 몇 번 호출되는지
		++totalCnt1; // 총 증가 횟수
		if(n<=1)
			return n;
		return fibo(n-1) + fibo(n-2);
	}
	
	private static long fibo2(int n) { // Memo o
		++call2[n]; // 몇 번 호출되는지
		++totalCnt2; // 총 증가 횟수
		if(n<=1)
			return n;
		
		// n항의 값을 계산한 적이 있었다면(메모확인) 메모된 값 리턴
		if(memo[n] != 0) { // 0이면 값이 아직 저장 안 된 것!
			return memo[n]; // 기억된 값을 불러온다.
		}
		
//		memo[n] = fibo(n-1) + fibo(n-2);
//		return memo[n];
		return memo[n] = fibo2(n-1) + fibo2(n-2); // 위의 2줄 의미
	}
}
