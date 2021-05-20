package com.boj.string;

import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;

public class Main_Bronze2_10808_알파벳개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String in = br.readLine();
		
		int[] ans = new int[26];
		for(int i=0;i<in.length();i++) {
			ans[in.charAt(i) - 'a']++;
		}
		
		for(int i=0;i<26;i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
}
