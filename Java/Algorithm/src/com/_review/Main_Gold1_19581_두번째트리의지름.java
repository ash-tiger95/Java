package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold1_19581_두번째트리의지름 {

	static int N;
	static int[][] adj;
	
	static class Node implements Comparable<Node>{
		int dest, value;
		
		public Node(int dest, int value) {
			this.dest = dest;
			this.value	 = value;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			
			adj[a][b] = adj[b][a] = c;
		}
		
		
	}

}
