package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_Gold4_12757_전설의JBNU {

	static int N, M, K;
	static TreeMap<Integer, Integer> tm;
	static ArrayList<Integer> list; // key

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		tm = new TreeMap<>();
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			tm.put(a, b);
			list.add(a);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == 1) { // 데이터 추가
				tm.put(b, c);
			} else if (a == 2) { // 데이터 수정
				int idx = b;
				int diff = Integer.MAX_VALUE;
				
				if(tm.containsKey(b)) {
					tm.put(b, c);
				} else {
					Collections.sort(list);
					int low = lowerBound(list, b);
					int up = upperBound(list,b);
					
					if(Math.abs(low-b) <= K) {
						
					}
				}
				
				
				
			} else {
				int low = lowerBound(list, b);
				int up = upperBound(list,b);
				
				if(low == up) {
					sb.append("?").append("\n");
					
				}
			}
		}
	}
	
	private static int lowerBound(List<Integer> data, int target) {
	    int begin = 0;
	    int end = data.size();
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(data.get(mid) >= target) {
	        	end = mid;
	        }
	        else {
	        	begin = mid + 1;
	        }
	    }
	    return end;
	}
	
	private static int upperBound(List<Integer> data, int target) {
	    int begin = 0;
	    int end = data.size();
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(data.get(mid) <= target) {
	        	begin = mid + 1;
	        }
	        else {
	        	end = mid;
	        }
	    }
	    return end;
	}

}
