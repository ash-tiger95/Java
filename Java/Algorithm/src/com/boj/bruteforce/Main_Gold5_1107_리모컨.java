package com.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Gold5_1107_리모컨 {
	static int N;
	static int M;
	static boolean[] broken;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 이동하려는 채널, 0 <= N <= 500,000
		M = Integer.parseInt(br.readLine()); // 고장난 버튼 개수, 0 <= M <= 10
		broken = new boolean[10]; // 0부터 9
		if(M !=0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}
			
		}

		// 수빈이가 현재 보고있는 채널: 100번
		int min = Math.abs(N - 100); // +, -로만 이동한 거리

		// 누를 수 있는 버튼: 1~9
		for (int i = 0; i < 1000000; i++) { // 999,999까지 누를 수 있다.
			int len = check(i);
			if (len > 0) {
				int press = Math.abs(N - i);
				min = Math.min(min, len + press);
			}
		}
		System.out.println(min);
	}

	private static int check(int n) {
		if (n == 0) {
			if (broken[0]) {
				return 0;
			} else {
				return 1;
			}
		}
		int len = 0;
		while (n > 0) { // 1->2->3 누르나 3->2->1 누르나 눌러지기만 하면 되니 같은 순서이다.
			if (broken[n % 10]) { // 고장난 버튼이 있는 경우
				return 0;
			}
			n /= 10;
			len += 1; // 숫자버튼 누르는 횟수 증가
		}
		return len;
	}

}
