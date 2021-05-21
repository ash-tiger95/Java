package com.test.day0415_월간코드챌린지;

public class Solution1 { // 성공
	public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        int N = absolutes.length;
        
        for(int i=0;i<N;i++){
            if(signs[i]){
                answer += absolutes[i];
            }else{
                answer += absolutes[i]*(-1);
            }
            
        }
        
        return answer;
    }
}
