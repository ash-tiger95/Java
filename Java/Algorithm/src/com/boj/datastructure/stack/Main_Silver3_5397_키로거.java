package com.boj.datastructure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 풀이) 스택 2개: 165192KB, 1276ms
 * 또 다른 풀이) 링크드 리스트: 183992KB, 2884ms
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
			// 커서를 중심으로 앞부분은 front 스택에, 뒷부분은 back 스택에 넣는다.
			Stack<Character> front = new Stack<>();
			Stack<Character> back = new Stack<>();

			for (int i = 0; i < in.length(); i++) {

				if (in.charAt(i) == '<') { // 1. 커서 이동
					if(!front.isEmpty()) {
						back.push(front.pop());
					}
				} else if (in.charAt(i) == '>') { // 2. 커서 이동
					if(!back.isEmpty()) {
						front.push(back.pop());
					}
				} else if (in.charAt(i) == '-') { // 3. 백 스페이스: 커서 앞 문자 삭제
					if(!front.isEmpty()) {
						front.pop();
					}
				} else {
					front.push(in.charAt(i));
				}

			}

			while(!front.isEmpty()) {
				back.push(front.pop());
			}
			
			while(!back.isEmpty()) {
				sb.append(back.pop());
			}
			sb.append("\n");

		} // for T
		System.out.print(sb);
	}
}
