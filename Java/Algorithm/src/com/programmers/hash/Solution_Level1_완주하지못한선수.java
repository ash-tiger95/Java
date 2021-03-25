package com.programmers.hash;

import java.util.HashMap;

public class Solution_Level1_완주하지못한선수 {
	public static void main(String[] args) {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = { "stanko", "ana", "mislav"};

		System.out.println(solution(participant, completion));
	}

	public static String solution(String[] participant, String[] completion) {
		/*
		// O(n*n)
		String answer = "";
        String temp = "";
        
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        int i = 0;
        
        while(i < completion.length){
            if(!completion[i].equals(participant[i])){
                temp = participant[i];
                break;
            }else{
                i++;
            }
        }
        
        if(!temp.equals("")){
            answer = temp;
        }else{
            answer = participant[participant.length-1];
        }
        
        return answer;
        */
		
		// Map의 효율성이 더 좋다. O(n)
		String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        for (String player : participant) {
        	hm.put(player, hm.getOrDefault(player, 0) + 1); // getOrDefault(key, defaultValue): 찾는 키가 존재하면 찾는 키의 값을 반환 없다면 기본값 반환
        }
        for (String player : completion) {
        	hm.put(player, hm.get(player) - 1); // get(key): value 출력
        }

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0){
                answer = key;
            }
        }
        return answer;
	}
}
