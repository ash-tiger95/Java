package com._review.swe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_Test_5658_보물상자비밀번호 {
	static int T, N,K;
    static String[] totalCase;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 숫자 갯수
            K = Integer.parseInt(st.nextToken()); // 몇 번째 큰 수
             
            String data = br.readLine();
            totalCase = new String[N];
             
             
            int index = 0;
            for(int j=0;j<N/4;j++) {
                for(int i=1;i<5;i++) {
                    totalCase[index] = data.substring((i-1)*N/4, i*N/4);
                    index++;
                }
                String a = data.charAt(N-1)+"";
                data = a.concat(data);
                data = data.substring(0, N);
            }
             
            Arrays.sort(totalCase, Collections.reverseOrder());
//          for(int i=0;i<N;i++) {
//              System.out.println(totalCase[i]);
//          }
             
            String Ans = "";
            int count = 0;
            for(int i=0;i<N;i++) {
                if(count == K-1) {
                    Ans = totalCase[i];
                    break;
                }
                if(totalCase[i].equals(totalCase[i+1])) {
                    continue;
                }
                 
                count++;
            }
             
            int value = 0;
            int jisu = N/4-1;
            for(int i=0;i<N/4;i++) {
                char z = Ans.charAt(i);
                int x=0;
                if(z >=48 && z<= 57)
                    x = z-'0';
                else {
                    switch(z) {
                    case 'A':
                        x = 10;
                        break;
                    case 'B':
                        x = 11;
                        break;
                    case 'C':
                        x = 12;
                        break;
                    case 'D':
                        x = 13;
                        break;
                    case 'E':
                        x = 14;
                        break;
                    case 'F':
                        x = 15;
                        break;
                    }
                }
                 
                value += x*Math.pow(16, jisu--);
            }
            System.out.println("#"+t+" "+value);
             
        }
    }
}
