package com.programmers.level1;

import java.util.Stack;

public class Solution_크레인인형뽑기게임 {
	public static void main(String[] args) {

		int n = 3;
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(solution(board, moves));
	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0; // 크레인을 모두 작동시킨 후 사라진 인형의 개수
		Stack<Integer> basket = new Stack<>();

		for (int i = 0; i < moves.length; i++) { // 크레인이 뽑는 순서만큼
			System.out.println(i +" : " + moves[i]);
			for (int j = 0; j < board.length; j++) {
				
				if (board[j][moves[i] - 1] != 0) {
					basket.push(board[j][moves[i] - 1]);
					board[j][moves[i] - 1] = 0;

					print(board, board.length);
					System.out.println("stack: " + basket);
					System.out.println();

					if (basket.size() >= 2) {
						if (basket.elementAt(basket.size() - 1) == basket.elementAt(basket.size() - 2)) {
							
							basket.pop();
							basket.pop();
							answer++;
						}
					}
					break;
				}
				
			} // for board
		} // for moves

		return answer*2;
	}

	private static void print(int[][] arr, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
