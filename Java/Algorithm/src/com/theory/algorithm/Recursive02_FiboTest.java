package com.theory.algorithm;

import java.util.Scanner;

public class Recursive02_FiboTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(fibo(N));
	}
	
	private static long fibo(int n) {
		if(n<=1)
			return n;
		return fibo(n-1) + fibo(n-2);
	}
}
