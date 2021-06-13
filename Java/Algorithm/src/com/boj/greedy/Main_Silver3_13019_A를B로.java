package com.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 아이디어!
 * 
 * 맨 뒤에서부터 같은 것을 체크한다.
 * 
 * @author jugia
 *
 */
public class Main_Silver3_13019_A를B로 {

	static char[] a, b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String in = br.readLine();
		a = new char[in.length()];
		a = in.toCharArray();

		in = br.readLine();
		b = new char[in.length()];
		b = in.toCharArray();

		int size = a.length;
		int count = 0;

		for (int i = size - 1; i >= 0; i--) { // 마지막 부분부터 같은 부분의 개수를 구한다.
			if (a[i] == b[size - 1 - count]) {
				count++;
			}
		}

		// 만들 수 없는 경우 -1 출력
		Arrays.sort(a);
		Arrays.sort(b);

		boolean isSame = true;
		for (int i = 0; i < size; i++) {
			if (a[i] == b[i]) {
				continue;
			} else {
				System.out.println(-1);
				isSame = false;
				break;
			}
		}

		if (isSame) {
			System.out.println(size - count);
		}

		br.close();
	}
}
