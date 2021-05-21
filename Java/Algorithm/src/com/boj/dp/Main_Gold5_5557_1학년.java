package com.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이) DP
 * 실패 풀이) Recur -> 시간 초과 
 * 
 * 아이디어 암기!
 * 
 * @author jugia
 *
 */
public class Main_Gold5_5557_1학년 {

	static int N, Ans;
	static int[] in;
	static long[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		in = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		count = new long[21]; // 0부터 20까지 방문한 횟수를 저장한다.
		count[in[0]]++;

		cal(0);
		System.out.println(count[in[N-1]]);
	}

	private static void cal(int index) {
		if (index == N - 2) {
			return;
		}

		long temp[] = new long[21];
		
		for (int i = 0; i <= 20; i++) {
			
			if (count[i] != 0) {
				if (i - in[index + 1] >= 0) { // count[i]를 중심으로 왼쪽
					temp[i - in[index + 1]] += count[i];
				}

				if (i + in[index + 1] <= 20) { // count[i]를 중심으로 오른쪽
					temp[i + in[index + 1]] += count[i];
				}
			}
			
		}
		
		count = temp.clone();
		cal(index+1);
	}
}
