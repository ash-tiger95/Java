package com.boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_Silver4_11656_접미사배열 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String in = br.readLine();
		String[] arr = new String[in.length()];
		
		for(int i=0;i<in.length();i++) {
			arr[i] = in.substring(i);
		}
		
		Arrays.sort(arr);
		for(String str : arr) {
			sb.append(str).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
