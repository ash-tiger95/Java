package com._review.swe;

import java.util.ArrayList;

public class test {
	public static void main(String[] args) {
		// 매개변수 이어서 쓰기 test
		int a = 1;
		int b = dfs(1);
		System.out.println(b);

		// break 문법 test
		outer: for (int i = 0; i < 100 - 1; i++) {
			System.out.println(i + "-----------");
			for (int j = i + 1; j < 100; j++) {
				if (j == 10) {
					break outer;
				}
				System.out.println(j);
			}
		}
		System.out.println("end");

		// 사칙연산 test
		a += 1 + 34;
		System.out.println(a);

		// 2차원 리스트 test
		ArrayList<Integer>[][] myList = new ArrayList[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				myList[i][j] = new ArrayList<Integer>();
				int n = (int) (Math.random() * 4);
				for (int q = 0; q < n; q++) {
					myList[i][j].add((int) (Math.random()));
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.printf("%d ", myList[i][j].size());
			}
			System.out.printf("\n");
		}
		
		// 논리연산자 && ||
		int x= 1;
		int y=2;
		int z = 3;
		if(x == 1 && y == 2 || z == 4) {
			System.out.println("true");
		}
		if(x == 1 && y == 3 || z == 4) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		if(x == 0 || y == 2 && z == 4) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
		if(x == 0 || y == 2 && z == 4 || y==2 && z==3) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
		
		
		//DFS
		dfs2(0,0,0,false);
		
		int[][] map = new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++){
				map[i][j] = 1;
			}
		}
		dfs3(0,0,map);
	}

	private static int dfs(int i) {
		if (i == 1) {
			i = 2;
		}
		return i;
	}
	
	private static void dfs2(int time, int y,int x,boolean b) {
		System.out.println(time+" "+y+" "+x+ " "+b);
		if(time == 5) {
			System.out.println(time+" "+y+" "+x+ " "+b);
			return;
		}
		if(time<5) {
			dfs2(++time, ++y,++x,!b);
		}
	}
	
	private static void dfs3(int y, int x, int[][] map) {
		System.out.println(y+" "+x);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		if(x == 3 || y == 3) {
			return;
		}
		map[y][x]++;
		dfs3(y+1,x+1,map);
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("============");
	}

}
