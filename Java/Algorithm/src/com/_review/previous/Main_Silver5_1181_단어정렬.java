package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class Main_Silver5_1181_단어정렬 {
	static int N;
	static TreeSet<String> ts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		ts = new TreeSet<String>(new Comparator<String>() {

			@Override
			public int compare(String pre, String next) { // pre가 나중에 들어온 값
				if (pre.length() != next.length()) {
					System.out.println("길이 비교: " + pre + " " + next + " " + (pre.length() - next.length()));
					return pre.length() - next.length(); // 양수면 pre가 뒤로(오름차순)
				} else {
					System.out.println("길이 같아: " + pre + " " + next + " " + pre.compareTo(next));
					return pre.compareTo(next); // 양수면 pre가 뒤로(오름차순)
				}
			}

		});

		for (int i = 0; i < N; i++) {
			ts.add(br.readLine());
			System.out.println(ts);
		}

		for (String name : ts) {
			System.out.println(name);
		}
	}
}
/*
 * testcase 13 but i wont hesitate no more no more it cannot wait im yours
 */