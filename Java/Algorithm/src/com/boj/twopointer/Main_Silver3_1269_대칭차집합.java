package com.boj.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이) 투 포인터: 포인터 2개를 이용해 하나 하나 비교, 956ms
 * 
 * 다른 풀이) HashSet: HashSet을 이용해 contain 되었는지 판별, 696ms
 * @author jugia
 *
 */
public class Main_Silver3_1269_대칭차집합 {

	static int aSize, bSize, Ans;
	static int[] a, b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		aSize = Integer.parseInt(st.nextToken());
		bSize = Integer.parseInt(st.nextToken());

		a = new int[aSize];
		b = new int[bSize];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < aSize; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < bSize; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(a);
		Arrays.sort(b);

		Ans = 0;
		if (aSize < bSize) {
			search(aSize, bSize, a, b);
		} else {
			search(bSize, aSize, b, a);
		}

		System.out.println(aSize + bSize - Ans * 2);
	}

	private static void search(int as, int bs, int[] aArr, int[] bArr) {

		int p1 = 0;
		int p2 = 0;

		while (true) {
			if (p1 == as) {
				break;
			}
			
			if(p2 == bs) { // aArr에 들어있는 모든 수가 bArr보다 클 경우를 대비
				break;
			}

			if (aArr[p1] < bArr[p2]) {
				p1++;
			} else if (aArr[p1] > bArr[p2]) {
				p2++;
			} else {
				p1++;
				p2++;
				Ans++;
			}
		}
	}
}
