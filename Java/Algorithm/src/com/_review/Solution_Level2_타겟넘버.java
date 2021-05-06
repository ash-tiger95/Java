package com._review;

public class Solution_Level2_타겟넘버 {

	static int N, Ans;

	public static int solution(int[] numbers, int target) {
		Ans = 0;

		N = numbers.length;

		dfs(numbers, target, 0, 0);

		return Ans;
	}

	public static void dfs(int[] numbers, int target, int cnt, int value) {
		if (cnt == N) {
			if (value == target) { // target과 일치하면 개수 증가
				Ans++;
			}
			return;
		}

		dfs(numbers, target, cnt + 1, value + numbers[cnt]); // 더하는 경우
		dfs(numbers, target, cnt + 1, value - numbers[cnt]); // 빼는 경우

	}

	public static void main(String[] args) {
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		System.out.println(solution(numbers, target));
	}
}
