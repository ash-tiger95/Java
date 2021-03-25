package com.programmers.stackqueue;

public class Solution_Level2_주식가격 {

	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 2, 3 };

		System.out.println(solution(prices));
	}

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		for (int i = 0; i < answer.length; i++) {
			for (int j = i + 1; j < answer.length; j++) {
				answer[i]++;
				if (prices[i] > prices[j])
					break;
			}
		}
		return answer;
	}

}
