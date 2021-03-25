package test.challenge;

import java.util.ArrayList;

public class Solution_Level1_두개뽑아서더하기 {
	public static void main(String[] args) {
		int[] numbers = { 2, 1, 3, 4, 1 }; // 5,0,2,7

		System.out.println(solution(numbers));
	}

	public static ArrayList<Integer> solution(int[] numbers) {
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (!list.contains(numbers[i] + numbers[j])) {
					list.add(numbers[i] + numbers[j]);
				}
			}
		}

		list.sort(null); // 오름차순 정렬

		return list;
	}

}
