package com.programmers.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_Level2_다리를지나는트럭 {

	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = { 7, 4, 5, 6 };
		System.out.println(solution(bridge_length, weight, truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;

		Queue<Truck> q = new LinkedList<>();
		int truckOn = 0;
		int truckIdx = 0;
		while (true) {
			int size = q.size();

			// 다리 위에 있는 트럭 먼저 이동
			for (int i = 0; i < size; i++) {
				Truck ct = q.poll();
				if (ct.loc + 1 == bridge_length) {
					truckOn -= ct.weight;
					continue;
				} else {
					q.offer(new Truck(ct.loc + 1, ct.weight));
				}
			}

			// 새로운 트럭 추가
			if (truckIdx < truck_weights.length && truckOn + truck_weights[truckIdx] <= weight) {
				q.offer(new Truck(0, truck_weights[truckIdx]));
				truckOn += truck_weights[truckIdx];
				truckIdx++;
			}
			answer++;

			if (truckOn == 0 && truckIdx == truck_weights.length) {
				break;
			}

		}

		return answer;
	}

	static class Truck {
		int loc, weight;

		public Truck(int loc, int weight) {
			super();
			this.loc = loc;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Truck [loc=" + loc + ", weight=" + weight + "]";
		}

	}
}
