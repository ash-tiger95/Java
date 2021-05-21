package com._review.swe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_Test_4013_특이한자석_Simulation {
	static int T, K, Ans;
	static int[][] map;
	static int[][] rotate;
	static int[] isValid;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			Ans = 0;
			K = Integer.parseInt(br.readLine());
			map = new int[4][8];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); // N극: 0, S극: 1
				}
			}
			rotate = new int[K][2]; // [0]: 자석 번호, [1]: 회전방향(1: 시계방향, -1: 반시계방향)
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				rotate[i][0] = Integer.parseInt(st.nextToken())-1; // 자석 번호
				rotate[i][1] = Integer.parseInt(st.nextToken()); // 회전방향(1: 시계방향, -1: 반시계방향)
			}
			
			
			for(int k=0;k<K;k++) {
				int curNum = rotate[k][0];
				int curDir = rotate[k][1]; // 회전방향(1: 시계방향, -1: 반시계방향)
				isValid = new int[4];
				isValid[curNum] = curDir;
//				System.out.println("start: "+Arrays.toString(isValid));
				
				
				// check right
				if(curNum != 3) {
					for(int i=curNum+1;i<4;i++) {
						if(map[i-1][2] != map[i][6]) {
							
							isValid[i] = isValid[i-1]*(-1);
						} else {
							break;
						}
					}
				}
				// chek left
				if(curNum != 0) {
					for(int i=curNum-1;i>=0;i--) {
						if(map[i+1][6] != map[i][2]) {
							
							isValid[i] = isValid[i+1]*(-1);
						}else {
							break;
						}
					}
				}
//				System.out.println(Arrays.toString(isValid));
				
				for(int i=0;i<4;i++) {
					if(isValid[i] != 0) {
						rotation(isValid[i], map[i]);
					}
				}
//				for(int i=0;i<4;i++) {
//					for(int j=0;j<8;j++) {
//						System.out.print(map[i][j]+" ");
//					}
//					System.out.println();
//				}
//				System.out.println("==========");
				// N극은 0점, S극은 1,2,4,8점
				
					
				
			}
			for(int i=0;i<4;i++) {
				if(map[i][0] == 1) {
					Ans += Math.pow(2,i);
				}
			}

			sb.append(Ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
	private static void rotation(int dir, int[] sawtooth) { // 톱니 회전시키기(방향, 톱니정보)
		if(dir == 1) { // 시계 방향
			int temp = sawtooth[7];
			for(int i=6;i>=0;i--) {
				sawtooth[i+1] = sawtooth[i];
			}
			sawtooth[0] = temp;
		} else {
			int temp = sawtooth[0];
			for(int i=1;i<8;i++) {
				sawtooth[i-1] = sawtooth[i];
			}
			sawtooth[7] = temp;
		}
	}
	
}
