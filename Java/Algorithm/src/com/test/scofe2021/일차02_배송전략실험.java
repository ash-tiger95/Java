package com.test.scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 일차02_배송전략실험 {
	
	static int N;
	static char[] map;
	static long[] memoi;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 3 <= N <= 50, 첫문자&&끝문자는 항상 1, 0: 두번 연속x
		map = new char[N];
		map = br.readLine().toCharArray();
		
		
		memoi = new long[N];
		memoi[0] = 1; // 첫문자는 항상 1
		
		if(map[1] == '0') {
			memoi[1] = 0;
		} else {
			memoi[1] = 1;
		}
		
		if(map[2] == '0') {
			memoi[2] = 0;
		} else {
			memoi[2] = memoi[1]+1;
		}
		
		for(int i=2;i<N;i++) {
			if(map[i] == '1') {
				memoi[i] = memoi[i-1]+ memoi[i-2];
			} else {
				memoi[i] = 0;
			}
		}
//		System.out.println(Arrays.toString(memoi));
		
		System.out.println(memoi[N-1]);
	}

}
