package com.swea.sw문제해결기본;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1일차_D3_1206_View {

	static int T, ans;
	static int[] apartment;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\1206.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 10; t++) {
			sb.append("#").append(t + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());

			apartment = new int[T];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < T; i++) {
				apartment[i] = Integer.parseInt(st.nextToken());
			}

			ans = 0;
			for (int i = 0; i < T; i++) {
				int left = -1;
				int right = -1;
				
				// i를 기준으로 양 옆을 검사한다. 사실 4개를 한 번에 비교해서 가장 큰 수를 찾으면 된다.
				if (i == 0) {
					left = 0;
					right = apartment[i + 2] > apartment[i + 1] ? apartment[i + 2] : apartment[i + 1];
				} else if (i == 1) {
					left = apartment[0];
					right = apartment[i + 2] > apartment[i + 1] ? apartment[i + 2] : apartment[i + 1];
				} else if (i == T - 2) {
					left = apartment[i - 2] > apartment[i - 1] ? apartment[i - 2] : apartment[i - 1];
					right = apartment[i + 1];
				} else if (i == T - 1) {
					left = apartment[i - 2] > apartment[i - 1] ? apartment[i - 2] : apartment[i - 1];
					right = 0;
				} else {
					left = apartment[i - 2] > apartment[i - 1] ? apartment[i - 2] : apartment[i - 1];
					right = apartment[i + 2] > apartment[i + 1] ? apartment[i + 2] : apartment[i + 1];
				}

				// i-2, i-1, i+1, i+2 중 가장 큰 수를 찾아서 i와 뺀다
				int cur = apartment[i];
				if (cur >= right && cur >= left) {
					int temp = right > left ? right : left;
					ans += cur - temp;
				}

			}

			sb.append(ans).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
