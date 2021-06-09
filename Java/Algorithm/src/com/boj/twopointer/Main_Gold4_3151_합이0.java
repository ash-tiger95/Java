package com.boj.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 기본 문제) Gold4 2473 세 용액 (Two Pointer)
 * @author jugia
 *
 */
public class Main_Gold4_3151_합이0 {

	static int N;
	static long ans; // ★ 신경쓰자!
	static int[] in, out;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학생 수, 1 <= N <= 10,000
		in = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken()); // 학생들의 코딩 실력, -10,000 <= in[i] <= 10,000
		}

		ans = 0; // 3명의 학생의 코딩실력 합이 0이 되는 경우의 수 구하기

		// 풀이1. nCr, 시간초과
		out = new int[3];
		nCr(0, 0);

		// 풀이2. 투 포인터
		Arrays.sort(in);

		// 하나를 기준으로 left, right 포인터를 사용한다.
		for (int i = 0; i < N - 2; i++) {
			int choice = in[i]; // 첫번째 기준점

			if (choice > 0) {
				break;
			}

			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				int sum = choice + in[left] + in[right];

				if (sum == 0) {
					if (in[left] == in[right]) { // ... 3(left) 3 3 3 3 3 3(right) ... 인 경우
						ans += (right - left + 1) * (right - left) / 2; // nC2
						break;
					} else { // ... -1(left) -1 -1 -1 -1 ... 5 5 5 5 5 5(right) ... 인 경우
						int tempR = in[right];
						int r = 0;

						while (true) {
							if (in[right] != tempR) {
								break;
							} else {
								right--;
								r++;
							}
						}

						int tempL = in[left];
						int l = 0;

						while (true) {
							if (in[left] != tempL) {
								break;
							} else {
								left++;
								l++;
							}
						}

						ans += r * l; // 끝나면 다음 숫자로 포인터가 바뀐다.
					}
				} else if (sum > 0) {
					right--;
				} else {
					left++;
				}
			}
		}

		System.out.println(ans);
		
		br.close();
	}

	private static void nCr(int cnt, int cur) {
		if (cnt == 3) {
			if (in[out[0]] + in[out[1]] + in[out[2]] == 0) {
				ans++;
			}

			return;
		}

		for (int i = cur; i < N; i++) {
			out[cnt] = i;
			nCr(cnt + 1, i + 1);
		}
	}
}
