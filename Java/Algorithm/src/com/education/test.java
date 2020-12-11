package com.education;

public class test {
	public static void main(String[] args) {
		int a = 1;
		int b = dfs(1);
		System.out.println(b);
		
		
		outer : for(int i=0;i<100-1;i++) {
			System.out.println(i+"-----------");
			for(int j=i+1;j<100;j++) {
				if(j == 10) {
					break outer;
				}
				System.out.println(j);
			}
		}
		System.out.println("end");
		
		a += 1+34;
		System.out.println(a);
	}

	private static int dfs(int i) {
		if(i==1) {
			i=2;
		}
		return i;
	}
	
}
