package com.test;

import java.util.Arrays;

public class test1 {
	
	static int[][] temp;
	
	private static int[] solution(int[][] blocks) {
		int high = blocks.length;
		int size = 0;
		
		for(int i=1;i<=high;i++) {
			size += i;
		}
		
		int[] answer = new int[size];
		
		temp = new int[high][high]; // 피라미드 데이터 저장
		
		// 맨 꼭대기 층 넣기
		temp[0][0] = blocks[0][1];
		answer[0] = blocks[0][1];
		
		// answer에 차례대로 넣기 위함
		int index = 1;
		
		for(int i=1;i<high;i++) {  // 두번째 층부터 시작
			temp[i][blocks[i][0]] = blocks[i][1]; // 각 층의 시작점 설정
			
			leftCheck(blocks[i][0], i); // 시작점 중심으로 왼쪽 검사
			rightCheck(blocks[i][0], i); // 시작점 중심으로 오른쪽 검사
			
			for(int j=0;j<=i;j++) { // 구한 데이터 answer에 넣기
				answer[index++] = temp[i][j];
			}
		}
		
		System.out.println(Arrays.toString(answer));
		
		
		
		return answer;
	}
	
	private static void leftCheck(int num, int len) {
		int prev = num - 1;

		if (prev >= 0) { // 왼쪽  검사
			temp[len][prev] = temp[len-1][prev] - temp[len][prev+1];
			
			leftCheck(prev, len);
		}
	}
	
	private static void rightCheck(int num, int len) {
		int next = num + 1;
		
		if (next <= len) { // 오른쪽  검사
			temp[len][next] = temp[len-1][next-1] - temp[len][next-1];
			
			rightCheck(next,len);
		}
	}
	
	
	public static void main(String[] args) {
		int[][] blocks = {{0,50},{0,22},{2,10},{1,4},{4,-13}};
		
		System.out.println(solution(blocks));
	}

	
	
}
