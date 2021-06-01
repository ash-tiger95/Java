package com.theory.algorithm;

import java.util.Arrays;

public class Recursive {

	public static void main(String[] args) {
		
		dfs(new boolean[10], 0);
	}
	
	private static void dfs(boolean[] visited, int cnt) {
		
//		System.out.println(Arrays.toString(visited));
		
		if(cnt == 5) {
			System.out.println("===========return: " + Arrays.toString(visited));
			return;
		}
		
		visited[cnt] = true;
		System.out.println(cnt+" "+"BEFORE"+"\t" +Arrays.toString(visited));
		
		dfs(visited, cnt+1);
		
		System.out.println(cnt+" "+"AFTER"+"\t\t" +Arrays.toString(visited));
		visited[cnt] = false;
		
//		System.out.println("RETURN"+" " +Arrays.toString(visited));
	}
}
