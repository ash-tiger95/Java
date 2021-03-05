package com.boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_Gold4_9935_폭발문자열 {

	static String input, bomb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		bomb = br.readLine();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < input.length(); i++) {
			stack.add(input.charAt(i));

			if (stack.size() >= bomb.length()) {
				boolean flag = true;

				for (int j = 0; j < bomb.length(); j++) {
					if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}

				if (flag) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}

			}

		}

		StringBuilder sb = new StringBuilder();
		for (Character ch : stack) {
			sb.append(ch);
		}
		System.out.println((sb.length() == 0 ? "FRULA" : sb));

	}

}
