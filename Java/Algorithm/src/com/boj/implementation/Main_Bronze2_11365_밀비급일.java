package com.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Bronze2_11365_밀비급일 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String in = br.readLine();
			
			if(in.equals("END")) {
				break;
			}
			
			char[] arr = in.toCharArray();
			for(int i=arr.length-1 ; i >=0 ; i--) {
				sb.append(arr[i]);
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
