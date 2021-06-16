package com.boj.datastructure.priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold5_11000_강의실배정 {

	static int N;
	static int[][] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		in = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			in[i][0] = Integer.parseInt(st.nextToken());
			in[i][1] = Integer.parseInt(st.nextToken());
		}

		// 1. 오름차순으로 정렬
		Arrays.sort(in, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) { // 시작 시간이 같다면
					return o1[1] - o2[1]; // 끝나는 시간으로 오름차순
				}
				return o1[0] - o2[0];
			}

		});

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(in[0][1]); // 2. 가장 먼저 시작하는 강의 입력

		// 3. 모든 강의 탐색
		for (int i = 1; i < in.length; i++) {
			if (pq.peek() <= in[i][0]) { // pq에 들어있는 강의 후에 끝나는 것이 있으면
				pq.poll(); // 해당 강의 삭제
			}

			pq.add(in[i][1]); // 새롭게 업데이트 || 겹치는 강의가 있으면 새로운 시간 추가
		}

		System.out.println(pq.size()); // 4. pq에 남아있는 강의 개수는 강의실 개수가 된다.
	}
}
