package com.programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_프린터 {
	public static void main(String[] args) {
		int[] priorities = { 2, 1, 3, 2 };
		int location = 0; // 앞에서부터 0, 1, 2, ...

		System.out.println(solution(priorities, location));
	}

	public static int solution(int[] priorities, int location) {
		int answer = 0;

		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < priorities.length; i++) {
			queue.offer(new Point(i, priorities[i]));
		}

		while(!queue.isEmpty()) {
			boolean flag = false;
			int front = queue.peek().priority;
			for(Point p : queue) {
				if(p.priority > front) {
					flag = true;
					break;
				}
			}
			
			if(flag) {
				queue.offer(queue.poll());
			} else {
				if(queue.poll().location == location) {
					answer = priorities.length - queue.size();
				}
			}
		}
		
		
		return answer;
	}

	static class Point {
		int location, priority;

		public Point(int location, int priority) {
			super();
			this.location = location;
			this.priority = priority;
		}

		@Override
		public String toString() {
			return "Point [location=" + location + ", priority=" + priority + "]";
		}
		
		
	}
}
