package com.programmers.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_기능개발 {
	public static void main(String[] args) {
		int[] progresses = { 93, 30, 55 };
		int[] speeds = { 1, 30, 5 };

		System.out.println(solution(progresses, speeds));
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		/*
		// 풀이 1
		int[] dayOfend = new int[100];
		int day = -1;
		for (int i = 0; i < progresses.length; i++) {
			while (progresses[i] + (day * speeds[i]) < 100) {
				day++;
			}
			dayOfend[day]++;
		}
		Arrays.stream(dayOfend).filter(i -> i != 0).forEach(System.out::println);
		return Arrays.stream(dayOfend).filter(i -> i != 0).toArray();
		*/
		
		// 풀이 2
		Queue<Integer> q = new LinkedList<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            double remain = (100 - progresses[i]) / (double) speeds[i];
            int date = (int) Math.ceil(remain); // 올림, floor:내림, round:반올림
            System.out.println(q);
            if (!q.isEmpty() && q.peek() < date) {
            	System.out.println(q.peek()+" "+date);
                answerList.add(q.size());
                q.clear();
            }
            q.offer(date);
//            System.out.println(q);
        }

        answerList.add(q.size());

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
	}
}
