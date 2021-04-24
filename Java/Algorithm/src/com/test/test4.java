package com.test;

import java.util.ArrayList;
import java.util.List;

public class test4 {

	static int K;
	static int[] num = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 }; // 성냥깨비 개수
	static int answer;
	static int[] memoi;

	private static int solution(int k) {
		K = k;
		answer = 0;
		
		memoi = new int[k];

		return answer;
	}

	
	private static int topDown2(int start) {
		if (start <= 1) {
			return 0;
		}

		if (memoi[start] > 0) {
			return memoi[start];
		}

		// 가장 오래 걸리는 순서
		memoi[start] = topDown2(start - 1) + 1;

		// 업데이트
		if (start % 2 == 0) {
			int tmp = topDown2(start / 2) + 1;
			if (memoi[start] > tmp) {
				memoi[start] = tmp;
			}
		}

		// 업데이트
		if (start % 3 == 0) {
			int tmp = topDown2(start / 3) + 1;
			if (memoi[start] > tmp) {
				memoi[start] = tmp;
			}
		}

		return memoi[start];
	}

	public static void main(String[] args) {
		int k = 40; // 1 <= k <= 50
		System.out.println(solution(k));
	}

}
