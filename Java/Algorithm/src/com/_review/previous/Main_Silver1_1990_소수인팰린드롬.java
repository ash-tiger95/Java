package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Silver1_1990_소수인팰린드롬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		int a = Integer.parseInt(input.split(" ")[0]);
		int b = Integer.parseInt(input.split(" ")[1]);

		System.out.println(a + " " + b);

		for (int i = a; i <= b; i++) {
			if (isPrime(i)) {
				String x = i + "";
				String temp = "";
				for (int j = x.length() - 1; j >= 0; j--) {
					temp += x.charAt(j);
				}

				if (x.equals(temp)) {
					System.out.println(x);
				}
			}
		}

		System.out.println(-1);

	}

	private static boolean isPrime(int num) { // 소수구하기 방법 1)
		for (int i = 2; i<= (int)Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private static void isPrime2(int num) { // 에라토스테네스의 체
		int rootSqrt = (int) Math.sqrt(num);
		boolean[] arr = new boolean[num];

		for(int i=2;i<=rootSqrt;i++) {
		    if (arr[i]) {

		        // 이미 걸려진 수의 배수는 skip
		        continue;
		    }
		    for(int j=i+i;j<=num;j+=i) {
		        arr[j] = true;
		    }
		}
		


	}
}
