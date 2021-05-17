package com.boj.math.euclidean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이) 유클리드 호제법
 * 최대공약수 -> 유클리드 호제법
 * 최소공배수 -> a * b / 최대공약수
 * 
 * @author jugia
 *
 */
public class Main_Silver5_2609_최대공약수와최소공배수 {

	static int a, b;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		int gcd = euclidean(a,b); // 최대공약수
		int lcm = a * b / gcd; // 최소공배수
		
		System.out.println(gcd);
		System.out.println(lcm);
	}
	
	private static int euclidean(int x, int y) {
		if(y == 0) {
			return x;
		} else {
			return euclidean(y, x%y);
		}
	}
	
}
