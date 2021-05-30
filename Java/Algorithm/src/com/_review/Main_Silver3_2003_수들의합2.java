package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Silver3_2003_수들의합2 {

	static int N, M;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		in = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		int result = 0;

		// m : 슬라이딩 윈도우 - 몇개씩 탐색할 것인지..
		for (int m = 0; m < N; m++) {
			int left = 0, right = m; // left부터 right까지: 윈도우의 범위 index
			int sum = 0; // 윈도우 범위의 부분합

			// 초기 sum값 세팅 -> 윈도우 범위만큼의 값을 최초에 가짐
			for (int i = 0; i <= right; i++) {
				sum += in[i];
			}
			if (sum == M)
				result++;
			right++;

			// right 인덱스의 범위가 n을 벗어나기 전까지 반복
			while (right < N) {
				// [윈도우] 의 다음 범위는 [윈도우] - arr[left] + arr[right] 임.
				sum -= in[left++];
				sum += in[right++];
				// target 값과 비교하여 같다면 result 값 1 증가
				if (sum == M)
					result++;
			}
		}
		System.out.println(result);
	}
}
