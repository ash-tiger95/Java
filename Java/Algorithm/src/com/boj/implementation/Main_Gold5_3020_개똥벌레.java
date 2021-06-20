package com.boj.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 내 풀이) 단순 구현 (Implementation) -> 시간초과
 * 현재 풀이) 구간합(Prefix Sum), 아이디어!!!
 * 
 * @author jugia
 *
 */
public class Main_Gold5_3020_개똥벌레 {

	static int N, H;
	static boolean[][] cave;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 동굴의 길이, 2 ≤ N ≤ 200,000
		H = Integer.parseInt(st.nextToken()); // 동굴의 높이, 2 ≤ H ≤ 500,000

		cave = new boolean[H][N];
		for (int i = 1; i <= N; i++) { // 홀수: 석순, 짝수: 종유석
			st = new StringTokenizer(br.readLine());

			if (i % 2 == 1) { // 석순
				makeSukSun(i - 1, Integer.parseInt(st.nextToken()));
			} else { // 종유석
				makeJongUSuk(i - 1, Integer.parseInt(st.nextToken()));
			}
		}

		int min = N;
		int cnt = 0;
		
		for (int i = 0; i < H; i++) {
			int tempMin = 0;

			for (int j = 0; j < N; j++) {
				if (cave[i][j]) {
					tempMin++;
				}
			}

			if (tempMin < min) {
				min = tempMin;
				cnt = 1;
			} else if (tempMin == min) {
				cnt++;
			}
		}

		sb.append(min).append(" ").append(cnt);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	private static void makeSukSun(int n, int h) {
		int index = H - 1;
		while (h-- > 0) {
			cave[index--][n] = true;
		}
	}

	private static void makeJongUSuk(int n, int h) {
		int index = 0;
		while (index < h) {
			cave[index++][n] = true;
		}
	}
}
