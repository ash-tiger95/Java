package com.programmers.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_Level3_디스크컨트롤러 {

	static class Node implements Comparable<Node> {
		int start, time;

		public Node(int start, int time) {
			super();
			this.start = start;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			if (this.time == o.time) {
				return Integer.compare(this.start, o.start);
			} else {
				return Integer.compare(this.time, o.time);
			}
		}

		@Override
		public String toString() {
			return start + ", " + time;
		}

	}

	public static void main(String[] args) {
		int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };

		System.out.println(solution(jobs));
	}

	public static int solution(int[][] jobs) { // jobs[요청 시점][작업 소요시간]
		PriorityQueue<Node> pq = new PriorityQueue<>();
		List<Node> list = new ArrayList<>();

		// PriorityQueue를 활용한 Job 정렬
		for (int i = 0; i < jobs.length; i++) {
			pq.add(new Node(jobs[i][0], jobs[i][1]));
		}

		// list에 우선순위 순으로 정렬된 Job 순차적으로 삽입
		for (int i = 0; i < jobs.length; i++) {
			list.add(pq.poll());
		}

		int time = 0;
		int sum = 0;
		while (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 시작시간이 현재 시간보다 이전이라면 시작 가능
				if (time >= list.get(i).start) {
					time += list.get(i).time;
					sum += time - list.get(i).start;
					list.remove(i);
					break;
				}
				// 시작시간이 현재 시간보다 이전인 것이 없다면 시간 1초 증가
				if (i == list.size() - 1)
					time++;
			}
		}

		int answer = (sum / jobs.length);
		return answer;
	}

}
