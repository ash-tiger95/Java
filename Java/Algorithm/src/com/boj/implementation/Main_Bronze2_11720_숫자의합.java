package com.boj.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Bronze2_11720_숫자의합 {

	static int N, ans;
	static char[] num;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 100
		
		num = new char[N];
		num = br.readLine().toCharArray();
		
		ans = 0;
		for(int i=0;i<N;i++) {
			ans += num[i]-'0';
		}
		
		sb.append(ans);

		bw.write(sb.toString());
        bw.flush();

        br.close(); 
        bw.close();
	}
}
