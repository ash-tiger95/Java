package com.boj.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Silver2_15658_연산자끼워넣기2 {

	static int N;
	static long max, min;
	static int[] num;
	static int[] operator;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수의 개수, 2 ≤ N ≤ 11

		st = new StringTokenizer(br.readLine());
		num = new int[N]; // 숫자, 1 ≤ num ≤ 100
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		operator = new int[4];
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken()); // +, -, x, /
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		dfs(1, num[0]);

		sb.append(max).append("\n").append(min);
		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static void dfs(int cnt, long value) {
		if (cnt == N) {
			if (max < value) {
				max = value;
			}

			if (min > value) {
				min = value;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				if (i == 0) {
					operator[i]--; // 연산자 사용하고
					dfs(cnt + 1, value + num[cnt]);
					operator[i]++; // 다시 채우기
				} else if (i == 1) {
					operator[i]--;
					dfs(cnt + 1, value - num[cnt]);
					operator[i]++;
				} else if (i == 2) {
					operator[i]--;
					dfs(cnt + 1, value * num[cnt]);
					operator[i]++;
				} else {
					operator[i]--;

					if (value < 0) { // 음수를 양수로 나눌 때
						value *= (-1);
						value = value / num[cnt];
						value *= (-1);
					} else {
						value = value / num[cnt];
					}

					dfs(cnt + 1, value);
					operator[i]++;
				}
			}
		}
	}
}
