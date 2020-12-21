package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class Main_Silver5_1181_단어정렬_Comparator {
	static int N;
	static TreeSet<String> ts;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		ts = new TreeSet<String>(new Comparator<String>() {

			@Override
			public int compare(String pre, String next) {
				if(pre.length() != next.length()) {
					return pre.length() - next.length();
				}
				else {
					return pre.compareTo(next);
				}
			}
			
		});
		
		for(int i=0;i<N;i++) {
			ts.add(br.readLine());
		}
		
		for(String name : ts) {
			System.out.println(name);
		}
	}
}
