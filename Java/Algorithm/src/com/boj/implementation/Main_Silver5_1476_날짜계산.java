package com.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이)
 * 1 1 1년 부터 차례대로 올라간다.
 * 
 * @author jugia
 *
 */
public class Main_Silver5_1476_날짜계산 {
	
	static int E,S,M,e,s,m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		E = Integer.parseInt(st.nextToken()); // 1 <= E <= 15
		S = Integer.parseInt(st.nextToken()); // 1 <= S <= 28
		M = Integer.parseInt(st.nextToken()); // 1 <= M <= 19
		
		e = s = m = 1;
		
		int Ans = 1;
		
		while(true) {
			if(e == E && s == S && m == M) {
				break;
			}
			
			e++;
			s++;
			m++;
			
			if(e > 15) {
				e = 1;
			}
			
			if(s > 28) {
				s = 1;
			}
			
			if(m > 19) {
				m = 1;
			}
			
			Ans++;
		}
		
		System.out.println(Ans);
	}
}
