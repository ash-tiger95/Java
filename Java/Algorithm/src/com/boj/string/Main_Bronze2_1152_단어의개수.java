package com.boj.string;

import java.io.IOException;

public class Main_Bronze2_1152_단어의개수 {

	public static void main(String[] args) throws IOException {
		// 내 풀이, 252ms
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		System.out.println(st.countTokens());
		*/
		
		// 다른 풀이, 144ms
		int count = 0;
        int pre_str = 32; // 공백을 의미한다. 아스키코드.
        int str;
        
        while(true) {
            str = System.in.read();

            // 입력받은 문자가 공백일 때,
            if(str == 32) {
                // 이전의 문자가 공백이 아니면 카운트
                if(pre_str != 32) count++;
            }
            
            // 입력받은 문자가 개행일때 ('\n')
            else if(str == 10) {
                // 이전의 문자가 공백이 아니면
                if(pre_str != 32) count++;
                break;
            }
            
            pre_str = str;
        }
        
        System.out.println(count);
	}
}
