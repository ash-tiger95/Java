package com.boj.datastructure.hashset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 풀이) HashSet: HashSet을 이용해 contain 되었는지 판별, 696ms
 * 
 * 다른 풀이) 투 포인터: 포인터 2개를 이용해 하나 하나 비교, 956ms
 * @author jugia
 *
 */
public class Main_Silver3_1269_대칭차집합 {

	static int aSize, bSize, Ans;
	static HashSet<Integer> hs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Ans = 0;

		aSize = Integer.parseInt(st.nextToken());
		bSize = Integer.parseInt(st.nextToken());

		hs = new HashSet<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < aSize; i++) {
			hs.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < bSize; i++) {
			int in = Integer.parseInt(st.nextToken());
			if (hs.contains(in)) {
				Ans++;
			}
		}

		System.out.println(aSize + bSize - Ans * 2);
	}
}