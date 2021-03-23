package com.programmers.dfsbfs;

public class Solution_Level3_단어변환 {
	
	static int len, answer;
    static boolean[] checked;

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		
		System.out.println(solution(begin,target,words));
	}
	
	private static int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        len = words.length;
        checked = new boolean[len];
        
        dfs(begin,target, 0, words);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

	private static void dfs(String begin, String target, int count, String[] words) {
		if(target.equals(begin)) {
			answer = Math.min(answer, count);
			return;
		}
		
		for(int i=0;i<len;i++) {
			if(checking(begin,words[i]) && !checked[i]) {
				checked[i] = true;
				dfs(words[i],target,count+1,words);
				checked[i] = false;
			}
		}
	}

	private static boolean checking(String begin, String string) {
		int count = 0;
		
		for(int i=0;i<begin.length();i++) {
//			if(begin.charAt(index))
		}
		return false;
	}
    

}
