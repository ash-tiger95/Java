package com.test.day0415월간코드챌린지;

import java.util.Stack;

public class Solution2 { // 성공
	public static int solution(String s) {
        int answer = 0;
        int N = s.length();
        
        
        for(int i=0;i<N;i++){
            char temp = s.charAt(0);
            
            s = s.substring(1,N);
            s += temp;
            System.out.println(s);
            
            if(check(s)){
                answer++;
            }
            System.out.println(answer);
        }
        
        return answer;
    }
    
    private static boolean check(String s){
    	int N = s.length();
        Stack<Character> st = new Stack<>();
        
        for(int i=0;i<N;i++){
        	if(st.empty()) {
            	if(s.charAt(i) == '[' || s.charAt(i) == '{'|| s.charAt(i) == '(') {
            		st.add(s.charAt(i));
            	} else {
            		return false;
            	}
            } else {
            	char input  = s.charAt(i);
                
            	if(input == '[' || input == '{' || input == '(') {
            		st.add(input);
            	} else {

                	char temp = st.pop();
                	if(temp == '[') {
                		if(input != ']') {
                			return false;
                		}
                		
                	} else if(temp == '{') {
                		if(input != '}') {
                			return false;
                		}
                		
                	} else if(temp == '(') {
                		if(input != ')') {
                			return false;
                		}
                		
                	}
            	}
            	
            	
            }
        }
       
        if(st.isEmpty()){
            return true;
        } else{
            return false;
        }
    }
    
	public static void main(String[] args) {
		String s = "{({})}";
		System.out.println(solution(s));
	}
}
