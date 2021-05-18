package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_Gold2_1655_가운데를말해요 {
	
	static int N;
	static Stack<Integer> front, rear;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 1 <= N <= 100,000
		front = new Stack<>();
		rear = new Stack<>();
		
		LinkedList<Integer> list = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			int in = Integer.parseInt(br.readLine());
			
			
		}
		
	}
}
