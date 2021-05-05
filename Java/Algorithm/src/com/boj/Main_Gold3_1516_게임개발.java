package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold3_1516_게임개발 {

	static int N;
	static int[] parent;
	static int[] time, pre, Ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 건물의 수, 1 <= N <= 500

		makeSet();

		time = new int[N + 1];
		pre = new int[N + 1];
		Ans = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken()); // 건설 시간
			int p = Integer.parseInt(st.nextToken()); // 이전에 지어야 하는 건물 번호

			time[i] = t;
			if (p != -1) {
				pre[i] = p;
				union(i, p);
			} else { // -1: 이전에 지어야할 건물이 없다.
				pre[i] = 0;
			}
		}
		System.out.println(Arrays.toString(pre));

		for (int i = 1; i <= N; i++) {
			if (pre[i] == 0) {
				Ans[i] = time[i];
			} else {

			}
		}
	}

	private static void makeSet() {
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		return;
	}

	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return find(parent[x]);
		}
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return;
		} else {
			if (a < b) {
				parent[b] = a;
			} else {
				parent[a] = b;
			}
			return;
		}
	}
}
