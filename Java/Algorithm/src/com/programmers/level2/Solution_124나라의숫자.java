package com.programmers.level2;

public class Solution_124나라의숫자 {
	public static void main(String[] args) {
		int n =1;
		System.out.println(solution(n));
	}
	public static String solution(int n) {
	      String[] num = {"4","1","2"};
	      String answer = "";

	      while(n > 0){
	          answer = num[n % 3] + answer;
	          n = (n - 1) / 3;
	      }
	      return answer;
	  }
}
