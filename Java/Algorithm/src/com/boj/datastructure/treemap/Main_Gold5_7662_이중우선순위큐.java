package com.boj.datastructure.treemap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * 참고
 * PriorityQueue<Integer> minQue = new PriorityQueue<>(); -> 오름차순
 * PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder()); -> 내림차순
 * 
 * TreeMap: key순으로 정렬 -> firstKey(), lastKey() 메서드 유용!
 */

public class Main_Gold5_7662_이중우선순위큐 {
	static int T, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			TreeMap<Long, Long> tm = new TreeMap<>();
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char op = st.nextToken().charAt(0);
				long val = Long.parseLong(st.nextToken());

				if (op == 'I') {
					if (tm.containsKey(val)) {
						tm.put(val, tm.get(val) + 1);
					} else {
						tm.put(val, 1L);
					}
				} else { // D
					if (tm.isEmpty()) {
						continue;
					}
					if (val == -1) { // 최솟값 삭제
						long minKey = tm.firstKey();
						long next = tm.get(minKey) - 1;
						if (next == 0) {
							tm.remove(minKey);
						} else {	
							tm.put(minKey, next);
						}
					} else { // 최댓값 삭제
						long maxKey = tm.lastKey();
						long next = tm.get(maxKey)-1;
						if(next == 0) {
							tm.remove(maxKey);
						} else {
							tm.put(maxKey, next);
						}
					}

				} // end D
				System.out.println(tm);
			} // end N
			
			if(tm.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.println(tm.lastKey()+" " +tm.firstKey());
			}

		}

	}
}
