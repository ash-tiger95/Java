package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold3_11049_행렬곱샘순서 {

	static int N;
	static int[][] input;
	static int[][] memoi;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		input = new int[N][2];
		memoi = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}

		find(0, N - 1);
		System.out.println(memoi[0][N - 1]);
	}

	private static int find(int x, int y) {
		int Ans = Integer.MAX_VALUE;

		if (x == y) {
			return 0;
		}

		if (memoi[x][y] != 0) {
			return memoi[x][y];
		}

		for (int i = x; i < y; i++) {
			Ans = Math.min(Ans, find(x, i) + find(i + 1, y) + input[x][0] * input[i][1] * input[y][1]);
			memoi[x][y] = Ans;
		}

		return Ans;
	}
}
