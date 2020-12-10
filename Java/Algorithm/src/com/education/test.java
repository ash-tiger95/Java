package com.education;

public class test {
	public static void main(String[] args) {
		int a = 1;
		int b = dfs(1);
		System.out.println(b);
	}

	private static int dfs(int i) {
		if(i==1) {
			i=2;
		}
		return i;
	}
	
}
