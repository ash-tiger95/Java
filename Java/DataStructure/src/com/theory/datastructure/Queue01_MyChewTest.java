package com.theory.datastructure;

import java.util.LinkedList;
import java.util.Queue;

public class Queue01_MyChewTest {
	public static void main(String[] args) {
		int N = 10;
		int person = 1;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {person, 1});
		
		while(N>0) {
			int availableCnt;
			if(!queue.isEmpty()) {
				int[] p = queue.poll();
				availableCnt = (N>p[1]) ? p[1] : N;
				N -= availableCnt;
				if(N == 0) {
					System.out.println("마지막 마이쮸를 가져가는 사람: "+p[0] +", 가져간 개수: "+availableCnt);
				} else {
					System.out.println(p[0]+"번 사람이 "+p[1]+"개수의 마이쮸를 가져갑니다.");
					p[1]++;
					queue.offer(p);
					queue.offer(new int[] {++person,1});
				}
			}
		}
	}
}
