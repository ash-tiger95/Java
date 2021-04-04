package com.test.scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 일차04_안본콘텐츠없게해주세요 {
	
	static int N,M;
	static double[] prefer; // 콘텐츠 선호도
	static char[][] info; // 콘텐츠 열람 정보
	static char[][] genre; // 콘텐츠 장르 정보
	
	static class Node implements Comparable<Node>{
		int y,x; // y, x
		char gen; // 장르
		double pre;// 선호도
		
		public Node(char gen, double pre, int y, int x) {
			super();
			this.gen = gen;
			this.pre = pre;
			this.y = y;
			this.x = x;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.pre == o.pre) {
				if(this.y == o.y) {
					return Integer.compare(this.x, o.x);
				} else {
					return Integer.compare(this.y, o.y);
				}
			} else {
				return (-1)*Double.compare(this.pre, o.pre);
			}
		}
		
		@Override
		public String toString() {
			return gen+" "+pre+" "+y+" "+x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		prefer = new double[5];
		for(int i=0;i<5;i++) {
			prefer[i] = Double.parseDouble(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		
		info = new char[N][M];
		for(int i=0;i<N;i++) {
			info[i] = br.readLine().toCharArray();
		}
		
		genre = new char[N][M];
		for(int i=0; i<N;i++) {
			genre[i] = br.readLine().toCharArray();
		}
		
		PriorityQueue<Node> pqY = new PriorityQueue<>();
		PriorityQueue<Node> pqW = new PriorityQueue<>();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(info[i][j] == 'Y') {
					pqY.offer(new Node(genre[i][j], prefer[genre[i][j]-'A'], i,j));
				} else if(info[i][j] == 'O') {
					pqW.offer(new Node(genre[i][j], prefer[genre[i][j]-'A'], i,j));
				}
			}
		}
		
		int size = pqY.size();
		for(int i=0;i<size;i++) {
			Node cn = pqY.poll();
			System.out.println(cn);
		}
		
		size = pqW.size();
		for(int i=0;i<size;i++) {
			Node cn = pqW.poll();
			System.out.println(cn);
		}
	}
}
