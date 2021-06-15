package com.swea.sw문제해결기본;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1일차_D3_1208_Flatten {

	static int dump, ans;
	static int[] box;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\1208.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 10; t++) {
			sb.append("#").append(t + 1).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			dump = Integer.parseInt(st.nextToken()); // 덤프 횟수, 1 <= dump <= 1,000

			box = new int[101]; // box의 높이별로 저장, 1 <= 높이 <= 100

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) { // 가로 길이는 항상 100
				box[Integer.parseInt(st.nextToken())]++;
			}

			// box에서 가장 큰 높이, 가장 낮은 높이 찾기
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 100; i++) {
				if (box[i] != 0) {
					min = i;
					break;
				}
			}

			for (int i = 100; i >= 0; i--) {
				if (box[i] != 0) {
					max = i;
					break;
				}
			}

			ans = 0;
			if (max != min) {
				while (dump-- > 0) {
					box[max]--;
					box[max - 1]++;
					box[min]--;
					box[min + 1]++;

					if (box[max] == 0) {
						max--;
					}

					if (box[min] == 0) {
						min++;
					}

					if (max == min) {
						break;
					}
				}
			}

			sb.append(max - min).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
