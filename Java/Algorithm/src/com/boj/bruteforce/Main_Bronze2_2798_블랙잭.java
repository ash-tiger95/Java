package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Bronze2_2798_블랙잭 {

	static int N, M;
	static int[] card;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		card = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(card);

		int max = 0;

		// 역으로 탐색하는데 어차피 전부다 검사해야되서 상관없다.
		// 잘못된 생각: 가장 큰수가 처음 나오면 끝내는 걸로... 하지만 전부다 검사를 해봐야한다.
		for (int i = N - 1; i >= 2; i--) {
			int temp = card[i];
			if (temp > M) {
				continue;
			}

			for (int j = i - 1; j >= 1; j--) {
				temp += card[j];

				if (temp > M) {
					temp -= card[j];
					continue;
				}

				for (int k = j - 1; k >= 0; k--) {
					temp += card[k];

					if (temp > M) {
						temp -= card[k];
						continue;
					} else {
						max = max > temp ? max : temp;
						temp -= card[k];
					}
				}

				temp -= card[j];
			}
		}

		sb.append(max);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

}
