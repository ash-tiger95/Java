package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_Silver3_1406_에디터 { // 시간초과 -> stack으로 해결

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		LinkedList<Character> ll = new LinkedList<>();

		String input = br.readLine();
		int N = input.length();

		for (int i = 0; i < N; i++) {
			ll.add(input.charAt(i));
		}
		int cursor = N; // 맨 오른쪽에서 커서 시작

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char command = st.nextToken().charAt(0);
			char word = ' ';

			if (command == 'L') { // 커서를 왼쪽을 한 칸 옮김
				if (cursor == 0) {
					continue;
				} else {
					cursor--;
				}
			} else if (command == 'D') { // 커서를 오른쪽으로 한 칸 옮김
				if (cursor == ll.size()) {
					continue;
				} else {
					cursor++;
				}
			} else if (command == 'B') { // 커서 왼쪽 문자 삭제
				if (cursor == 0) {
					continue;
				} else {
					ll.remove(cursor - 1);
					cursor--;
				}
			} else if (command == 'P') {
				word = st.nextToken().charAt(0);
				ll.add(cursor, word);
				cursor++;
			}
		}

		for (Character c : ll) {
			System.out.print(c);
		}
		System.out.println();
	}

}
