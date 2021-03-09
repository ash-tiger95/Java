package com.programmers.level2;

public class Solution_소수찾기 {
	public static void main(String[] args) {
		String numbers = "17";
		System.out.println(solution(numbers));
	}

	static int len, answer;
	static int[] input;
	static boolean[] isSelected;

	public static int solution(String numbers) {
		answer = 0;

		len = numbers.length();
		input = new int[len];
		isSelected = new boolean[len];

		for (int i = 0; i < len; i++) {
			input[i] = numbers.charAt(i) - '0';
		}
		subSet(0);

		return answer;
	}

	private static void subSet(int cnt) {
		if (cnt == len) {
			int number = 0;
			for (int i = 0; i < len; i++) {
				if (isSelected[i]) {
					number += input[i] * Math.pow(10, i);
				}
			}
			if(is_prime(number)) {
				answer++;
			}
			return;
		}

		isSelected[cnt] = true;
		subSet(cnt + 1);

		isSelected[cnt] = false;
		subSet(cnt + 1);
	}

	public static boolean is_prime(int number) {
		// 0 과 1 은 소수가 아니다
		if (number < 2) {
			return false;
		}
		// 2 는 소수다
		if (number == 2) {
			return true;
		}
		// 제곱근 함수 : Math.sqrt()
		for (int i = 2; i <= Math.sqrt(number); i++) {
			// 소수가 아닐경우
			if (number % i == 0) {
				return false;
			}
		}
		// 위 반복문에서 약수를 갖고 있지 않는경우 소수다.
		return true;
	}

}
