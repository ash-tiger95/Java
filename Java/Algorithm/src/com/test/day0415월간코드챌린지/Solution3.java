package com.test.day0415월간코드챌린지;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
	static boolean[] noTouch;
	static List<Integer> list;
	static int[][] adj;
	
	public static long solution(int[] a, int[][] edges) {
        long answer = -2;
        
        int N = a.length;
        int max = 0;
        
        adj = new int[N][N];
        for(int i=0;i<edges.length;i++) {
        	adj[edges[i][0]][edges[i][1]] = adj[edges[i][1]][edges[i][0]] = 1;
        }
        
        
        list = new ArrayList<>();
        for(int i=0;i<N;i++) {
        	if(max < Math.abs(a[i])) {
        		list.clear();
        		max = Math.abs(a[i]);
        		list.add(i);
        	} else if(max == Math.abs(a[i])) {
        		list.add(i);
        	}
        }
        
        
        noTouch  = new boolean[N];
        if(list.size() == 1) {
        	for(Integer i : list) {
        		noTouch[i] = true;
        	}
        } else {
        	
        }
        
        
        
        return answer;
    }
	
	private static void recur(int node) {
		if(list.contains(node)) {
			
		}
	}

	public static void main(String[] args) {
		int[] a = {-5,0,2,1,2};
		int[][] edges = {{0,1},{3,4},{2,3},{0,3}};
		System.out.println(solution(a, edges));
	}
}
