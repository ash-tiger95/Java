package com.boj.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main_Bronze2_10809_알파벳찾기 {

	static char[] in;
	static int[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		in = br.readLine().toCharArray();

		alpha = new int[26];
		Arrays.fill(alpha, -1);
		
		for(int i=0;i<in.length;i++) {
			int ch = in[i] - 'a';
			if(alpha[ch] == -1) {
				alpha[ch] = i;
			}
		}
		
		for(int i=0;i<26;i++) {
			sb.append(alpha[i]).append(" ");
		}

		bw.write(sb.toString()); // 출력
//		bw.newLine(); // 줄바꿈
        bw.flush(); //남아있는 데이터를 모두 출력시킴

        br.close(); 
        bw.close();
	}
}
