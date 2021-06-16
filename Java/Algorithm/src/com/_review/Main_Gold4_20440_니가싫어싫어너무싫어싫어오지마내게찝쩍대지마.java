package com._review;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Gold4_20440_니가싫어싫어너무싫어싫어오지마내게찝쩍대지마 {

	static int N;
	static int[][] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		time = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(time, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				} else {
					return Integer.compare(o1[0], o2[0]);
				}
			}
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(time[0][1]);
		
		int max = 0;
		int start=0;
		int end = 0;
		
		for(int i=1;i<time.length;i++) {
			if(pq.peek() <= time[i][0]) {
				pq.poll();
				start = i;
			}
			
			pq.add(time[i][1]);
		}
		
		sb.append(pq.size());

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
