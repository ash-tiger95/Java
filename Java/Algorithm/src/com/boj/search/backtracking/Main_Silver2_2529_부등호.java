package com.boj.search.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_Silver2_2529_부등호 {

	static int N;
	static String[] input;
	static boolean[] visited;
	static ArrayList<String> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		input = new String[N];
		input = br.readLine().split(" ");
		System.out.println(Arrays.toString(input));
		list = new ArrayList<>();

		visited = new boolean[10];
		for (int i = 0; i <= 9; i++) {
			visited[i] = true;
			dfs(i, 0, i + "");
			
		}
		
		System.out.println(list.get(list.size()-1));
		System.out.println(list.get(0));
	}

	private static void dfs(int v, int cnt, String str) {
		System.out.println(v+" "+cnt+" "+str);
		System.out.println(Arrays.toString(visited));
		if (cnt == N) {
			list.add(str);
		} else {
			for (int i = 0; i <= 9; i++) {
				if (!visited[i]) {
					if (input[cnt].equals("<")) {
						if (v > i) {
							continue;
						}
					} else {
						if (v < i) {
							continue;
						}
					}

					visited[i] = true;

					dfs(i, cnt + 1, str + i);
				}
			}
		}
		// backtracking
		visited[v] = false;
	}
}
