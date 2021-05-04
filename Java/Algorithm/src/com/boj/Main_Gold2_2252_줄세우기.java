package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 위상정렬
 * 어
 * 
 * @author jugia
 *
 */
public class Main_Gold2_2252_줄세우기 {

	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 학생 수, 1 <= N <= 32,000
		M = Integer.parseInt(st.nextToken()); // 비교 횟수, 1 <= M <= 100,000
		
		
	}
}
