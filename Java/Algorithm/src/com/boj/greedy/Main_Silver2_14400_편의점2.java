package com.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Silver2_14400_편의점2 {

	static int N;
	static int[] y, x;
	static boolean[][][] visited;
	static Queue<int[]> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 고객 수, 1 ≤ N ≤ 100,000

		y = new int[N];
		x = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); // (x,y), -1,000,000 ≤ x, y ≤ 1,000,000
			y[i] = Integer.parseInt(st.nextToken());
			x[i] = Integer.parseInt(st.nextToken());
		}

		// ★ 아이디어
		// y좌표의 중심, x좌표의 중심이 최소 (맨해튼거리여서 그런가...)
		// 이 부분을 잘 모르겠다. 왜 최소가 되는지.
		Arrays.sort(y);
		Arrays.sort(x);

		int midY = y[(N - 1) / 2]; // 홀수든 짝수든 상관없다.
		int midX = x[(N - 1) / 2];

		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += Math.abs(midY - y[i]) + Math.abs(midX - x[i]);
		}

		System.out.println(sum);

		br.close();
	}
}
