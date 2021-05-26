package com.boj.datastructure.arraydeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main_Silver4_4949_균형잡힌세상 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String in = br.readLine();
			if (in.equals(".")) {
				break;
			} else {

				if (check(in)) {
					sb.append("yes").append("\n");
				} else {
					sb.append("no").append("\n");
				}
			}
		} // for TestCase
		System.out.println(sb);
	}

	private static boolean check(String str) {
		ArrayDeque<Character> stack = new ArrayDeque<>(); // ArrayDeque는 stack보다 빠르다.

		char[] tmp = str.toCharArray();

		for (char c : tmp) {
			if (c == '[' || c == '(') {
				stack.push(c);
			} else if (c == ']') {
				if (stack.size() != 0 && stack.peek() == '[') {
					stack.pop();
				} else {
					return false;
				}

			} else if (c == ')') {
				if (stack.size() != 0 && stack.peek() == '(') {
					stack.pop();
				} else {
					return false;
				}
			}
		}

		return stack.size() == 0 ? true : false;
	}
}
