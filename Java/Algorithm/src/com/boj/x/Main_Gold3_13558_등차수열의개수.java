package com.boj.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main_Gold3_13558_등차수열의개수 {
	static int N, Ans;
	static int[] input;
	static HashSet<Node> set;

	static class Node {
		int i, j, k;

		public Node(int i, int j, int k) {
			super();
			this.i = i;
			this.j = j;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Node [i=" + i + ", j=" + j + ", k=" + k + "]";
		}

		// equals: 두 객체의 내용이 같은지 확인하는 메서드

		// hashCode: 두 객체가 같은 객체인지 확인하는 메서드

		@Override
		public int hashCode() {
			return Objects.hash(i * 10000 + j * 100 + k);
		}

		@Override
		public boolean equals(Object o) {
			Node p = (Node) o;
			return p.i == this.i && this.j == p.j && p.k == this.k;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Ans = 0;

		input = new int[N];
		set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				for (int k = j + 1; k < N; k++) {
//					System.out.println(i+","+j+","+k);
					if (input[j] * 2 == (input[i] + input[k])) {
						if (set.contains(new Node(input[i], input[j], input[k]))) {
							Ans++;
						} else {
							Ans++;
							set.add(new Node(input[i], input[j], input[k]));

						}
//						System.out.println(input[i]+" "+input[j]+" "+input[k]);
					}
				}
			}
		}
//		System.out.println(set.toString());
		System.out.println(Ans);
	}

}
