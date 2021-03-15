package com.programmers.level2;

import java.util.Arrays;

public class Solution_구명보트 {
	public static void main(String[] args) {
		int[] people = {70,50,80,50};
		int limit = 100;
		System.out.println(solution(people,limit));
	}
	public static int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
       int i=0;
        
        for(int j=people.length-1;i<=j;j--){
            if(people[j] + people[i] <= limit){
                i++;
            } 
            answer++;
        }
        
        return answer;
    }
}
