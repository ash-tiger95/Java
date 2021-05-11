package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_Silver4_10816_숫자카드2 {
	static int N, M;
	static HashMap<Integer, Integer> hm;
	static int[] card, check;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		hm = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			hm.put(a, hm.getOrDefault(a, 0) + 1);
		}
//		System.out.println(hm.keySet());
		card = new int[hm.size()];
		int idx = 0;
		for (Integer key : hm.keySet()) {
			card[idx++] = key;
		}
		Arrays.sort(card);
//		System.out.println(Arrays.toString(card));

		M = Integer.parseInt(br.readLine());
		check = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			check[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			binarySearch(check[i]);
		}
		System.out.println(sb);
	}

	private static void binarySearch(int num) {
		int left = 0;
		int right = card.length - 1;
		int mid;

		while (left <= right) {
			mid = (left + right) / 2;
			
			if (card[mid] == num) {
//				System.out.println(hm.get(num));
				sb.append(hm.get(num)).append(" ");
				return;
			}

			if (num < card[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		sb.append(0).append(" ");
		return;
	}

}
