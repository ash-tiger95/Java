package com.test.devmatching;

import java.util.Arrays;

/*
 	순위		당첨 내용
	1		6개 번호가 모두 일치
	2		5개 번호가 일치
	3		4개 번호가 일치
	4		3개 번호가 일치
	5		2개 번호가 일치
	6(낙첨)	그 외
	
	당첨 번호		31	10		45	1	6	19	결과
	최고 순위 번호	31	0→10	44	1	0→6	25	4개 번호 일치, 3등
	최저 순위 번호	31	0→11	44	1	0→7	25	2개 번호 일치, 5등
 */
public class Solution01_로또순위 { // 성공

	public static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];

		Arrays.sort(win_nums);
		Arrays.sort(lottos);

		System.out.println(Arrays.toString(win_nums));
		System.out.println(Arrays.toString(lottos));

		int possibleCnt = 0;
		int zeroCnt = 0;
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (win_nums[i] == lottos[j]) {
					possibleCnt++;
					break;
				}
			}
		}

		for (int i = 0; i < 6; i++) {
			if (lottos[i] == 0) {
				zeroCnt++;
			}
		}

		if (possibleCnt == 0 && zeroCnt == 0) {
			answer[0] = 6;

		} else {

			answer[0] = (possibleCnt + zeroCnt) % 6 == 0 ? 1 : 6 - (possibleCnt + zeroCnt) % 6 + 1; // 최고 순위
		}
		answer[1] = (possibleCnt) == 0 ? 6 : 6 - possibleCnt + 1; // 최저 순위

		System.out.println(Arrays.toString(answer));
		return answer;
	}

	/*
	 	입출력 예시
		lottos 					win_nums 					result 
		[44, 1, 0, 0, 31, 25] 	[31, 10, 45, 1, 6, 19] 		[3, 5]
		[0, 0, 0, 0, 0, 0] 		[38, 19, 20, 40, 15, 25] 	[1, 6] 
		[45, 4, 35, 20, 3, 9] 	[20, 9, 3, 45, 4, 35] 		[1, 1]
	 */
	public static void main(String[] args) {
		// testcase 1.
//		int[] lottos = {44, 1, 0, 0, 31, 25};
//		int[] win_nums = {31, 10, 45, 1, 6, 19};

		// testcase 2.
//		int[] lottos = {0, 0, 0, 0, 0, 0};
//		int[] win_nums = {38, 19, 20, 40, 15, 25};

		// testcase 3.
//		int[] lottos = {45, 4, 35, 20, 3, 9};
//		int[] win_nums = {20, 9, 3, 45, 4, 35};

		// testcase 4.
		int[] lottos = { 1, 2, 3, 4, 5, 6 };
		int[] win_nums = { 7, 8, 9, 10, 11, 12 };
		System.out.println(solution(lottos, win_nums));
	}
}
