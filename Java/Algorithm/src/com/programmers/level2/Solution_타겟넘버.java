package com.programmers.level2;

public class Solution_타겟넘버 {
	public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, 0, 0, target);
        return answer;
    }
    
    private int dfs(int[] numbers, int node, int sum, int target){
        if(node == numbers.length){
            if(sum==target)
                return 1;
            return 0;
        }
        return dfs(numbers, node+1, sum + numbers[node], target) 
        	 + dfs(numbers, node+1, sum - numbers[node], target);
    }
}
