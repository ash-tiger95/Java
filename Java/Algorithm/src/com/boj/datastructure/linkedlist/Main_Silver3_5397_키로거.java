package com.boj.datastructure.linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 풀이) 링크드 리스트: 183992KB, 2884ms
 * 또 다른 풀이) 스택 2개: 165192KB, 1276ms
 * 
 * @author jugia
 *
 */
public class Main_Silver3_5397_키로거 {

	static int T;
	static String in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken()); // 테스트 케이스

		for (int t = 0; t < T; t++) {
			in = br.readLine();
			LinkedList<Character> temp = new LinkedList<>();
			int cursor = 0;

			for (int i = 0; i < in.length(); i++) {

				if (in.charAt(i) == '<') { // 1. 커서 이동
					cursor--;
					if (cursor < 0) { // 커서의 최솟값은 0
						cursor = 0;
					}
					
				} else if (in.charAt(i) == '>') { // 2. 커서 이동
					cursor++;
					if (cursor > temp.size()) { // 커서의 최댓값은 temp.size()
						cursor = temp.size();
					}
					
				} else if (in.charAt(i) == '-') { // 3. 백 스페이스: 커서 앞 문자 삭제
					if (temp.size() == 0 || cursor == 0) { // 3-1) 삭제할 문자가 없는 경우 || 커서가 맨 앞에 있을 때
						continue;
					} else { // 3-2) 삭제할 문자가 있을 때
						temp.remove(--cursor);
					}

				} else {
					temp.add(cursor++, in.charAt(i));
				}

			}

			for(Character c : temp) {
				sb.append(c);
			}
			sb.append("\n");

		} // for T
		System.out.print(sb);
	}
}
