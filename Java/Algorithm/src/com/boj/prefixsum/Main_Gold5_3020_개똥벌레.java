package com.boj.prefixsum;

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
	static int[] bot, top;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 동굴의 길이, 2 ≤ N ≤ 200,000
		H = Integer.parseInt(st.nextToken()); // 동굴의 높이, 2 ≤ H ≤ 500,000

		bot = new int[H + 1];
		top = new int[H + 1];

		for (int i = 0; i < N / 2; i++) {
			bot[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}

//		System.out.println(Arrays.toString(bot));
//		System.out.println(Arrays.toString(top));

		int[] botSum = new int[H + 1];
		int[] topSum = new int[H + 1];

		// 누적합
		for (int i = 1; i <= H; i++) {
			botSum[i] = botSum[i - 1] + bot[i];
			topSum[i] = topSum[i - 1] + top[i];
		}

//		System.out.println(Arrays.toString(botSum));
//		System.out.println(Arrays.toString(topSum));

		int cnt = 0;
		int min = N;

		for (int i = 1; i <= H; i++) {
			int crush = 0;

			// 부딪히는 석순의 개수 = 전체 석순 개수 - (h-i)이하인 석순의 개수
			crush += botSum[H] - botSum[H - i];

			// 부딪히는 종유석의 개수 = 전체 종유석 개수 - (i-1)이하인 종유석의 개수
			crush += topSum[H] - topSum[i - 1];

			if (min > crush) {
				min = crush;
				cnt = 1;
			} else if (min == crush) {
				cnt++;
			}
		}

		sb.append(min).append(" ").append(cnt);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
