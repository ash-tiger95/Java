package com.boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 풀이)
 * substring
 * 
 * 잘 못 접근한 풀이)
 * 순서대로 검사하는 경우에 맞고 맞고 틀리다. 하면 두번째 맞고로 돌아가야하는데 이 부분을 생략함
 * 
 * @author jugia
 *
 */
public class Main_Silver4_1543_문서검색 {

	static int Ans;
	static String in, word;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		in = br.readLine();
		word = br.readLine();
		int len = word.length();
		
		Ans = 0;

		for (int i = 0; i < in.length() - len + 1; i++) {
			if (in.substring(i, i + len).equals(word)) {
				Ans++;
				i += len - 1; // ★. for문 끝나고 i가 증가하니깐 - 1
			}
		}

		System.out.println(Ans);
		
		br.close();
	}
	
	// 잘못생각한 풀이
	/*
	static int Ans;
	static char[] in, word;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
//		String input = br.readLine();
//		in = new char[input.length()];
//		in = input.toCharArray();
		 
		in = br.readLine().toCharArray(); // 위처럼 배열 크기 따로 설정 안해도 된다
		word = br.readLine().toCharArray();

		Ans = 0;

		int point = 0;

		for (int i = 0; i < in.length; i++) {
			if (in[i] == word[point]) {
				point++;

				if (point == word.length) {
					point = 0;
					Ans++;
				}
			} else {
				point = 0;
			}
		}
		
		System.out.println(Ans);
	}
	 */
}
