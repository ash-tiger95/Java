package com.test.day0403_kakaocommerce;

import java.util.TreeSet;

public class Solution01_기프트카드 {
	public int solution(int[] gift_cards, int[] wants) {
        int answer = 0; // 최소


        int N = wants.length;
        TreeSet<Integer> gc = new TreeSet<>();
        for(int i=0;i<N;i++){
            gc.add(gift_cards[i]);
        }

        TreeSet<Integer> ts = new TreeSet<>();
        for(int i=0;i<N;i++){
            ts.add(wants[i]);
        }

        for(Integer i : gc){
            if(ts.contains(i)){
                answer++;
            }
        }

        return N-answer;
    }
}
