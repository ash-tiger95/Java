package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_Gold5_2075_N번째큰수 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
		
		int size= pq.size();
		for(int s=0;s<size;s++) {
			if(s == N-1) {
				System.out.println(pq.poll());
				break;
			}
			pq.poll();
		}
	}

}
