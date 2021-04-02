package com.programmers.test.kakao;

import java.util.TreeMap;

public class Solution_Level2_문자열압축 {
	
	public static int solution(String s) {
        int answer = s.length(); // 가장 큰 경우
        System.out.println(s);
        int len = s.length();
        
        for(int i=1;i<len/2;i++) { // 입력 문자열의 절반 길이까지 검사
        	int start = 0;
        	int end = start+i; // 포함 x
        	String prev = "";
        	boolean flag = false;
        	
        	int temp = 0;
        	
        	while(end<len && start < len) {
        		if(start+i >=len || end+i>= len) {
        			break;
        		}
        		prev = s.substring(start,end);
        		
        		System.out.println(prev);
        		
        		if(s.subSequence(start+i, end+i).equals(prev)) { // 이전 문자열과 같으면
        			
        			flag = true;
        		} else {
        			if(flag) {
        				temp += 1+i;
        				flag = false;
        			}else {
        				temp+=i;
        			}
        		}
        		
        		start += i;
    			end +=i;
        		
        	}
        	
        	
        	answer = Math.min(answer, temp);
        }
        
        return answer;
    }

	public static void main(String[] args) {
		String s = "aabbacccd";
		System.out.println(solution(s));
	}

}
