package com.programmers.heap;

import java.util.TreeMap;

public class Solution_Level3_이중우선순위큐 {
	TreeMap<Integer, Integer> tm;

	public int[] solution(String[] operations) {
		int[] answer = new int[2];

		tm = new TreeMap<>();
		for (int i = 0; i < operations.length; i++) {
			String[] a = operations[i].split(" ");
			int b = Integer.parseInt(a[1]);

			if (a[0].equals("I")) {
				tm.put(b, tm.getOrDefault(b, 0) + 1);
			} else {
				if (tm.size() == 0) {
					continue;
				} else {
					if (b == 1) { // 최댓값 삭제
						int maxKey = tm.lastKey();
						int value = tm.get(maxKey);

						if (value == 1) {
							tm.remove(maxKey);
						} else {
							tm.remove(maxKey);
							tm.put(maxKey, value - 1);
						}
					} else {
						int minKey = tm.firstKey();
						int value = tm.get(minKey);

						if (value == 1) {
							tm.remove(minKey);
						} else {
							tm.remove(minKey);
							tm.put(minKey, value - 1);
						}
					}
				}
			}
		}

		if (tm.size() == 0) {
			answer[0] = 0;
			answer[1] = 0;
		} else {
			answer[1] = tm.firstKey();
			answer[0] = tm.lastKey();
		}

		return answer;
	}
}
