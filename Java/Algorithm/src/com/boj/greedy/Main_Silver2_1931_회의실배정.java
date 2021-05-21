package com.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 풀이) Comparator
 * 종료시간을 중심으로 종료시간이 빠른 것을 찾아낸다.
 * 
 * @author jugia
 *
 */
public class Main_Silver2_1931_회의실배정 {

	static int N;
	static int[][] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		time = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
			time[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
		}

		Arrays.sort(time, new Comparator<int[]>() { // 익명 클래스

			@Override
			public int compare(int[] o1, int[] o2) {

				if (o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}

				return o1[1] - o2[1]; // 오름차순
			}

		});

		int count = 0;
		int endTime = 0;
		for (int i = 0; i < N; i++) {
			if (endTime <= time[i][0]) {
				endTime = time[i][1];
				count++;
			}
		}

		System.out.println(count);
	}
}
