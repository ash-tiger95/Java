package com.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 서로소 집합: Disjoint-set
 * 서로소 집합을 표현하는 알고리즘: Union-Find 알고리즘 (트리 구조)
 * 
 * Union-Find 연산
 * 1. make-set(x): 초기화, x를 유일한 원소로 하는 새로운 집합 
 * 2. union(x, y): 합하기, x가 속한 집합과 y가 속한 집합을 합친다. 즉, x와 y가 두 집합을 합치는 연산 
 * 3. find(x): 찾기, x가 속한 집합의 대표값(루트 노드 값)을 반환한다.
 * 
 * @author jugia
 *
 */
public class Main_Gold4_1717_집합의표현 {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // {0} ~ {N} 집합, 1 <= N <= 1,000,000
		M = Integer.parseInt(st.nextToken()); // 연산 개수, 1 <= M <= 100,000

		// 1. make-set
		makeSet(N);

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int ope = Integer.parseInt(st.nextToken()); // 연산, 0: 합집합, 1: 확인
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (ope == 0) {
				// union
				union(a, b);
			} else {
				// find
				a = find(a);
				b = find(b);
				if (a == b) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}

		}
//		System.out.println(Arrays.toString(parent));
		System.out.println(sb.toString());

	}

	public static void makeSet(int n) {
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		/*
		 * if(a == b) { return; } parent[a] = b;
		 */
		// 50116KB, 464ms

		if (a != b) {
			if (a < b) {
				parent[b] = a;
			} else {
				parent[a] = b;
			}
		}
		// 50212KB, 456ms
	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
