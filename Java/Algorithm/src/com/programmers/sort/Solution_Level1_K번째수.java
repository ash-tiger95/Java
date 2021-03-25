package com.programmers.sort;

import java.util.Arrays;

public class Solution_Level1_K번째수 {
	public static void main(String[] args) {
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };

		System.out.println(solution(array, commands));
	}

	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		for (int i = 0; i < commands.length; i++) {
			// 내 풀이
			int start = commands[i][0];
			int end = commands[i][1];
			int num = commands[i][2];

			int[] temp = new int[end - start + 1];
			int idx = 0;
			for (int j = start - 1; j < end; j++) {
				temp[idx++] = array[j];
			}

			Arrays.sort(temp);
//            System.out.println(Arrays.toString(temp));

			answer[i] = temp[num - 1];
//            System.out.println(Arrays.toString(answer));

			// 간결한 풀이
			int[] temp2 = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]); // 위에거랑 같은 의미 start부터 end-1까지
			Arrays.sort(temp2);
			answer[i] = temp2[commands[i][2] - 1];
		}
		return answer;
	}
}
